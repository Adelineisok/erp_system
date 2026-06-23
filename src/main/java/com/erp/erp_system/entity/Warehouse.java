package com.erp.erp_system.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("warehouse")
public class Warehouse {

    @TableId(type= IdType.AUTO)
    private Long id;

    private String warehouseCode;

    private String warehouseName;

    private String manager;

    private String phone;

    private String address;

    private Integer status;
}