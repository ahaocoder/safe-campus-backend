package com.safecampusbackend.service.impl;

import com.safecampusbackend.model.entity.UserEntity;
import com.safecampusbackend.model.mapper.UserMapper;
import com.safecampusbackend.service.RegisterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    private final UserMapper userMapper;

    public UserEntity getUser(){
        return userMapper.selectById(1);
    }
}
