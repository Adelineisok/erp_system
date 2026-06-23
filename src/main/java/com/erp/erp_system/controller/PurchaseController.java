package com.erp.erp_system.controller;

import com.erp.erp_system.common.Result;
import com.erp.erp_system.dto.PurchaseDTO;
import com.erp.erp_system.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping
    public Result save(@RequestBody PurchaseDTO dto){

        purchaseService.save(dto);

        return Result.success("success");

    }

    @GetMapping("/list")
    public Result list(){

        return Result.success(
                purchaseService.list()
        );

    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Long id){

        return Result.success(
                purchaseService.getById(id)
        );

    }

    @PostMapping("/stockIn/{id}")
    public Result stockIn(@PathVariable Long id){

        purchaseService.stockIn(id);

        return Result.success("success");

    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){

        purchaseService.delete(id);

        return Result.success("success");

    }

}
