package com.safecampusbackend.controller;

import com.safecampusbackend.model.entity.UserEntity;
import com.safecampusbackend.service.RegisterService;
import com.safecampusbackend.util.result.Result;
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
        Result<UserEntity> result = new Result<>();
        UserEntity entity = registerService.getUser();
        if (entity != null) {
            result.setCode(200);
            result.setMsg("查询成功");
            result.setData(entity);
            return result;
        } else {
            result.setCode(404);
            result.setMsg("查询失败");
            return result;
        }
    }
}
