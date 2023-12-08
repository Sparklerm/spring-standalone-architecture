package ${groupId}.web.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Alex Meng
 * @createDate 2023-11-23 12:01
 */
@Data
@ApiModel(value = "用户-角色操作请求参数")
public class UserRoleRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "角色ID列表")
    private List<Long> roleIds;
}
