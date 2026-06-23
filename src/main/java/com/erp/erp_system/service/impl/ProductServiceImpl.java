package com.erp.erp_system.service.impl;

import com.erp.erp_system.dto.ProductDTO;
import com.erp.erp_system.entity.Product;
import com.erp.erp_system.mapper.ProductMapper;
import com.erp.erp_system.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public void save(ProductDTO dto){

        Product product=new Product();

        BeanUtils.copyProperties(dto,product);

        productMapper.insert(product);

    }
    @Override
    public void update(Long id, ProductDTO dto){

        Product product=new Product();

        BeanUtils.copyProperties(dto,product);

        product.setId(id);

        productMapper.updateById(product);

    }
    @Override
    public void delete(Long id){

        productMapper.deleteById(id);

    }
    @Override
    public List<Product> list(){

        return productMapper.selectList(null);

    }
    @Override
    public Product getById(Long id) {

        return productMapper.selectById(id);

    }
}
