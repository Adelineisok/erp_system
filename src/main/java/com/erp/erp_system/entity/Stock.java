package com.erp.erp_system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("stock")
public class Stock {

    @TableId(type= IdType.AUTO)
    private Long id;

    private Long productId;

    private Long warehouseId;

    private Integer quantity;

    private Integer warningQuantity;
}