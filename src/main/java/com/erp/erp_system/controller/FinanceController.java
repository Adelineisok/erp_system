package com.erp.erp_system.controller;

import com.erp.erp_system.common.Result;
import com.erp.erp_system.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/finance")
public class FinanceController{

    @Autowired
    private FinanceService financeService;

    @GetMapping("/list")
    public Result list(){

        return Result.success(

                financeService.list()

        );

    }

}
