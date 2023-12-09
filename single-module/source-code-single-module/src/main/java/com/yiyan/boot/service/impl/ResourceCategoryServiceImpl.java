package com.yiyan.boot.service.impl;

import com.yiyan.boot.common.exception.BizAssert;
import com.yiyan.boot.common.utils.BeanCopierUtils;
import com.yiyan.boot.dao.IResourceCategoryDao;
import com.yiyan.boot.model.dto.ResourceCategoryDTO;
import com.yiyan.boot.model.po.ResourceCategoryPO;
import com.yiyan.boot.service.IResourceCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alex Meng
 * @createDate 2023-11-21 20:32
 */
@Service
public class ResourceCategoryServiceImpl implements IResourceCategoryService {

    @Resource
    private IResourceCategoryDao resourceCategoryDao;

    @Override
    public List<ResourceCategoryDTO> listAll() {
        return resourceCategoryDao.selectAll()
                .stream()
                .map(po -> BeanCopierUtils.copyProperties(po, ResourceCategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Integer create(String name) {
        ResourceCategoryPO esxiRc = resourceCategoryDao.selectByName(name);
        BizAssert.isNull(esxiRc, "分类名称已存在");
        ResourceCategoryPO category = ResourceCategoryPO.builder().name(name).build();
        return resourceCategoryDao.insert(category);
    }

    @Override
    public Integer update(Long id, String name) {
        return resourceCategoryDao.updateById(ResourceCategoryPO.builder().id(id).name(name).build());
    }

    @Override
    public Integer deleteById(Long id) {
        return resourceCategoryDao.deleteById(id);
    }
}
