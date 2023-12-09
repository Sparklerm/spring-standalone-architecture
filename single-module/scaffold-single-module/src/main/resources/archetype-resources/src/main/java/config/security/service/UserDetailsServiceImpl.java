package ${groupId}.config.security.service;

import ${groupId}.common.constant.NumberConstant;
import ${groupId}.common.constant.RedisCacheKey;
import ${groupId}.common.enums.BizCodeEnum;
import ${groupId}.common.exception.BizAssert;
import ${groupId}.common.utils.BeanCopierUtils;
import ${groupId}.common.utils.StrUtils;
import ${groupId}.common.utils.redis.RedisService;
import ${groupId}.dao.IRoleDao;
import ${groupId}.dao.IUserDao;
import ${groupId}.model.po.ResourcePO;
import ${groupId}.model.po.RolePO;
import ${groupId}.model.po.UserPO;
import ${groupId}.model.dto.AuthUserDetails;
import ${groupId}.model.dto.ResourceDTO;
import ${groupId}.model.dto.RoleDTO;
import ${groupId}.model.dto.UserDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private IUserDao userDao;
    @Resource
    private IRoleDao roleDao;
    @Resource
    private RedisService redisService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询用户信息
        UserDTO userInfo = redisService.getString(StrUtils.format(RedisCacheKey.User.USER_INFO, username));
        if (Objects.isNull(userInfo)) {
            UserPO userPO = userDao.selectByUsername(username);
            userInfo = BeanCopierUtils.copyProperties(userPO, UserDTO.class);
            redisService.setString(StrUtils.format(RedisCacheKey.User.USER_INFO, username),
                    userInfo,
                    NumberConstant.TWELVE_HOURS,
                    TimeUnit.SECONDS);
        }
        BizAssert.notNull(userInfo, BizCodeEnum.USER_NOT_EXIST);
        // 查询用户拥有的资源
        List<ResourceDTO> resourceDTOS = redisService.getString(StrUtils.format(RedisCacheKey.User.USER_PERMISSION, username));
        if (CollectionUtils.isEmpty(resourceDTOS)) {
            // 查询用户拥有的角色
            List<RolePO> rolePOS = roleDao.selectByUserId(userInfo.getId());
            List<RoleDTO> roleDTOS = BeanCopierUtils.copyListProperties(rolePOS, RoleDTO.class);
            // 查询角色拥有的资源
            List<ResourceDTO> resourceList = new ArrayList<>();
            for (RoleDTO rolePO : roleDTOS) {
                List<ResourcePO> resourcePOS = roleDao.selectResourceListByRoleId(rolePO.getId());
                List<ResourceDTO> resourceDTOList = BeanCopierUtils.copyListProperties(resourcePOS, ResourceDTO.class);
                resourceList.addAll(resourceDTOList);
            }
            resourceDTOS = resourceList;
            redisService.setString(StrUtils.format(RedisCacheKey.User.USER_PERMISSION, username),
                    resourceDTOS,
                    NumberConstant.TWELVE_HOURS,
                    TimeUnit.SECONDS);
        }
        return new AuthUserDetails(userInfo, resourceDTOS);
    }
}
