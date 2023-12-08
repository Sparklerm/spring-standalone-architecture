package ${groupId}.web.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Alex Meng
 * @createDate 2023-11-23 02:17
 */
@ApiModel("创建角色请求参数")
@Data
public class RoleCreateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色名称")
    @NotBlank(message = "角色名称不能为空")
    private String name;

    @ApiModelProperty("角色描述")
    private String description;
}
