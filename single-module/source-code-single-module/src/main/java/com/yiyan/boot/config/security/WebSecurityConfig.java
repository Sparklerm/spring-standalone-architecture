package com.yiyan.boot.config.security;

import com.yiyan.boot.config.security.component.DynamicSecurityFilter;
import com.yiyan.boot.config.security.component.DynamicSecurityService;
import com.yiyan.boot.config.security.config.CustomAuthFilter;
import com.yiyan.boot.config.security.config.IgnoreUrlsConfig;
import com.yiyan.boot.config.security.config.UserAccessDeniedHandler;
import com.yiyan.boot.config.security.config.UserAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * SpringSecurity 配置
 *
 * @author MENGJIAO
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private UserAccessDeniedHandler userAccessDeniedHandler;
    @Resource
    private UserAuthenticationEntryPoint userAuthenticationEntryPoint;
    @Resource
    private CustomAuthFilter customAuthFilter;
    @Resource
    private IgnoreUrlsConfig ignoreUrlsConfig;
    @Resource
    private DynamicSecurityService dynamicSecurityService;
    @Resource
    private DynamicSecurityFilter dynamicSecurityFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        HttpSecurity httpSecurity = http
                // 关闭跨站请求防护及禁用session
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 权限设置
                .authorizeRequests(authorize -> authorize
                        .antMatchers(ignoreUrlsConfig.getUrls().toArray(new String[0])).permitAll()
                        // 其他地址的访问均需验证权限
                        .anyRequest().authenticated())
                // 自定义权限拦截器JWT过滤器
                .addFilterBefore(customAuthFilter, UsernamePasswordAuthenticationFilter.class)
                // 自定义权限拒绝处理类,返回权限处理结果
                .exceptionHandling()
                .authenticationEntryPoint(userAuthenticationEntryPoint)
                .accessDeniedHandler(userAccessDeniedHandler)
                .and()
                // 认证用户时用户信息加载配置，注入springAuthUserService
                .userDetailsService(userDetailsService);
        if (dynamicSecurityService != null) {
            httpSecurity.addFilterBefore(dynamicSecurityFilter, FilterSecurityInterceptor.class);
        }
        return httpSecurity.build();
    }
}
