package com.erp.erp_system.controller;

import com.erp.erp_system.common.Result;
import com.erp.erp_system.dto.OrderDTO;
import com.erp.erp_system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Result save(@RequestBody OrderDTO dto){

        orderService.save(dto);

        return Result.success(null);

    }

    @GetMapping("/list")
    public Result list(){

        return Result.success(orderService.list());

    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){

        orderService.delete(id);

        return Result.success("success");

    }

    @PostMapping("/deliver/{id}")
    public Result deliver(@PathVariable Long id){

        orderService.deliver(id);

        return Result.success("success");

    }

}