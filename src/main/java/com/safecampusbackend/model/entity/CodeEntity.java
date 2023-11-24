package com.safecampusbackend.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CodeEntity {
    @ApiModelProperty(value = "验证码")
    @NotNull(message = "验证码不得为空")
    private String code;
    @ApiModelProperty(value = "唯一标识符")
    @NotNull(message = "唯一标识符不得为空")
    private String uuid;
}
