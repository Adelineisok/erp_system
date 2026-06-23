package com.erp.erp_system.controller;



import com.erp.erp_system.common.Result;
import com.erp.erp_system.dto.LoginDTO;
import com.erp.erp_system.service.UserService;
import com.erp.erp_system.vo.LoginVO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService=userService;
    }

    @PostMapping("/login")
    public Result<LoginVO>login(@RequestBody @Valid LoginDTO loginDTO){

            return Result.success(userService.login(loginDTO));

    }

}