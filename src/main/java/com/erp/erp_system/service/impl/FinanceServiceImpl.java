package com.erp.erp_system.service.impl;

import com.erp.erp_system.entity.FinanceRecord;
import com.erp.erp_system.mapper.FinanceMapper;
import com.erp.erp_system.service.FinanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class FinanceServiceImpl implements FinanceService {

    @Autowired
    private FinanceMapper financeMapper;
    @Override
    public void addIncome(String orderNo, BigDecimal amount){

        FinanceRecord record=new FinanceRecord();

        record.setRecordNo(
                "CW"+System.currentTimeMillis()
        );

        record.setType("收入");

        record.setBusinessNo(orderNo);

        record.setAmount(amount);

        record.setRemark("订单收入");

        financeMapper.insert(record);

    }
    @Override
    public void addExpense(String purchaseNo,
                           BigDecimal amount){

        FinanceRecord record=new FinanceRecord();

        record.setRecordNo(
                "CW"+System.currentTimeMillis()
        );

        record.setType("支出");

        record.setBusinessNo(purchaseNo);

        record.setAmount(amount);

        record.setRemark("采购支出");

        financeMapper.insert(record);

    }
    @Override
    public void addRefund(String orderNo, BigDecimal amount){

        FinanceRecord record=new FinanceRecord();

        record.setRecordNo(
                "CW"+System.currentTimeMillis()
        );

        record.setType("退款");

        record.setBusinessNo(orderNo);

        record.setAmount(amount);

        record.setRemark("售后退款");

        financeMapper.insert(record);

    }
    @Override
    public List<FinanceRecord> list(){

        return financeMapper.selectList(null);

    }
}