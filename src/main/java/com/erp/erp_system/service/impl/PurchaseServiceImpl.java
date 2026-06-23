package com.erp.erp_system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.erp.erp_system.dto.PurchaseDTO;
import com.erp.erp_system.dto.PurchaseItemDTO;
import com.erp.erp_system.dto.StockInDTO;
import com.erp.erp_system.entity.PurchaseOrder;
import com.erp.erp_system.entity.PurchaseOrderItem;

import com.erp.erp_system.mapper.PurchaseItemMapper;
import com.erp.erp_system.mapper.PurchaseMapper;

import com.erp.erp_system.service.FinanceService;
import com.erp.erp_system.service.PurchaseService;
import com.erp.erp_system.service.StockService;
import com.erp.erp_system.vo.PurchaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    private PurchaseMapper purchaseMapper;


    @Autowired
    private PurchaseItemMapper purchaseItemMapper;
    @Autowired
    private StockService stockService;

    @Autowired
    private FinanceService financeService;

    @Override
    @Transactional
    public void save(PurchaseDTO dto) {

        PurchaseOrder purchase = new PurchaseOrder();

        purchase.setPurchaseNo("CG" + System.currentTimeMillis());

        purchase.setSupplierId(dto.getSupplierId());

        purchase.setWarehouseId(dto.getWarehouseId());

        purchase.setStatus("待入库");

        purchase.setOperator("admin");

        BigDecimal total = BigDecimal.ZERO;

        for (PurchaseItemDTO item : dto.getItems()) {

            total = total.add(
                    item.getPrice()
                            .multiply(BigDecimal.valueOf(item.getQuantity()))
            );

        }

        purchase.setTotalAmount(total);

        purchaseMapper.insert(purchase);

        for (PurchaseItemDTO item : dto.getItems()) {

            PurchaseOrderItem purchaseItem = new PurchaseOrderItem();

            purchaseItem.setPurchaseId(purchase.getId());

            purchaseItem.setProductId(item.getProductId());

            purchaseItem.setQuantity(item.getQuantity());

            purchaseItem.setPrice(item.getPrice());

            purchaseItem.setSubtotal(
                    item.getPrice()
                            .multiply(BigDecimal.valueOf(item.getQuantity()))
            );

            purchaseItemMapper.insert(purchaseItem);

        }

    }

    @Override
    public List<PurchaseVO> list() {

        return purchaseMapper.list();

    }

    @Override
    public PurchaseVO getById(Long id) {

        return purchaseMapper.getById(id);

    }

    @Override
    @Transactional
    public void stockIn(Long purchaseId) {

        PurchaseOrder purchase = purchaseMapper.selectById(purchaseId);

        if (purchase == null) {

            throw new RuntimeException("采购单不存在");

        }

        if ("已入库".equals(purchase.getStatus())) {

            throw new RuntimeException("采购单已经入库");

        }

        LambdaQueryWrapper<PurchaseOrderItem> wrapper =
                new LambdaQueryWrapper<>();

        wrapper.eq(PurchaseOrderItem::getPurchaseId, purchaseId);

        List<PurchaseOrderItem> items =
                purchaseItemMapper.selectList(wrapper);

        for (PurchaseOrderItem item : items) {

            StockInDTO dto = new StockInDTO();

            dto.setProductId(item.getProductId());

            dto.setWarehouseId(purchase.getWarehouseId());

            dto.setQuantity(item.getQuantity());

            stockService.stockIn(dto);

        }

        purchase.setStatus("已入库");

        purchaseMapper.updateById(purchase);

        financeService.addExpense(

                purchase.getPurchaseNo(),

                purchase.getTotalAmount()

        );

    }

    @Override
    @Transactional
    public void delete(Long id) {

        purchaseMapper.deleteById(id);

        LambdaQueryWrapper<PurchaseOrderItem> wrapper =
                new LambdaQueryWrapper<>();

        wrapper.eq(PurchaseOrderItem::getPurchaseId, id);

        purchaseItemMapper.delete(wrapper);


    }
}
