package com.erp.erp_system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.erp.erp_system.dto.StockInDTO;
import com.erp.erp_system.dto.StockOutDTO;
import com.erp.erp_system.entity.Stock;
import com.erp.erp_system.entity.StockRecord;
import com.erp.erp_system.mapper.StockMapper;
import com.erp.erp_system.mapper.StockRecordMapper;
import com.erp.erp_system.service.StockService;
import com.erp.erp_system.vo.StockVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockMapper stockMapper;

    @Autowired
    private StockRecordMapper stockRecordMapper;
    @Override
    public List<StockVO> list() {
        LambdaQueryWrapper<Stock> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Stock::getWarehouseId, 1L);

        List<Stock> stockList = stockMapper.selectList(wrapper);

        // 使用 Stream 流进行批量类型转换
        return stockList.stream().map(stock -> {
            StockVO vo = new StockVO();
            org.springframework.beans.BeanUtils.copyProperties(stock, vo);
            return vo;
        }).collect(Collectors.toList());
    }
    @Override
    @Transactional
    public void stockIn(StockInDTO dto){

        LambdaQueryWrapper<Stock> wrapper=
                new LambdaQueryWrapper<>();

        wrapper.eq(
                Stock::getProductId,
                dto.getProductId()
        );

        wrapper.eq(
                Stock::getWarehouseId,
                dto.getWarehouseId()
        );

        Stock stock=stockMapper.selectOne(wrapper);

        if(stock==null){

            stock=new Stock();

            stock.setProductId(dto.getProductId());

            stock.setWarehouseId(dto.getWarehouseId());

            stock.setQuantity(dto.getQuantity());

            stock.setWarningQuantity(10);

            stockMapper.insert(stock);

        }else{

            stock.setQuantity(

                    stock.getQuantity()+dto.getQuantity()

            );

            stockMapper.updateById(stock);

        }

        StockRecord record=new StockRecord();

        record.setProductId(dto.getProductId());

        record.setWarehouseId(dto.getWarehouseId());

        record.setQuantity(dto.getQuantity());

        record.setType("入库");

        record.setRemark("采购入库");

        stockRecordMapper.insert(record);

    }
    @Override
    @Transactional
    public void stockOut(StockOutDTO dto){

        LambdaQueryWrapper<Stock>wrapper=
                new LambdaQueryWrapper<>();

        wrapper.eq(
                Stock::getProductId,
                dto.getProductId()
        );

        wrapper.eq(
                Stock::getWarehouseId,
                dto.getWarehouseId()
        );

        Stock stock=stockMapper.selectOne(wrapper);

        if(stock==null){

            throw new RuntimeException("库存不存在");

        }

        if(stock.getQuantity()<dto.getQuantity()){

            throw new RuntimeException("库存不足");

        }

        stock.setQuantity(

                stock.getQuantity()-dto.getQuantity()

        );

        stockMapper.updateById(stock);

        StockRecord record=new StockRecord();

        record.setProductId(dto.getProductId());

        record.setWarehouseId(dto.getWarehouseId());

        record.setQuantity(dto.getQuantity());

        record.setType("出库");

        record.setRemark("订单发货");

        stockRecordMapper.insert(record);

    }
    @Override
    public List<StockVO> warningList() {
        return stockMapper.warningList();
    }
}