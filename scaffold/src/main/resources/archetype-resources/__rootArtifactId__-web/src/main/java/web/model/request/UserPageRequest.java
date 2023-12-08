package ${groupId}.web.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Alex Meng
 * @createDate 2023-11-23 0023 上午 11:30
 */
@Data
@ApiModel(value = "用户分页查询请求参数")
public class UserPageRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("页码")
    private Integer pageNum;

    @ApiModelProperty("每页条数")
    private Integer pageSize;
}
