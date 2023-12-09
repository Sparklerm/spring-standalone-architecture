package ${groupId}.service.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${groupId}.common.utils.BeanCopierUtils;
import ${groupId}.dao.auth.dao.IRoleDao;
import ${groupId}.dao.auth.dao.IRoleResourceRelationDao;
import ${groupId}.dao.auth.po.ResourcePO;
import ${groupId}.dao.auth.po.RolePO;
import ${groupId}.dao.auth.po.RoleResourceRelationPO;
import ${groupId}.service.auth.model.ResourceDTO;
import ${groupId}.service.auth.model.RoleDTO;
import ${groupId}.service.auth.model.RoleUpdateDTO;
import ${groupId}.service.auth.service.IRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Alex Meng
 * @createDate 2023-11-23 02:14
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Resource
    private IRoleDao roleDao;
    @Resource
    private IRoleResourceRelationDao roleResourceRelationDao;

    @Override
    public Integer create(String name, String description) {
        RolePO role = new RolePO();
        role.setName(name);
        role.setDescription(description);
        return roleDao.insert(role);
    }

    @Override
    public Integer update(RoleUpdateDTO roleUpdateDTO) {
        RolePO role = BeanCopierUtils.copyProperties(roleUpdateDTO, RolePO.class);
        return roleDao.updateById(role);
    }

    @Override
    public Integer delete(List<Long> ids) {
        return roleDao.deleteBatchIds(ids);
    }

    @Override
    public List<RoleDTO> listAll() {
        List<RolePO> rolePOS = roleDao.selectAll();
        return BeanCopierUtils.copyListProperties(rolePOS, RoleDTO.class);
    }

    @Override
    public List<RoleDTO> list(Integer pageNum, Integer pageSize, RoleDTO roleDTO) {
        Page<RolePO> page = new Page<>(pageNum, pageSize);
        page.setSearchCount(false);

        QueryWrapper<RolePO> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(roleDTO.getName()), "name", roleDTO.getName());
        queryWrapper.like(StringUtils.isNotBlank(roleDTO.getDescription()), "description", roleDTO.getDescription());

        Page<RolePO> rolePage = roleDao.selectPage(page, queryWrapper);
        return BeanCopierUtils.copyListProperties(rolePage.getRecords(), RoleDTO.class);
    }

    @Override
    public Integer update(RoleDTO roleDTO) {
        RolePO role = BeanCopierUtils.copyProperties(roleDTO, RolePO.class);
        return roleDao.updateById(role);
    }

    @Override
    public List<ResourceDTO> listResource(Long roleId) {
        List<ResourcePO> resourcePOS = roleDao.selectResourceListByRoleId(roleId);
        return BeanCopierUtils.copyListProperties(resourcePOS, ResourceDTO.class);
    }

    @Override
    public Integer updateHasResource(Long roleId, List<Long> resourceIds) {
        // 查询角色已有资源
        List<Long> existResourceIds = roleResourceRelationDao
                .selectList(new QueryWrapper<RoleResourceRelationPO>().eq("role_id", roleId))
                .stream()
                .map(RoleResourceRelationPO::getResourceId)
                .collect(Collectors.toList());
        // 待绑定资源
        List<Long> addResourceIds = resourceIds.stream().filter(resourceId -> !existResourceIds.contains(resourceId)).collect(Collectors.toList());
        // 待解绑资源
        List<Long> removeResourceIds = existResourceIds.stream().filter(resourceId -> !resourceIds.contains(resourceId)).collect(Collectors.toList());
        // 绑定资源
        for (Long resourceId : addResourceIds) {
            RoleResourceRelationPO roleResourceRelation = new RoleResourceRelationPO();
            roleResourceRelation.setRoleId(roleId);
            roleResourceRelation.setResourceId(resourceId);
            roleResourceRelationDao.insert(roleResourceRelation);
        }
        // 解绑资源
        for (Long resourceId : removeResourceIds) {
            roleResourceRelationDao.delete(roleId, resourceId);
        }
        return resourceIds.size();
    }
}
