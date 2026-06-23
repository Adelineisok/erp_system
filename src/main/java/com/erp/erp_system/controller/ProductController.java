package com.erp.erp_system.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.erp.erp_system.common.Result;
import com.erp.erp_system.dto.ProductDTO;
import com.erp.erp_system.entity.Product;
import com.erp.erp_system.mapper.ProductMapper;
import com.erp.erp_system.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    private ProductMapper productMapper;
    @PostMapping
    public Result save(@RequestBody ProductDTO dto){

        productService.save(dto);

        return Result.success(null);

    }
    @PutMapping("/{id}")
    public Result update(@PathVariable Long id,
                       @RequestBody ProductDTO dto){

        productService.update(id,dto);

        return Result.success(null);

    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){

        productService.delete(id);

        return Result.success(null);

    }
    @GetMapping("/{id}")
    public Result<Product> detail(@PathVariable Long id){

        return Result.success(productService.getById(id));

    }
    @GetMapping("/list")
    public Result<List<Product>>list(){

        return Result.success(productService.list());

    }
    @GetMapping("/page")
    public Result page(int page,int size){

        Page<Product> p=new Page<>(page,size);

        productMapper.selectPage(p,null);

        return Result.success(p);

    }
    LambdaQueryWrapper<Product>wrapper=new LambdaQueryWrapper<>();

}