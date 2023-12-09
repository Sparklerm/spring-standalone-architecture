package com.yiyan.boot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yiyan.boot.model.po.UserRoleRelationPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IUserRoleRelationDao extends BaseMapper<UserRoleRelationPO> {

    /**
     * 删除用户角色关系
     *
     * @param userId 用户ID
     * @param roleId 角色ID
     * @return 删除结果
     */
    Integer delete(@Param("userId") Long userId, @Param("roleId") Long roleId);
}




