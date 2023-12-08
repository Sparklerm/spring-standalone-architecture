package com.yiyan.boot.service.auth.service;

import com.yiyan.boot.service.auth.model.ResourceDTO;
import com.yiyan.boot.service.auth.model.RoleDTO;
import com.yiyan.boot.service.auth.model.UserDTO;
import com.yiyan.boot.service.auth.model.UserLoginResultDTO;

import java.util.List;

/**
 * 用户管理Service
 *
 * @author Alex Meng
 * @createDate 2023-11-23 03:32
 */
public interface IUserService {
    /**
     * 创建用户
     *
     * @param userDTO 用户信息
     * @return 创建结果
     */
    UserDTO register(UserDTO userDTO);

    /**
     * 密码登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     */
    UserLoginResultDTO loginByPassword(String username, String password);

    /**
     * 根据用户名查询用户信息
     */
    UserDTO selectByUsername(String username);

    /**
     * 根据用户ID查询用户拥有的资源
     *
     * @param id 用户ID
     * @return 资源列表
     */
    List<ResourceDTO> selectResourceByUserId(Long id);

    /**
     * 登出
     *
     * @param username 用户名
     */
    void logout(String username);

    /**
     * 分页查询用户列表
     *
     * @param pageNum  当前页
     * @param pageSize 每页显示条数
     * @param userDTO  查询条件
     * @return 用户列表
     */
    List<UserDTO> list(Integer pageNum, Integer pageSize, UserDTO userDTO);

    /**
     * 根据ID查询用户信息
     *
     * @param id 用户ID
     * @return 用户信息
     */
    UserDTO selectById(Long id);

    /**
     * 更新用户信息
     *
     * @param userDTO 用户信息
     * @return 更新结果
     */
    Integer update(UserDTO userDTO);

    /**
     * 修改密码
     *
     * @param id          用户ID
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 修改结果
     */
    Integer updatePassword(Long id, String oldPassword, String newPassword);

    /**
     * 删除用户
     *
     * @param id 用户ID
     * @return 删除结果
     */
    Integer delete(Long id);

    /**
     * 用户绑定角色
     *
     * @param userId  用户ID
     * @param roleIds 角色ID列表
     * @return 绑定结果
     */
    Integer updateHasRole(Long userId, List<Long> roleIds);

    /**
     * 查询用户拥有的角色
     *
     * @param id 用户ID
     * @return 角色列表
     */
    List<RoleDTO> selectUserRoles(Long id);
}
