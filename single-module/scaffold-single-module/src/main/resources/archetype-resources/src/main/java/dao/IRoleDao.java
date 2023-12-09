package ${groupId}.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ${groupId}.model.po.ResourcePO;
import ${groupId}.model.po.RolePO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IRoleDao extends BaseMapper<RolePO> {

    /**
     * 查询所有角色
     */
    List<RolePO> selectAll();


    /**
     * 根据用户ID查询角色
     *
     * @return 角色列表
     */
    List<RolePO> selectByUserId(@Param("userId") Long userId);

    /**
     * 根据角色ID查询资源
     *
     * @param roleId 角色ID
     * @return 资源列表
     */
    List<ResourcePO> selectResourceListByRoleId(@Param("roleId") Long roleId);

    /**
     * 查询默认角色
     *
     * @return 角色列表
     */
    List<RolePO> selectDefaultRole();
}




