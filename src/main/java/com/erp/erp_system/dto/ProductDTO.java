package com.erp.erp_system.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {

    private String productCode;

    private String productName;

    private Long categoryId;

    private String specification;

    private BigDecimal costPrice;

    private BigDecimal salePrice;

    private String unit;

    private Integer status;
}