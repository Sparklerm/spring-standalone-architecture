package ${groupId}.service.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${groupId}.common.constant.RedisCacheKey;
import ${groupId}.common.utils.ObjectUtils;
import ${groupId}.dao.auth.dao.IResourceDao;
import ${groupId}.dao.auth.po.ResourcePO;
import ${groupId}.service.auth.model.ResourceDTO;
import ${groupId}.service.auth.service.IResourceService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alex Meng
 * @createDate 2023-11-21 0021 上午 01:59
 */
@Service
public class ResourceServiceImpl implements IResourceService {

    @Resource
    private IResourceDao IResourceDao;

    @NotNull
    private static ResourcePO dtoToPo(ResourceDTO resourceDTO) {
        ResourcePO resourcePO = new ResourcePO();
        resourcePO.setId(resourceDTO.getId());
        resourcePO.setName(resourceDTO.getName());
        resourcePO.setUrl(resourceDTO.getUrl());
        resourcePO.setDescription(resourceDTO.getDescription());
        resourcePO.setCategoryId(resourceDTO.getCategoryId());
        return resourcePO;
    }

    @NotNull
    private static ResourceDTO poToDto(ResourcePO resourcePO) {
        ResourceDTO resourceDTO = new ResourceDTO();
        resourceDTO.setId(resourcePO.getId());
        resourceDTO.setName(resourcePO.getName());
        resourceDTO.setUrl(resourcePO.getUrl());
        resourceDTO.setDescription(resourcePO.getDescription());
        resourceDTO.setCategoryId(resourcePO.getCategoryId());
        return resourceDTO;
    }

    @Override
    public int create(ResourceDTO resourceDTO) {
        ResourcePO resourcePO = dtoToPo(resourceDTO);
        return IResourceDao.insert(resourcePO);
    }

    @Override
    public int update(ResourceDTO resourceDTO) {
        ResourcePO resourcePO = dtoToPo(resourceDTO);
        return IResourceDao.updateById(resourcePO);
    }

    @Override
    public ResourceDTO getItem(Long id) {
        ResourcePO resourcePO = IResourceDao.selectById(id);
        return poToDto(resourcePO);
    }

    @Override
    public int delete(Long id) {
        return IResourceDao.deleteById(id);
    }

    @Override
    public List<ResourceDTO> list(Long categoryId, String nameKeyword, String url, Integer pageSize, Integer pageNum) {
        QueryWrapper<ResourcePO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ObjectUtils.isNotEmpty(categoryId), "category_id", categoryId);
        queryWrapper.like(ObjectUtils.isNotEmpty(nameKeyword), "name", nameKeyword);
        queryWrapper.like(ObjectUtils.isNotEmpty(url), "url", url);
        Page<ResourcePO> page = new Page<>(pageNum, pageSize);
        page.setSearchCount(false);
        Page<ResourcePO> resourcePOPage = IResourceDao.selectPage(page, queryWrapper);

        return resourcePOPage.getRecords().stream().map(ResourceServiceImpl::poToDto).collect(Collectors.toList());
    }

    @Override
    public List<ResourceDTO> listAll() {
        return IResourceDao.selectAll().stream().map(ResourceServiceImpl::poToDto).collect(Collectors.toList());
    }
}
