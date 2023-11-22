package com.safecampusbackend.service.impl;

import cn.hutool.extra.mail.MailUtil;
import com.safecampusbackend.model.dto.RegisterDTO;
import com.safecampusbackend.model.entity.CodeEntity;
import com.safecampusbackend.model.entity.UserEntity;
import com.safecampusbackend.model.mapper.UserMapper;
import com.safecampusbackend.service.RegisterService;
import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.security.SecureRandom;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    private final UserMapper userMapper;
    private StringRedisTemplate redisTemplate;
    private final TemplateEngine templateEngine;

    // 注册实现
    public Boolean register(RegisterDTO dto) {
        return true;
    }

    // 给注册邮箱发送验证码实现
    public CodeEntity sendCodeToMail(String mailAddress) {
        String uuid = UUID.randomUUID().toString();
        String code = generateRandomNumber();
        CodeEntity entity = new CodeEntity();
        entity.setCode(code);
        entity.setUuid(uuid);

        // 将验证码存储到 Redis，设置有效期为 5 分钟
        String key = "verification_code:" + uuid;
        redisTemplate.opsForValue().set(key, code, 5, TimeUnit.MINUTES);

        // 创建Thymeleaf上下文对象，并设置变量值
        Context context = new Context();
        context.setVariable("Code", code);

        // 使用Thymeleaf模板引擎渲染邮件模板
        String emailContent = templateEngine.process("register-mail", context);

        // 给注册邮箱发送验证码
        MailUtil.send(mailAddress, "验证码", emailContent, true);
        return entity;
    }

    // 生成注册验证码
    private String generateRandomNumber() {
        SecureRandom random = new SecureRandom();
        int randomNumber = random.nextInt(1000000);  // 生成 [0, 999999] 范围内的随机数
        return String.format("%06d", randomNumber);  // 通过格式化确保输出的数字总是 6 位
    }
}
