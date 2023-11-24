package com.safecampusbackend.service;

import com.safecampusbackend.model.dto.RegisterDTO;
import com.safecampusbackend.model.entity.CodeEntity;
import com.safecampusbackend.model.entity.UserEntity;

public interface RegisterService {
    /**
     * 注册接口
     *
     * @param dto 注册数据传输对象
     * @return true or false
     */
    Boolean register(RegisterDTO dto);

    /**
     * 给邮箱发送验证码
     *
     * @param mailAddress 邮件地址
     * @return code，uuid 验证码，唯一标识符
     */
    CodeEntity sendCodeToMail(String mailAddress);

    /**
     * 验证码校验
     *
     * @param codeEntity 验证码类对象
     * @return true or false
     */
    Boolean isCodeValid(CodeEntity codeEntity);

    /**
     * 检验用户名是否重复
     *
     * @param username 注册用户名
     * @return true or false
     */
    Boolean isUsernameDuplicate(String username);
}
