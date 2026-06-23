package com.erp.erp_system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.erp_system.entity.Stock;
import com.erp.erp_system.vo.StockVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StockMapper extends BaseMapper<Stock> {
    List<StockVO> warningList();
}
