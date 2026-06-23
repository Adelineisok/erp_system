package com.erp.erp_system.dto;

import lombok.Data;

import java.util.List;

@Data
public class PurchaseDTO{

    private Long supplierId;

    private String remark;
    private Long warehouseId;
    private String operator;
    private List<PurchaseItemDTO> items;

}