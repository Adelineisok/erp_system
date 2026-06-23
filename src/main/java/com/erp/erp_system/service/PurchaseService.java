package com.erp.erp_system.service;

import com.erp.erp_system.dto.PurchaseDTO;
import com.erp.erp_system.vo.PurchaseVO;

import java.util.List;

public interface PurchaseService {


    void save(PurchaseDTO dto);


    List<PurchaseVO> list();


    PurchaseVO getById(Long id);


    void stockIn(Long purchaseId);


    void delete(Long id);

}