package com.safecampusbackend.model.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CodeEntity {
    @ApiModelProperty(value = "验证码")
    private String code;
    @ApiModelProperty(value = "唯一标识符")
    private String uuid;
}
