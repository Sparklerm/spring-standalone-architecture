package com.yiyan.boot.service.auth.service;

import com.yiyan.boot.service.auth.model.ResourceDTO;

import java.util.List;

/**
 * 资源管理Service
 *
 * @author Alex Meng
 * @createDate 2023-11-23 02:14
 */
public interface IResourceService {
    /**
     * 添加资源
     */
    int create(ResourceDTO resourceDTO);

    /**
     * 修改资源
     */
    int update(ResourceDTO resourceDTO);

    /**
     * 获取资源详情
     */
    ResourceDTO getItem(Long id);

    /**
     * 删除资源
     */
    int delete(Long id);

    /**
     * 分页查询资源
     */
    List<ResourceDTO> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum);

    /**
     * 查询全部资源
     */
    List<ResourceDTO> listAll();
}
