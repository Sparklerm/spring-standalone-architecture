package ${groupId}.config;

import ${groupId}.security.component.AuthContextInterceptor;
import ${groupId}.security.config.IgnoreUrlsConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * MVC配置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private IgnoreUrlsConfig ignoreUrlsConfig;
    @Resource
    private AuthContextInterceptor authContextInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 会话上下文拦截器
        registry.addInterceptor(authContextInterceptor)
                // 排除白名单
                .excludePathPatterns(ignoreUrlsConfig.getUrls())
                // 拦截路径
                .addPathPatterns("/**");
    }
}
