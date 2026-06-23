package com.erp.erp_system.controller;

import com.erp.erp_system.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class DashboardController {

    @GetMapping("/dashboard")
    public Result dashboard(){

        Map<String,Object> map=new HashMap<>();

        map.put("productCount",18);

        map.put("orderCount",36);

        map.put("purchaseCount",15);

        map.put("warningCount",2);

        map.put("orderData",
                new Integer[]{5,8,10,15,20,18,25});

        map.put("purchaseData",
                new Integer[]{3,5,7,6,8,10,9});

        return Result.success(map);

    }

}