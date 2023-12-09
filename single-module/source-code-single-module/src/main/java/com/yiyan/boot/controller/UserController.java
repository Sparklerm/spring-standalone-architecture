package com.yiyan.boot.controller;

import com.yiyan.boot.common.model.result.Result;
import com.yiyan.boot.common.utils.BeanCopierUtils;
import com.yiyan.boot.config.security.component.AuthContextHolder;
import com.yiyan.boot.model.dto.AuthUserDetails;
import com.yiyan.boot.model.dto.RoleDTO;
import com.yiyan.boot.model.dto.UserDTO;
import com.yiyan.boot.model.dto.UserLoginResultDTO;
import com.yiyan.boot.model.request.LoginRequest;
import com.yiyan.boot.model.request.UserPageRequest;
import com.yiyan.boot.model.request.UserRegisterRequest;
import com.yiyan.boot.model.request.UserRoleRequest;
import com.yiyan.boot.model.request.UserUpdatePasswordRequest;
import com.yiyan.boot.model.request.UserUpdateRequest;
import com.yiyan.boot.model.response.UserVO;
import com.yiyan.boot.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Alex Meng
 * @createDate 2023-11-21 04:55
 */
@RestController
@RequestMapping("/user")
@Api(tags = "后台用户API")
public class UserController {

    @Resource
    private IUserService userService;

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    @ResponseBody
    public Result<UserVO> register(@Valid @RequestBody UserRegisterRequest request) {
        UserDTO userDTO = BeanCopierUtils.copyProperties(request, UserDTO.class);
        UserVO register = BeanCopierUtils.copyProperties(userService.register(userDTO), UserVO.class);
        return Result.success(register);
    }

    @PostMapping("/login")
    @ApiOperation(value = "密码登录")
    public Result<UserLoginResultDTO> login(@RequestBody LoginRequest request) {
        UserLoginResultDTO userLoginResultDTO = userService.loginByPassword(request.getUsername(), request.getPassword());
        return Result.success(userLoginResultDTO);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping(value = "/common/current")
    public Result<UserVO> getAdminInfo() {
        AuthUserDetails context = AuthContextHolder.getContext();
        UserVO userVO = BeanCopierUtils.copyProperties(context.getUser(), UserVO.class);
        return Result.success(userVO);
    }

    @ApiOperation(value = "登出功能")
    @PostMapping("/common/logout")
    public Result<String> logout() {
        AuthUserDetails context = AuthContextHolder.getContext();
        userService.logout(context.getUser().getUsername());
        return Result.success();
    }

    @ApiOperation("用户修改密码")
    @PostMapping("/common/updatePassword")
    public Result<String> updatePassword(@Valid @RequestBody UserUpdatePasswordRequest request) {
        AuthUserDetails context = AuthContextHolder.getContext();
        Integer update = userService.updatePassword(context.getUser().getId(), request.getOldPassword(), request.getNewPassword());
        return update > 0 ? Result.updateSuccess(update) : Result.error();
    }

    @ApiOperation("修改当前登录用户信息")
    @PostMapping("/common/updateCurrentUserInfo")
    public Result<String> updateCurrentUserInfo(@RequestBody UserUpdateRequest request) {
        // 获取当前登录用户信息
        AuthUserDetails context = AuthContextHolder.getContext();
        // 当前登录用户信息及更新信息填充到DTO
        UserDTO userDTO = BeanCopierUtils.copyProperties(request, UserDTO.class);
        userDTO.setId(context.getUser().getId());
        // 不允许在此处修改密码
        userDTO.setPassword(null);
        Integer update = userService.update(userDTO);
        return update > 0 ? Result.updateSuccess(update) : Result.error();
    }

    @ApiOperation("根据用户名或姓名分页获取用户列表")
    @GetMapping(value = "/list")
    public Result<List<UserVO>> list(UserPageRequest request) {
        List<UserDTO> list = userService.list(request.getPageNum(), request.getPageSize(), BeanCopierUtils.copyProperties(request, UserDTO.class));
        return Result.success(BeanCopierUtils.copyListProperties(list, UserVO.class));
    }

    @ApiOperation("获取指定用户信息")
    @GetMapping(value = "/{id}")
    public Result<UserVO> getItem(@PathVariable("id") Long id) {
        UserDTO userDTO = userService.selectById(id);
        return Result.success(BeanCopierUtils.copyProperties(userDTO, UserVO.class));
    }

    @ApiOperation("修改指定用户信息")
    @PostMapping("/update/{id}")
    public Result<String> update(@PathVariable("id") Long id, @RequestBody UserUpdateRequest request) {
        UserDTO userDTO = BeanCopierUtils.copyProperties(request, UserDTO.class);
        userDTO.setId(id);
        Integer update = userService.update(userDTO);
        return update > 0 ? Result.updateSuccess(update) : Result.error();
    }


    @ApiOperation("删除指定用户")
    @PostMapping("/delete/{id}")
    public Result<String> delete(@PathVariable Long id) {
        Integer delete = userService.delete(id);
        return delete > 0 ? Result.deleteSuccess(delete) : Result.error();
    }

    @ApiOperation("修改帐号状态")
    @PostMapping("/updateStatus/{id}")
    public Result<String> updateStatus(@PathVariable("id") Long id, @RequestBody UserUpdateRequest request) {
        UserDTO userDTO = UserDTO.builder().id(id).status(request.getStatus()).build();
        Integer update = userService.update(userDTO);
        return update > 0 ? Result.updateSuccess(update) : Result.error();
    }

    @ApiOperation("给用户分配角色")
    @PostMapping("/role/update")
    public Result<String> updateRole(@RequestBody UserRoleRequest request) {
        Integer bindRole = userService.updateHasRole(request.getUserId(), request.getRoleIds());
        return bindRole > 0 ? Result.updateSuccess(bindRole) : Result.error();
    }

    @ApiOperation("获取指定用户的角色")
    @GetMapping(value = "/role/{id}")
    public Result<List<RoleDTO>> getRoleList(@PathVariable Long id) {
        List<RoleDTO> roleList = userService.selectUserRoles(id);
        return Result.success(roleList);
    }
}
