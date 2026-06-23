package com.erp.erp_system.vo;



import lombok.Data;

@Data
public class StockVO {


    private Long id;


    private Long productId;


    private String productName;


    private Long warehouseId;


    private String warehouseName;


    private Integer quantity;


    private Integer warningQuantity;

}