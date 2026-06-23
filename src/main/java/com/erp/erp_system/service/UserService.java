package com.erp.erp_system.service;



import com.erp.erp_system.dto.LoginDTO;
import com.erp.erp_system.vo.LoginVO;

public interface UserService {

    LoginVO login(LoginDTO loginDTO);

}