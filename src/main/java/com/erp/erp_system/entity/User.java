package com.erp.erp_system.entity;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class User {
    @TableId
    private Long id;

    private String username;

    private String password;

    private String nickname;

    private String phone;

    private String role;

    private Integer status;
}
