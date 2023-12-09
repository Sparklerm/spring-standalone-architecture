package ${groupId}.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ${groupId}.model.po.RoleResourceRelationPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IRoleResourceRelationDao extends BaseMapper<RoleResourceRelationPO> {

    /**
     * 删除角色资源关系
     *
     * @param roleId     角色ID
     * @param resourceId 资源ID
     * @return 删除结果
     */
    Integer delete(@Param("roleId") Long roleId, @Param("resourceId") Long resourceId);
}




