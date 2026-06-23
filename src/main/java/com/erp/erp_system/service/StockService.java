package com.erp.erp_system.service;

import com.erp.erp_system.dto.StockInDTO;
import com.erp.erp_system.dto.StockOutDTO;
import com.erp.erp_system.vo.StockVO;

import java.util.List;

public interface StockService {

    List<StockVO> list();

    void stockIn(StockInDTO dto);

    void stockOut(StockOutDTO dto);

    List<StockVO> warningList();

}