package com.yiyan.boot.web.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Alex Meng
 * @createDate 2023-11-23 02:24
 */
@ApiModel("修改角色请求参数")
@Data
public class RoleUpdateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("角色描述")
    private String description;

    @ApiModelProperty("启用状态：0->禁用；1->启用")
    private Integer status;

    @ApiModelProperty("是否为用户注册时绑定的默认角色：0->不是；1->是")
    private Integer isDefault;
}
