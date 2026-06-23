package com.erp.erp_system.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class AfterSaleDTO {

    private Long orderId;

    private String customerName;

    private String reason;

    private BigDecimal refundAmount;

    private List<AfterSaleItemDTO> items;

}
