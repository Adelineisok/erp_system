package com.erp.erp_system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.erp_system.entity.Order;
import com.erp.erp_system.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    OrderVO getById(Long id);
}
