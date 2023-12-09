package com.yiyan.boot.config.security.service;

import com.yiyan.boot.common.constant.NumberConstant;
import com.yiyan.boot.common.constant.RedisCacheKey;
import com.yiyan.boot.common.enums.BizCodeEnum;
import com.yiyan.boot.common.exception.BizAssert;
import com.yiyan.boot.common.utils.BeanCopierUtils;
import com.yiyan.boot.common.utils.StrUtils;
import com.yiyan.boot.common.utils.redis.RedisService;
import com.yiyan.boot.dao.IRoleDao;
import com.yiyan.boot.dao.IUserDao;
import com.yiyan.boot.model.dto.AuthUserDetails;
import com.yiyan.boot.model.dto.ResourceDTO;
import com.yiyan.boot.model.dto.RoleDTO;
import com.yiyan.boot.model.dto.UserDTO;
import com.yiyan.boot.model.po.ResourcePO;
import com.yiyan.boot.model.po.RolePO;
import com.yiyan.boot.model.po.UserPO;
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
