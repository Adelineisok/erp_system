
package com.erp.erp_system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("stock_record")
public class StockRecord {


    @TableId(type = IdType.AUTO)
    private Long id;


    private Long productId;


    private Long warehouseId;


    private String type;


    private Integer quantity;

    private String remark;

    private String operator;

   
    private LocalDateTime createTime;

}