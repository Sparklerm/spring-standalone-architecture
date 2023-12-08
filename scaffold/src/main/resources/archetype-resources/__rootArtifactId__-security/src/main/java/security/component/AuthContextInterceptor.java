package ${groupId}.security.component;

import ${groupId}.security.component.AuthContextHolder;
import ${groupId}.service.auth.model.AuthUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 拦截器，将当前会话的用户信息保存到上下文
 */
@Component
public class AuthContextInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.isNull(authentication) || Objects.isNull(authentication.getPrincipal())) {
            //无上下文信息，直接放行
            return true;
        }
        AuthUserDetails principal = (AuthUserDetails) authentication.getPrincipal();
        // 保存用户信息到当前会话
        AuthContextHolder.getInstance().setContext(principal);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 会话结束，清除上下文信息
        AuthContextHolder.getInstance().clear();
    }
}
