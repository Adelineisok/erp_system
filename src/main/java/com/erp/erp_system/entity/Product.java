package com.erp.erp_system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("product")
public class Product {

    @TableId(type= IdType.AUTO)
    private Long id;

    private String productCode;

    private String productName;

    private Long categoryId;

    private String specification;

    private BigDecimal costPrice;

    private BigDecimal salePrice;

    private String unit;

    private Integer status;

    @TableLogic
    private Integer deleted;
}