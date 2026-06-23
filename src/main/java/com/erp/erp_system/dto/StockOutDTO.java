package com.erp.erp_system.dto;


import lombok.Data;



@Data
public class StockOutDTO {

    private Long productId;

    private Long warehouseId;

    private Integer quantity;

}