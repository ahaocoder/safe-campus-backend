package com.safecampusbackend.model.dto;

import com.safecampusbackend.model.form.RegisterForm;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RegisterDTO {
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
    private String phone;

    /**
     * 入参转换为 DTO
     *
     * @param form 前端入参的 RegisterForm
     * @return RegisterDTO 数据传输对象
     */
    public static RegisterDTO toDTO(RegisterForm form){
        RegisterDTO dto = new RegisterDTO();
        dto.setUsername(form.getUsername());
        dto.setPassword(form.getPassword());
        dto.setRealName(form.getRealName());
        dto.setGender(form.getGender());
        dto.setEmail(form.getEmail());
        dto.setPhone(form.getPhone());
        return dto;
    }
}
