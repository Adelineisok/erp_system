package com.erp.erp_system.controller;



import com.erp.erp_system.common.Result;
import com.erp.erp_system.entity.User;
import com.erp.erp_system.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    private final UserMapper userMapper;

    public TestController(UserMapper userMapper) {
        this.userMapper=userMapper;
    }

    @GetMapping("/test")
    public Result<List<User>> test(){

        return Result.success(userMapper.selectList(null));

    }

}