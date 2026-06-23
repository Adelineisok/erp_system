package com.erp.erp_system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.erp.erp_system.dto.AfterSaleDTO;
import com.erp.erp_system.dto.AfterSaleItemDTO;
import com.erp.erp_system.dto.StockInDTO;
import com.erp.erp_system.entity.AfterSale;
import com.erp.erp_system.entity.AfterSaleItem;
import com.erp.erp_system.mapper.AfterSaleItemMapper;
import com.erp.erp_system.mapper.AfterSaleMapper;
import com.erp.erp_system.service.AfterSaleService;
import com.erp.erp_system.service.FinanceService;
import com.erp.erp_system.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class AfterSaleImpl implements AfterSaleService {
    @Autowired
    private AfterSaleMapper afterSaleMapper;
    @Autowired
    private AfterSaleItemMapper afterSaleItemMapper;
    @Autowired
    private StockService stockService;

    @Autowired
    private FinanceService financeService;
    @Transactional
    @Override
    public void save(AfterSaleDTO dto){

        AfterSale afterSale=new AfterSale();

        afterSale.setAfterNo(
                "TH"+System.currentTimeMillis()
        );

        afterSale.setOrderId(dto.getOrderId());

        afterSale.setCustomerName(
                dto.getCustomerName()
        );

        afterSale.setReason(dto.getReason());

        afterSale.setRefundAmount(
                dto.getRefundAmount()
        );

        afterSale.setStatus("待审核");

        afterSaleMapper.insert(afterSale);

        for(AfterSaleItemDTO item:dto.getItems()){

            AfterSaleItem afterItem=new AfterSaleItem();

            afterItem.setAfterId(afterSale.getId());

            afterItem.setProductId(item.getProductId());

            afterItem.setQuantity(item.getQuantity());

            afterSaleItemMapper.insert(afterItem);

        }

    }

    @Transactional
    @Override
    public void approve(Long id){

        AfterSale afterSale=
                afterSaleMapper.selectById(id);

        LambdaQueryWrapper<AfterSaleItem> wrapper=
                new LambdaQueryWrapper<>();

        wrapper.eq(
                AfterSaleItem::getAfterId,
                id
        );

        List<AfterSaleItem> items=
                afterSaleItemMapper.selectList(wrapper);

        for(AfterSaleItem item:items){



            StockInDTO stockInDTO = new StockInDTO();


            stockInDTO.setProductId(item.getProductId());      // 商品ID
            stockInDTO.setWarehouseId(1L);                     // 仓库ID
            stockInDTO.setQuantity(item.getQuantity());        // 入库数量



            stockService.stockIn(stockInDTO);

        }

        financeService.addRefund(

                afterSale.getAfterNo(),

                afterSale.getRefundAmount()

        );

        afterSale.setStatus("已完成");

        afterSaleMapper.updateById(afterSale);

    }
    @Override
    public void reject(Long id){

        AfterSale afterSale=
                afterSaleMapper.selectById(id);

        afterSale.setStatus("已拒绝");

        afterSaleMapper.updateById(afterSale);

    }
    @Override
    public List<AfterSale> list(){

        return afterSaleMapper.selectList(null);

    }
    @Transactional
    @Override
    public void delete(Long id){

        afterSaleMapper.deleteById(id);

        LambdaQueryWrapper<AfterSaleItem> wrapper=
                new LambdaQueryWrapper<>();

        wrapper.eq(
                AfterSaleItem::getAfterId,
                id
        );

        afterSaleItemMapper.delete(wrapper);

    }
}
