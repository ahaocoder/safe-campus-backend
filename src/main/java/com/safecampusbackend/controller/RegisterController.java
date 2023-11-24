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
    public Result<String> register(@RequestBody @Validated RegisterForm form) {
        CodeEntity codeEntity = form.getCodeEntity();
        if (!registerService.isCodeValid(codeEntity)){
            return ResultUtil.error(400, "验证码不正确");
        }

        // 检查用户名是否重复
        if (registerService.isUsernameDuplicate(form.getUsername())) {
            return ResultUtil.error(400, "用户名已存在");
        }

        if (registerService.register(RegisterDTO.toDTO(form))) {
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
