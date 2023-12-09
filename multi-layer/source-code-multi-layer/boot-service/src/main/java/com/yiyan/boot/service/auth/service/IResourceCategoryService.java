package com.yiyan.boot.service.auth.service;

import com.yiyan.boot.service.auth.model.ResourceCategoryDTO;

import java.util.List;

/**
 * 后端资源分类Service
 *
 * @author Alex Meng
 * @createDate 2023-11-23 02:14
 */
public interface IResourceCategoryService {

    /**
     * 查询所有后台资源分类
     */
    List<ResourceCategoryDTO> listAll();

    /**
     * 添加后台资源分类
     *
     * @param name 分类名称
     */
    Integer create(String name);

    /**
     * 修改后台资源分类
     *
     * @param id   分类ID
     * @param name 分类名称
     * @return 修改成功条数
     */
    Integer update(Long id, String name);

    /**
     * 根据ID删除后台资源分类
     *
     * @param id 分类ID
     * @return 删除成功条数
     */
    Integer deleteById(Long id);
}
