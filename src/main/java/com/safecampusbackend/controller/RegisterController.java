package com.safecampusbackend.controller;

import com.safecampusbackend.model.entity.UserEntity;
import com.safecampusbackend.model.form.RegisterForm;
import com.safecampusbackend.service.RegisterService;
import com.safecampusbackend.util.result.Result;
import com.safecampusbackend.util.result.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@AllArgsConstructor
@Api(value = "用户管理", tags = "UserController")
public class RegisterController {
    private final RegisterService registerService;

    @PostMapping("/test")
    @ApiOperation(value = "测试入参校验", notes = "测试入参校验")
    public RegisterForm test(@RequestBody @Validated RegisterForm form) {
        return form;
    }
}
