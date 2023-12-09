package com.yiyan.boot.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Alex Meng
 * @createDate 2023-11-23 11:19
 */
@ApiModel(value = "CurrentUserVO", description = "当前登录用户信息")
@Data
public class UserVO implements Serializable {

    @ApiModelProperty(value = "用户ID")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "备注")
    private String note;

    @ApiModelProperty(value = "账号启用状态：0->禁用；1->启用")
    private Integer status;

    @ApiModelProperty(value = "最后登录时间")
    private LocalDateTime loginTime;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
