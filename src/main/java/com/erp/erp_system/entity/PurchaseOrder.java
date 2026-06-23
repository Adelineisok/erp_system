package com.erp.erp_system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("purchase_order")
public class PurchaseOrder{

    @TableId(type= IdType.AUTO)

    private Long id;

    private String purchaseNo;

    private Long supplierId;

    private BigDecimal totalAmount;

    private String status;

    private String remark;
    private Long warehouseId;
    private String operator;
}