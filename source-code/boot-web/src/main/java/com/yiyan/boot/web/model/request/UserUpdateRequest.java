package com.yiyan.boot.web.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Alex Meng
 * @createDate 2023-11-23 11:41
 */
@Data
@ApiModel("更新用户信息请求参数")
public class UserUpdateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("备注")
    private String note;

    @ApiModelProperty("账号启用状态：0->禁用；1->启用")
    private Integer status;
}
