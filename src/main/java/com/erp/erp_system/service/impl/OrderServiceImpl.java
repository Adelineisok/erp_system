package com.erp.erp_system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.erp.erp_system.dto.OrderDTO;
import com.erp.erp_system.dto.OrderItemDTO;
import com.erp.erp_system.dto.StockOutDTO;
import com.erp.erp_system.entity.Order;
import com.erp.erp_system.entity.OrderItem;
import com.erp.erp_system.mapper.OrderItemMapper;
import com.erp.erp_system.mapper.OrderMapper;
import com.erp.erp_system.service.OrderService;
import com.erp.erp_system.service.StockService;
import com.erp.erp_system.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.erp.erp_system.service.FinanceService;
import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private StockService stockService;

    @Autowired
    private FinanceService financeService;
    @Transactional
    @Override
    public void save(OrderDTO dto) {

        Order order = new Order();

        order.setOrderNo("DD" + System.currentTimeMillis());

        order.setCustomerName(dto.getCustomerName());

        order.setPhone(dto.getPhone());

        order.setAddress(dto.getAddress());

        order.setStatus("待发货");

        BigDecimal total = BigDecimal.ZERO;

        for (OrderItemDTO item : dto.getItems()) {

            total = total.add(
                    item.getPrice().multiply(
                            BigDecimal.valueOf(item.getQuantity())
                    )
            );

        }

        order.setTotalAmount(total);

        orderMapper.insert(order);

        for (OrderItemDTO item : dto.getItems()) {

            OrderItem orderItem = new OrderItem();

            orderItem.setOrderId(order.getId());

            orderItem.setProductId(item.getProductId());

            orderItem.setQuantity(item.getQuantity());

            orderItem.setPrice(item.getPrice());

            orderItem.setSubtotal(
                    item.getPrice().multiply(
                            BigDecimal.valueOf(item.getQuantity())
                    )
            );

            orderItemMapper.insert(orderItem);

        }

    }
    @Override
    public List<Order> list() {

        return orderMapper.selectList(null);

    }
    @Override
    @Transactional
    public void delete(Long id) {

        orderMapper.deleteById(id);

        LambdaQueryWrapper<OrderItem> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(OrderItem::getOrderId,id);

        orderItemMapper.delete(wrapper);

    }

    @Transactional
    @Override
    public void deliver(Long id){

        Order order = orderMapper.selectById(id);

        LambdaQueryWrapper<OrderItem> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(OrderItem::getOrderId,id);

        List<OrderItem> items = orderItemMapper.selectList(wrapper);

        for (OrderItem item : items) {

            StockOutDTO dto = new StockOutDTO();

            dto.setProductId(item.getProductId());

            dto.setWarehouseId(1L);      // 如果以后一个订单对应一个仓库，这里可以改成订单里的仓库ID

            dto.setQuantity(item.getQuantity());

            stockService.stockOut(dto);

        }

        order.setStatus("已发货");

        orderMapper.updateById(order);

        financeService.addIncome(

                order.getOrderNo(),

                order.getTotalAmount()

        );

    }
    @Override
    public OrderVO getById(Long id) {
        return orderMapper.getById(id);
    }
}
