package ${groupId}.security.config;

import com.alibaba.fastjson2.JSON;
import ${groupId}.common.enums.BizCodeEnum;
import ${groupId}.common.model.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义未登录或者token失效时的返回结果
 */
public class UserAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());
        response.getWriter().write(JSON.toJSONString(Result.error(BizCodeEnum.UNAUTHORIZED)));
        response.getWriter().flush();
    }
}