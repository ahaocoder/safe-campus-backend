package com.safecampusbackend.controller;

import com.safecampusbackend.model.entity.UserEntity;
import com.safecampusbackend.service.RegisterService;
import com.safecampusbackend.util.result.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@AllArgsConstructor
public class RegisterController {
    private final RegisterService registerService;

    @GetMapping("/getUser")
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
