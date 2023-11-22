package com.safecampusbackend.controller;

import com.safecampusbackend.model.dto.RegisterDTO;
import com.safecampusbackend.model.entity.CodeEntity;
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

import javax.validation.constraints.Email;

@Validated
@RestController
@RequestMapping
@AllArgsConstructor
@Api(value = "用户管理", tags = "UserController")
public class RegisterController {
    private final RegisterService registerService;

    @PostMapping("/register")
    @ApiOperation(value = "用户注册", notes = "用户注册接口")
    public Result<String> test(@RequestBody @Validated RegisterForm form) {
        RegisterDTO dto = new RegisterDTO();
        dto.toDTO(form);
        if (registerService.register(dto)) {
            return ResultUtil.success("注册成功！");
        } else {
            return ResultUtil.error(404, "注册失败");
        }
    }

    @GetMapping("/getCode")
    @ApiOperation(value = "获取注册验证码", notes = "用户通过注册邮箱，获取注册验证码")
    public Result<CodeEntity> getCode(@RequestParam @Email String mailAddress) {
        return ResultUtil.success(registerService.sendCodeToMail(mailAddress));
    }
}
