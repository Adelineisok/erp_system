package com.erp.erp_system.service;

import com.erp.erp_system.dto.ProductDTO;
import com.erp.erp_system.entity.Product;

import java.util.List;

public interface ProductService {

    void save(ProductDTO dto);

    void update(Long id,ProductDTO dto);

    void delete(Long id);

    Product getById(Long id);

    List<Product> list();

}
