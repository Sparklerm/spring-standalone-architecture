package com.yiyan.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yiyan.boot.common.utils.BeanCopierUtils;
import com.yiyan.boot.dao.IResourceDao;
import com.yiyan.boot.model.dto.ResourceDTO;
import com.yiyan.boot.model.po.ResourcePO;
import com.yiyan.boot.service.IResourceService;
import org.apache.commons.lang3.ObjectUtils;
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

    @Override
    public int create(ResourceDTO resourceDTO) {
        ResourcePO resource = BeanCopierUtils.copyProperties(resourceDTO, ResourcePO.class);
        return resourceDao.insert(resource);
    }

    @Override
    public int update(ResourceDTO resourceDTO) {
        ResourcePO resource = BeanCopierUtils.copyProperties(resourceDTO, ResourcePO.class);
        return resourceDao.updateById(resource);
    }

    @Override
    public ResourceDTO getItem(Long id) {
        ResourcePO resource = resourceDao.selectById(id);
        return BeanCopierUtils.copyProperties(resource, ResourceDTO.class);
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

        return resourcePage.getRecords().stream()
                .map(po -> BeanCopierUtils.copyProperties(po, ResourceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ResourceDTO> listAll() {
        return resourceDao.selectAll().stream()
                .map(po -> BeanCopierUtils.copyProperties(po, ResourceDTO.class))
                .collect(Collectors.toList());
    }
}
