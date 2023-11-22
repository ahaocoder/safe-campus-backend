package com.safecampusbackend.util.result;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Result<T> {
    @ApiModelProperty(value = "响应码")
    private Integer code;
    @ApiModelProperty(value = "响应消息")
    private String msg;
    @ApiModelProperty(value = "响应数据")
    private T data;

    public Result(int code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
