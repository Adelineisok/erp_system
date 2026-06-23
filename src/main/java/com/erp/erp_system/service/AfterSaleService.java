package com.erp.erp_system.service;

import com.erp.erp_system.dto.AfterSaleDTO;
import com.erp.erp_system.entity.AfterSale;

import java.util.List;

public interface AfterSaleService {
    void save(AfterSaleDTO dto);

    void approve(Long id);

    void reject(Long id);

    void delete(Long id);

    List<AfterSale> list();
}
