package com.yiyan.boot.service.auth.model;

import com.yiyan.boot.common.enums.YesNoEnum;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 鉴权用户信息
 *
 * @author Alex Meng
 * @createDate 2023-11-20 21:26
 */
@Data
public class AuthUserDetails implements UserDetails {

    public AuthUserDetails(UserDTO user, List<ResourceDTO> resourceList) {
        this.user = user;
        this.resourceList = resourceList;
    }

    /**
     * 用户信息
     */
    private final UserDTO user;

    /**
     * 用户的权限集合
     */
    private final List<ResourceDTO> resourceList;


    /**
     * 返回授予用户的权限。无法返回 null。
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return resourceList.stream()
                .map(resource -> new SimpleGrantedAuthority(resource.getId() + ":" + resource.getName()))
                .collect(Collectors.toList());
    }


    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus().equals(YesNoEnum.YES.getKey());
    }
}
