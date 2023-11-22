package com.safecampusbackend.model.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RegisterForm {
    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String realName;

    @ApiModelProperty(value = "性别")
    private Integer gender;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "手机号")
    private Integer phone;
}
