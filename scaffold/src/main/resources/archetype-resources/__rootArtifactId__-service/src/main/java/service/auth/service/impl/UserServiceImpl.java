package ${groupId}.service.auth.service.impl;

import cn.hutool.core.codec.Base64;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${groupId}.common.constant.NumberConstant;
import ${groupId}.common.constant.RedisCacheKey;
import ${groupId}.common.enums.BizCodeEnum;
import ${groupId}.common.enums.YesNoEnum;
import ${groupId}.common.exception.BizAssert;
import ${groupId}.common.utils.BeanCopierUtils;
import ${groupId}.common.utils.JWTUtil;
import ${groupId}.common.utils.StringUtils;
import ${groupId}.common.utils.encrypt.AESUtil;
import ${groupId}.common.utils.json.JsonUtils;
import ${groupId}.common.utils.redis.RedisService;
import ${groupId}.dao.auth.dao.IRoleDao;
import ${groupId}.dao.auth.dao.IUserDao;
import ${groupId}.dao.auth.dao.IUserRoleRelationDao;
import ${groupId}.dao.auth.po.ResourcePO;
import ${groupId}.dao.auth.po.RolePO;
import ${groupId}.dao.auth.po.UserPO;
import ${groupId}.dao.auth.po.UserRoleRelationPO;
import ${groupId}.service.auth.model.AuthUserDetails;
import ${groupId}.service.auth.model.ResourceDTO;
import ${groupId}.service.auth.model.RoleDTO;
import ${groupId}.service.auth.model.UserDTO;
import ${groupId}.service.auth.model.UserLoginResultDTO;
import ${groupId}.service.auth.service.IUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Alex Meng
 * @createDate 2023-11-23 0023 上午 03:33
 */
@Service
public class UserServiceImpl implements IUserService {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Resource
    private IUserDao userDao;
    @Resource
    private IRoleDao roleDao;
    @Resource
    private IUserRoleRelationDao userRoleRelationDao;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private RedisService redisService;

    @Override
    public UserDTO register(UserDTO userDTO) {
        // 查询是否存在相同用户名的用户
        UserPO existUser = userDao.selectByUsername(userDTO.getUsername());
        BizAssert.isNull(existUser, BizCodeEnum.USERNAME_ALREADY_REGISTER);
        // 创建用户
        UserPO userPO = BeanCopierUtils.copyProperties(userDTO, UserPO.class);
        userPO.setStatus(YesNoEnum.YES.getKey());
        String password = passwordEncoder.encode(userDTO.getPassword());
        userPO.setPassword(password);
        userDao.insert(userPO);
        // 返回结果
        return BeanCopierUtils.copyProperties(userPO, UserDTO.class);
    }

    @Override
    public UserLoginResultDTO loginByPassword(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        // 判断是否验证成功
        BizAssert.notNull(authenticate, BizCodeEnum.USER_NOT_EXIST);
        // 在认证信息authenticate中获取登录成功后的用户信息
        AuthUserDetails userInfo = (AuthUserDetails) authenticate.getPrincipal();
        //  将当前验证信息加密后生成token
        String encodeSubject = AESUtil.encode(jwtSecret, JsonUtils.toJsonStr(userInfo.getUser()));
        String token = JWTUtil.generateToken(encodeSubject);
        // 将token存入redis，并设置过期时间用于校验
        redisService.setString(StringUtils.format(RedisCacheKey.USER_TOKEN, userInfo.getUser().getUsername()),
                token,
                NumberConstant.TWO_HOURS,
                TimeUnit.SECONDS);
        return UserLoginResultDTO.builder()
                .token(Base64.encode(token))
                .build();
    }

    @Override
    public UserDTO selectByUsername(String username) {
        return BeanCopierUtils.copyProperties(userDao.selectByUsername(username), UserDTO.class);
    }

    @Override
    public List<ResourceDTO> selectResourceByUserId(Long id) {
        // 查询用户拥有的角色
        List<RolePO> rolePOS = roleDao.selectByUserId(id);
        // 查询角色拥有的资源
        List<ResourcePO> resourceList = new ArrayList<>();
        for (RolePO rolePO : rolePOS) {
            List<ResourcePO> resourcePOS = roleDao.selectResourceListByRoleId(rolePO.getId());
            resourceList.addAll(resourcePOS);
        }
        return BeanCopierUtils.copyListProperties(resourceList, ResourceDTO.class);
    }

    @Override
    public void logout(String username) {
        // 移除redis中的token
        redisService.remove(StringUtils.format(RedisCacheKey.USER_TOKEN, username));
        // 移除redis中的用户信息
        redisService.remove(StringUtils.format(RedisCacheKey.USER_INFO, username));
        // 移除redis中的用户权限
        redisService.remove(StringUtils.format(RedisCacheKey.USER_PERMISSION, username));
    }

    @Override
    public List<UserDTO> list(Integer pageNum, Integer pageSize, UserDTO userDTO) {
        Page<UserPO> page = new Page<>(pageNum, pageSize);
        page.setSearchCount(YesNoEnum.NO.getValue());

        QueryWrapper<UserPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(userDTO.getUsername()), "username", userDTO.getUsername());
        queryWrapper.like(StringUtils.isNotBlank(userDTO.getNickName()), "nick_name", userDTO.getNickName());

        return BeanCopierUtils.copyListProperties(userDao.selectPage(page, queryWrapper).getRecords(), UserDTO.class);
    }

    @Override
    public UserDTO selectById(Long id) {
        UserPO userPO = userDao.selectById(id);
        return BeanCopierUtils.copyProperties(userPO, UserDTO.class);
    }

    @Override
    public Integer update(UserDTO userDTO) {
        UserPO userPO = BeanCopierUtils.copyProperties(userDTO, UserPO.class);
        return userDao.updateById(userPO);
    }

    @Override
    public Integer updatePassword(Long id, String oldPassword, String newPassword) {
        UserPO userPO = userDao.selectById(id);
        BizAssert.notNull(userPO, BizCodeEnum.USER_NOT_EXIST);

        // 校验旧密码是否正确
        boolean matches = passwordEncoder.matches(oldPassword, userPO.getPassword());
        BizAssert.isTrue(matches, BizCodeEnum.OLD_PASSWORD_ERROR);
        // 更新密码
        userPO.setPassword(passwordEncoder.encode(newPassword));
        // 退出登录
        logout(userPO.getUsername());
        return userDao.updateById(userPO);
    }

    @Override
    public Integer delete(Long id) {
        return userDao.deleteById(id);
    }

    @Override
    public Integer updateHasRole(Long userId, List<Long> roleIds) {
        // 查询用户已绑定角色
        List<Long> hasRoles = roleDao.selectByUserId(userId)
                .stream()
                .map(RolePO::getId)
                .collect(Collectors.toList());
        // 需要绑定的角色
        List<Long> addRoles = roleIds.stream().filter(resourceId -> !hasRoles.contains(resourceId)).collect(Collectors.toList());
        // 需要解绑的角色
        List<Long> removeRoles = hasRoles.stream().filter(resourceId -> !roleIds.contains(resourceId)).collect(Collectors.toList());
        // 绑定角色
        for (Long roleId : addRoles) {
            UserRoleRelationPO userRoleRelationPO = new UserRoleRelationPO();
            userRoleRelationPO.setUserId(userId);
            userRoleRelationPO.setRoleId(roleId);
            userRoleRelationDao.insert(userRoleRelationPO);
        }
        // 解绑角色
        for (Long roleId : removeRoles) {
            userRoleRelationDao.delete(userId, roleId);
        }
        return roleIds.size();
    }

    @Override
    public List<RoleDTO> selectUserRoles(Long id) {
        List<RolePO> rolePOS = roleDao.selectByUserId(id);
        return BeanCopierUtils.copyListProperties(rolePOS, RoleDTO.class);
    }
}
