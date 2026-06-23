package com.erp.erp_system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.erp_system.entity.PurchaseOrder;
import com.erp.erp_system.vo.PurchaseVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PurchaseMapper extends BaseMapper<PurchaseOrder> {

    List<PurchaseVO> list();
    PurchaseVO getById(Long id);
}
