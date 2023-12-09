package com.yiyan.boot.config.security.component;

import cn.hutool.core.util.URLUtil;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 动态权限数据源，用于获取动态权限规则
 *
 * @author Alex Meng
 * @createDate 2023-11-21 01:04
 */
public class DynamicSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private static Map<String, ConfigAttribute> configAttributeMap = null;
    @Resource
    private DynamicSecurityService dynamicSecurityService;

    @PostConstruct
    public void loadDataSource() {
        configAttributeMap = dynamicSecurityService.loadDataSource();
    }

    public void clearDataSource() {
        configAttributeMap.clear();
        configAttributeMap = null;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        if (configAttributeMap == null) {
            this.loadDataSource();
        }
        List<ConfigAttribute> configAttributes = new ArrayList<>();
        //获取当前访问的路径
        String url = ((FilterInvocation) o).getRequestUrl();
        String path = URLUtil.getPath(url);
        PathMatcher pathMatcher = new AntPathMatcher();
        //获取访问该路径所需资源
        for (Map.Entry<String, ConfigAttribute> config : configAttributeMap.entrySet()) {
            if (pathMatcher.match(config.getKey(), path)) {
                configAttributes.add(config.getValue());
            }
        }

        // 未设置操作请求权限，返回空集合
        return configAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return Collections.emptyList();
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

}
