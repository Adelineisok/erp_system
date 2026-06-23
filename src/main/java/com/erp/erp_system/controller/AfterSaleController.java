package com.erp.erp_system.controller;

import com.erp.erp_system.common.Result;
import com.erp.erp_system.dto.AfterSaleDTO;
import com.erp.erp_system.service.AfterSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/afterSale")
public class AfterSaleController {

    @Autowired
    private AfterSaleService afterSaleService;

    @PostMapping
    public Result save(
            @RequestBody AfterSaleDTO dto){

        afterSaleService.save(dto);

        return Result.success("success");

    }

    @GetMapping("/list")
    public Result list(){

        return Result.success(
                afterSaleService.list()
        );

    }

    @PostMapping("/approve/{id}")
    public Result approve(
            @PathVariable Long id){

        afterSaleService.approve(id);

        return Result.success("success");

    }

    @PostMapping("/reject/{id}")
    public Result reject(
            @PathVariable Long id){

        afterSaleService.reject(id);

        return Result.success("success");

    }

    @DeleteMapping("/{id}")
    public Result delete(
            @PathVariable Long id){

        afterSaleService.delete(id);

        return Result.success("success");

    }

}
