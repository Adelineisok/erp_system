package com.erp.erp_system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("after_sale")
public class AfterSale {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String afterNo;

    private Long orderId;

    private String customerName;

    private String reason;

    private BigDecimal refundAmount;

    private String status;

    private LocalDateTime createTime;

}