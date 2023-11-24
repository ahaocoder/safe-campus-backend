package com.safecampusbackend.service.impl;

import cn.hutool.extra.mail.MailUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.safecampusbackend.model.dto.RegisterDTO;
import com.safecampusbackend.model.entity.CodeEntity;
import com.safecampusbackend.model.entity.UserEntity;
import com.safecampusbackend.model.mapper.UserMapper;
import com.safecampusbackend.service.RegisterService;
import com.safecampusbackend.util.SaltUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
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
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(dto, userEntity);

        //产盐
        String salt = SaltUtil.generateSalt();
        userEntity.setSalt(salt);
        // 加盐
        String password = SaltUtil.generateAddSalt(userEntity.getPassword(), salt);
        userEntity.setPassword(password);

        // 账号状态默认开启
        userEntity.setStatus(1);

        try {
            userMapper.insert(userEntity);
        } catch (Exception e) {
            return false;
        }
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

    // 验证码验证实现
    public Boolean isCodeValid(CodeEntity codeEntity) {
        // 从 Redis 中获取存储的验证码
        String storedCode = getCodeFromRedis(codeEntity.getUuid());

        // 验证验证码是否正确
        return storedCode != null && storedCode.equals(codeEntity.getCode());
    }

    // 从 redis 中取验证码
    private String getCodeFromRedis(String uuid) {
        // 构造 Redis 中存储验证码的 key
        String key = "verification_code:" + uuid;

        // 从 Redis 中获取验证码
        return redisTemplate.opsForValue().get(key);
    }

    // 检验用户名是否重复实现
    public Boolean isUsernameDuplicate(String username) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        UserEntity userEntity = userMapper.selectOne(queryWrapper);
        return userEntity != null;
    }
}
