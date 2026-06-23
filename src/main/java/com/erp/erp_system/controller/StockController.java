package com.erp.erp_system.controller;

import com.erp.erp_system.common.Result;
import com.erp.erp_system.dto.StockInDTO;
import com.erp.erp_system.dto.StockOutDTO;
import com.erp.erp_system.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/list")
            public Result list(){

        return Result.success(

                stockService.list()

        );

    }

    @PostMapping("/in")
    public Result stockIn(@RequestBody StockInDTO dto){

        stockService.stockIn(dto);

        return Result.success("success");

    }

    @PostMapping("/out")
    public Result stockOut(@RequestBody StockOutDTO dto){

        stockService.stockOut(dto);

        return Result.success("success");

    }

    @GetMapping("/warning")
            public Result warning(){

        return Result.success(

                stockService.warningList()

        );

    }

}