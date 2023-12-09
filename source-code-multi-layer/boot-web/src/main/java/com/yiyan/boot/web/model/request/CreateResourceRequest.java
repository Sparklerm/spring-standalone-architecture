package com.yiyan.boot.web.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 创建资源请求参数
 */
@Data
@ApiModel(value = "创建资源请求参数")
public class CreateResourceRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "资源名称")
    @NotBlank(message = "资源名称不能为空")
    private String name;

    @ApiModelProperty(value = "资源URL")
    @NotBlank(message = "资源URL不能为空")
    private String url;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "资源分类ID")
    @NotNull(message = "资源分类ID不能为空")
    private Long categoryId;
}
