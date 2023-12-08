package com.yiyan.boot.service.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yiyan.boot.dao.auth.dao.IResourceDao;
import com.yiyan.boot.dao.auth.po.ResourcePO;
import com.yiyan.boot.service.auth.model.ResourceDTO;
import com.yiyan.boot.service.auth.service.IResourceService;
import org.apache.commons.lang3.ObjectUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alex Meng
 * @createDate 2023-11-21 01:59
 */
@Service
public class ResourceServiceImpl implements IResourceService {

    @Resource
    private IResourceDao resourceDao;

    @NotNull
    private static ResourcePO dtoToPo(ResourceDTO resourceDTO) {
        ResourcePO resource = new ResourcePO();
        resource.setId(resourceDTO.getId());
        resource.setName(resourceDTO.getName());
        resource.setUrl(resourceDTO.getUrl());
        resource.setDescription(resourceDTO.getDescription());
        resource.setCategoryId(resourceDTO.getCategoryId());
        return resource;
    }

    @NotNull
    private static ResourceDTO poToDto(ResourcePO resource) {
        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setId(resource.getId());
        resourceDTO.setName(resource.getName());
        resourceDTO.setUrl(resource.getUrl());
        resourceDTO.setDescription(resource.getDescription());
        resourceDTO.setCategoryId(resource.getCategoryId());
        return resourceDTO;
    }

    @Override
    public int create(ResourceDTO resourceDTO) {
        ResourcePO resource = dtoToPo(resourceDTO);
        return resourceDao.insert(resource);
    }

    @Override
    public int update(ResourceDTO resourceDTO) {
        ResourcePO resource = dtoToPo(resourceDTO);
        return resourceDao.updateById(resource);
    }

    @Override
    public ResourceDTO getItem(Long id) {
        ResourcePO resource = resourceDao.selectById(id);
        return poToDto(resource);
    }

    @Override
    public int delete(Long id) {
        return resourceDao.deleteById(id);
    }

    @Override
    public List<ResourceDTO> list(Long categoryId, String nameKeyword, String url, Integer pageSize, Integer pageNum) {
        QueryWrapper<ResourcePO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ObjectUtils.isNotEmpty(categoryId), "category_id", categoryId);
        queryWrapper.like(ObjectUtils.isNotEmpty(nameKeyword), "name", nameKeyword);
        queryWrapper.like(ObjectUtils.isNotEmpty(url), "url", url);
        Page<ResourcePO> page = new Page<>(pageNum, pageSize);
        page.setSearchCount(false);
        Page<ResourcePO> resourcePage = resourceDao.selectPage(page, queryWrapper);

        return resourcePage.getRecords().stream().map(ResourceServiceImpl::poToDto).collect(Collectors.toList());
    }

    @Override
    public List<ResourceDTO> listAll() {
        return resourceDao.selectAll().stream().map(ResourceServiceImpl::poToDto).collect(Collectors.toList());
    }
}
