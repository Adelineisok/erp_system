package com.erp.erp_system.vo;



import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PurchaseVO {


    private Long id;


    private String purchaseNo;


    private Long supplierId;


    private String supplierName;


    private Long warehouseId;


    private String warehouseName;


    private BigDecimal totalAmount;


    private String status;


    private String operator;

    
    private LocalDateTime createTime;

}
