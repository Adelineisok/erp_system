package com.erp.erp_system.service;

import com.erp.erp_system.entity.FinanceRecord;

import java.math.BigDecimal;
import java.util.List;

public interface FinanceService {

    void addIncome(String orderNo,
                   BigDecimal amount);

    void addExpense(String purchaseNo,
                    BigDecimal amount);

    void addRefund(String orderNo,
                   BigDecimal amount);

    List<FinanceRecord> list();

}