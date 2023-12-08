package com.yiyan.boot.dao.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yiyan.boot.dao.auth.po.UserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IUserDao extends BaseMapper<UserPO> {

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    UserPO selectByUsername(@Param("username") String username);
}




