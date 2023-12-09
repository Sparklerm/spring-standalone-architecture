package com.yiyan.boot.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Resource分页查询参数
 */
@Data
@ApiModel(value = "资源分页查询参数")
public class ResourcePageRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "资源分类ID")
    private Long categoryId;

    @ApiModelProperty(value = "资源名称")
    private String name;

    @ApiModelProperty(value = "资源URL")
    private String url;

    @ApiModelProperty(value = "每页条数")
    private Integer pageSize;

    @ApiModelProperty(value = "页码")
    private Integer pageNum;
}
