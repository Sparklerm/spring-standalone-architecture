package ${groupId}.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alex Meng
 * @createDate 2023-11-23 03:10
 */
@ApiModel("角色-资源操作请求参数")
@Data
public class RoleResourceRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色ID")
    private Long roleId;

    @ApiModelProperty("待操作资源ID集合")
    private List<Long> resourceIds;
}
