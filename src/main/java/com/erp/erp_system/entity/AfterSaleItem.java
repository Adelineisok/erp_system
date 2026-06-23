package com.erp.erp_system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("after_sale_item")
public class AfterSaleItem {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long afterId;

    private Long productId;

    private Integer quantity;

}