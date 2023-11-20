package com.safecampusbackend.service.impl;

import com.safecampusbackend.model.entity.UserEntity;
import com.safecampusbackend.model.mapper.RegisterMapper;
import com.safecampusbackend.service.RegisterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    private final RegisterMapper registerMapper;

    public UserEntity getUser(){
        return registerMapper.selectById(1);
    }
}
