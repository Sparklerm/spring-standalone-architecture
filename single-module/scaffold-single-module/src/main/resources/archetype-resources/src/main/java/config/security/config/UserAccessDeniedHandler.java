package ${groupId}.config.security.config;

import com.alibaba.fastjson2.JSON;
import ${groupId}.common.enums.BizCodeEnum;
import ${groupId}.common.model.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义无权限访问的返回结果
 *
 * @author Alex Meng
 * @createDate 2023-11-21 01:04
 */
public class UserAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());
        response.getWriter().println(JSON.toJSONString(Result.error(BizCodeEnum.FORBIDDEN)));
        response.getWriter().flush();
    }
}