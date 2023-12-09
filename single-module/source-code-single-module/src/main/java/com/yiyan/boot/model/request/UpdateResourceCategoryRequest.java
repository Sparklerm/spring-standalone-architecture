package com.yiyan.boot.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Alex Meng
 * @createDate 2023-11-21 21:05
 */
@ApiModel("修改后台资源分类")
@Data
public class UpdateResourceCategoryRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("分类ID")
    @NotNull(message = "分类ID不能为空")
    private Long id;

    @ApiModelProperty("分类名称")
    @NotBlank(message = "分类名称不能为空")
    private String name;

}
