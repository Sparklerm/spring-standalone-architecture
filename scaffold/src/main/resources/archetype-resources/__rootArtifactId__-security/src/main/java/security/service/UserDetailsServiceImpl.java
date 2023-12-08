package ${groupId}.security.service;

import ${groupId}.common.constant.NumberConstant;
import ${groupId}.common.constant.RedisCacheKey;
import ${groupId}.common.enums.BizCodeEnum;
import ${groupId}.common.exception.BizAssert;
import ${groupId}.common.utils.BeanCopierUtils;
import ${groupId}.common.utils.CollectionUtils;
import ${groupId}.common.utils.StringUtils;
import ${groupId}.common.utils.redis.RedisService;
import ${groupId}.dao.auth.dao.IRoleDao;
import ${groupId}.dao.auth.dao.IUserDao;
import ${groupId}.dao.auth.po.ResourcePO;
import ${groupId}.dao.auth.po.RolePO;
import ${groupId}.dao.auth.po.UserPO;
import ${groupId}.service.auth.model.AuthUserDetails;
import ${groupId}.service.auth.model.ResourceDTO;
import ${groupId}.service.auth.model.UserDTO;
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
        UserDTO userInfo = redisService.getString(StringUtils.format(RedisCacheKey.USER_INFO, username));
        if (Objects.isNull(userInfo)) {
            UserPO userPO = userDao.selectByUsername(username);
            userInfo = BeanCopierUtils.copyProperties(userPO, UserDTO.class);
            redisService.setString(StringUtils.format(RedisCacheKey.USER_INFO, username),
                    userInfo,
                    NumberConstant.TWELVE_HOURS,
                    TimeUnit.SECONDS);
        }
        BizAssert.notNull(userInfo, BizCodeEnum.USER_NOT_EXIST);
        // 查询用户拥有的资源
        List<ResourceDTO> resourceDTOS = redisService.getString(StringUtils.format(RedisCacheKey.USER_PERMISSION, username));
        if (CollectionUtils.isEmpty(resourceDTOS)) {
            // 查询用户拥有的角色
            List<RolePO> rolePOS = roleDao.selectByUserId(userInfo.getId());
            // 查询角色拥有的资源
            List<ResourcePO> resourceList = new ArrayList<>();
            for (RolePO rolePO : rolePOS) {
                List<ResourcePO> resourcePOS = roleDao.selectResourceListByRoleId(rolePO.getId());
                resourceList.addAll(resourcePOS);
            }
            resourceDTOS = BeanCopierUtils.copyListProperties(resourceList, ResourceDTO.class);
            redisService.setString(StringUtils.format(RedisCacheKey.USER_PERMISSION, username),
                    resourceDTOS,
                    NumberConstant.TWELVE_HOURS,
                    TimeUnit.SECONDS);
        }
        return new AuthUserDetails(userInfo, resourceDTOS);
    }
}
