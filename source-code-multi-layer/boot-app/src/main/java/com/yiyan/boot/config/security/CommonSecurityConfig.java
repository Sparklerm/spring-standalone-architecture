package com.yiyan.boot.config.security;

import com.yiyan.boot.security.component.DynamicAccessDecisionManager;
import com.yiyan.boot.security.component.DynamicSecurityFilter;
import com.yiyan.boot.security.component.DynamicSecurityMetadataSource;
import com.yiyan.boot.security.component.DynamicSecurityService;
import com.yiyan.boot.security.config.UserAccessDeniedHandler;
import com.yiyan.boot.security.config.UserAuthenticationEntryPoint;
import com.yiyan.boot.service.auth.model.ResourceDTO;
import com.yiyan.boot.service.auth.service.IResourceService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Alex Meng
 * @createDate 2023-11-21 01:04
 */
@Configuration
public class CommonSecurityConfig {

    @Resource
    private IResourceService resourceService;

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserAccessDeniedHandler userAccessDeniedHandler() {
        return new UserAccessDeniedHandler();
    }

    @Bean
    public UserAuthenticationEntryPoint userAuthenticationEntryPoint() {
        return new UserAuthenticationEntryPoint();
    }

    @Bean
    public DynamicSecurityService dynamicSecurityService() {
        return () -> {
            List<ResourceDTO> resourceList = resourceService.listAll();
            if (resourceList == null) {
                return null;
            }
            Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
            for (ResourceDTO resource : resourceList) {
                map.put(resource.getUrl(), new org.springframework.security.access.SecurityConfig(resource.getId() + ":" + resource.getName()));
            }
            return map;
        };
    }

    @ConditionalOnBean(name = "dynamicSecurityService")
    @Bean
    public DynamicAccessDecisionManager dynamicAccessDecisionManager() {
        return new DynamicAccessDecisionManager();
    }

    @ConditionalOnBean(name = "dynamicSecurityService")
    @Bean
    public DynamicSecurityMetadataSource dynamicSecurityMetadataSource() {
        return new DynamicSecurityMetadataSource();
    }

    @ConditionalOnBean(name = "dynamicSecurityService")
    @Bean
    public DynamicSecurityFilter dynamicSecurityFilter() {
        return new DynamicSecurityFilter();
    }
}
