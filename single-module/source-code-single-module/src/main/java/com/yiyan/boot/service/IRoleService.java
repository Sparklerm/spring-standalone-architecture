package com.yiyan.boot.service;

import com.yiyan.boot.model.dto.ResourceDTO;
import com.yiyan.boot.model.dto.RoleDTO;
import com.yiyan.boot.model.dto.RoleUpdateDTO;

import java.util.List;

/**
 * 角色管理Service
 *
 * @author Alex Meng
 * @createDate 2023-11-23 02:14
 */
public interface IRoleService {
    /**
     * 创建角色
     *
     * @param name        角色名称
     * @param description 角色描述
     * @return 角色ID
     */
    Integer create(String name, String description);

    /**
     * 根据ID修改角色
     *
     * @param roleUpdateDTO 角色信息
     * @return 修改结果
     */
    Integer update(RoleUpdateDTO roleUpdateDTO);

    /**
     * 批量删除角色
     *
     * @param ids 角色ID集合
     * @return 删除结果
     */
    Integer delete(List<Long> ids);

    /**
     * 查询所有角色
     *
     * @return 角色列表
     */
    List<RoleDTO> listAll();

    /**
     * 分页查询角色列表
     *
     * @param pageNum  页码
     * @param pageSize 每页条数
     * @param roleDTO  查询条件
     * @return 角色列表
     */
    List<RoleDTO> list(Integer pageNum, Integer pageSize, RoleDTO roleDTO);

    /**
     * 根据ID修改角色状态
     *
     * @param roleDTO 角色信息
     * @return 修改结果
     */
    Integer update(RoleDTO roleDTO);

    /**
     * 根据角色ID查询资源列表
     *
     * @param roleId 角色ID
     * @return 资源列表
     */
    List<ResourceDTO> listResource(Long roleId);

    /**
     * 给角色分配资源
     *
     * @param roleId      角色ID
     * @param resourceIds 资源ID集合
     * @return 分配结果
     */
    Integer updateHasResource(Long roleId, List<Long> resourceIds);
}
