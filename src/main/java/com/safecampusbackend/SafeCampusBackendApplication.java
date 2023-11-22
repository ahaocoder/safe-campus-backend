package com.safecampusbackend;

import cn.hutool.extra.mail.MailUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.safecampusbackend.model.mapper")
public class SafeCampusBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SafeCampusBackendApplication.class, args);
        MailUtil.send("yohoozhao@163.com", "测试邮件工具", "Hello World", false);
        System.out.println("------------------------安全校园，启动！------------------------");
    }

}
