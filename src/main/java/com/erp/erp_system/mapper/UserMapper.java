package com.erp.erp_system.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erp.erp_system.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
