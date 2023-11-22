package com.safecampusbackend.controller;

import com.safecampusbackend.model.entity.UserEntity;
import com.safecampusbackend.service.RegisterService;
import com.safecampusbackend.util.result.Result;
import com.safecampusbackend.util.result.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@AllArgsConstructor
@Api(value = "用户管理", tags = "UserController")
public class RegisterController {
    private final RegisterService registerService;

    @GetMapping("/getUser")
    @ApiOperation(value = "获取用户列表", notes = "获取所有用户信息")
    public Result<UserEntity> getUser() {
        UserEntity entity = registerService.getUser();
        if (entity != null) {
            return ResultUtil.success(entity);
        } else {
            return ResultUtil.error(404, "查询失败");
        }
    }
}
