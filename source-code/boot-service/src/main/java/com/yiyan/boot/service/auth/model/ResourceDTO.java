package com.yiyan.boot.service.auth.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Alex Meng
 * @createDate 2023-11-2 20:31
 */
@Data
public class ResourceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "资源名称")
    private String name;

    @ApiModelProperty(value = "资源URL")
    private String url;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "资源分类ID")
    private Long categoryId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
}
