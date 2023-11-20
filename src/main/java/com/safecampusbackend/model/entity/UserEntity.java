package com.safecampusbackend.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user")
@ApiModel(value = "用户信息", description = "用于表示系统中的用户信息")
public class UserEntity {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键ID")
    private Long id;

    @TableField("username")
    @ApiModelProperty(value = "用户名")
    private String username;

    @TableField("password")
    @ApiModelProperty(value = "密码")
    private String password;

    @TableField("salt")
    @ApiModelProperty(value = "盐")
    private String salt;

    @TableField("real_name")
    @ApiModelProperty(value = "昵称")
    private String realName;

    @TableField("gender")
    @ApiModelProperty(value = "性别")
    private Integer gender;

    @TableField("status")
    @ApiModelProperty(value = "账号状态")
    private Integer status;

    @TableField("email")
    @ApiModelProperty(value = "邮箱")
    private String email;

    @TableField("phone")
    @ApiModelProperty(value = "手机号")
    private Integer phone;

    @TableField("update_user")
    @ApiModelProperty(value = "更新者")
    private String updateUser;

    @TableField("update_date")
    @ApiModelProperty(value = "更新时间")
    private Date updateDate;

    @TableField("update_log")
    @ApiModelProperty(value = "更新日志")
    private String updateLog;
}
