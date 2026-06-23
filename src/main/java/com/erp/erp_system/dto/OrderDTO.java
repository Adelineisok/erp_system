package com.erp.erp_system.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {

    private String customerName;

    private String phone;

    private String address;

    private List<OrderItemDTO> items;

}