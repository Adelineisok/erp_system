package com.erp.erp_system.service.impl;



import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.erp.erp_system.dto.LoginDTO;
import com.erp.erp_system.entity.User;
import com.erp.erp_system.mapper.UserMapper;
import com.erp.erp_system.service.UserService;
import com.erp.erp_system.vo.LoginVO;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper=userMapper;
    }

    @Override
            public LoginVO login(LoginDTO loginDTO) {

        LambdaQueryWrapper<User>wrapper=new LambdaQueryWrapper<>();

        wrapper.eq(User::getUsername,loginDTO.getUsername());

        User user=userMapper.selectOne(wrapper);

        if (user==null) {
            throw new RuntimeException("用户名不存在");
        }

        if (!user.getPassword().equals(loginDTO.getPassword())) {
            throw new RuntimeException("密码错误");
        }

// 暂时先返回假Token
        return new LoginVO(
                "token-test-123456",
                user.getUsername(),
                user.getNickname(),
                user.getRole()
        );
    }
}