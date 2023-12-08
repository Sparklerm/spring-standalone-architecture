package ${groupId}.web.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Alex Meng
 * @createDate 2023-11-21 0021 下午 08:45
 */
@Data
@ApiModel("创建后台资源分类")
public class CreateResourceCategoryRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("资源分类名称")
    @NotBlank(message = "资源分类名称不能为空")
    private String name;
}
