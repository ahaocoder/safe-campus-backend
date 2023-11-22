package com.safecampusbackend.model.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class RegisterForm {
    @ApiModelProperty(value = "用户名")
    @NotNull(message = "用户名不得为空")
    @Size(min = 3, max = 10, message = "用户名不得小于3位以及大于10位")
    private String username;

    @ApiModelProperty(value = "密码")
    @NotNull(message = "密码不得为空")
    @Size(min = 1, max = 16, message = "密码不得小于1位以及大于16位")
    private String password;

    @ApiModelProperty(value = "昵称")
    @NotNull(message = "昵称不得为空")
    @Size(min = 1, max = 10, message = "用户名不得小于1位以及大于10位")
    private String realName;

    @ApiModelProperty(value = "性别")
    private Integer gender;

    @ApiModelProperty(value = "邮箱")
    @NotNull(message = "邮箱不得为空")
    @Email(message = "输入正确的邮箱格式")
    private String email;

    @ApiModelProperty(value = "手机号")
    private String phone;
}
