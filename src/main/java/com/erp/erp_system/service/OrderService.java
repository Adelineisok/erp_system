package com.erp.erp_system.service;

import com.erp.erp_system.dto.OrderDTO;
import com.erp.erp_system.entity.Order;
import com.erp.erp_system.vo.OrderVO;

import java.util.List;

public interface OrderService {

    void save(OrderDTO dto);

    void deliver(Long id);

    void delete(Long id);

    List<Order> list();

    OrderVO getById(Long id);

}