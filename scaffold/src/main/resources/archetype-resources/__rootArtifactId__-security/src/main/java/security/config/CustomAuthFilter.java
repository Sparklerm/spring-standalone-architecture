package ${groupId}.security.config;

import ${groupId}.common.constant.RedisCacheKey;
import ${groupId}.common.utils.JWTUtil;
import ${groupId}.common.utils.StringUtils;
import ${groupId}.common.utils.encrypt.AESUtil;
import ${groupId}.common.utils.json.JsonUtils;
import ${groupId}.common.utils.redis.RedisService;
import ${groupId}.service.auth.model.AuthUserDetails;
import ${groupId}.service.auth.model.UserDTO;
import jodd.util.Base64;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 自定义认证过滤器
 */
@Slf4j
@Component
public class CustomAuthFilter extends OncePerRequestFilter {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private RedisService redisService;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String requestToken = request.getHeader(this.tokenHeader);
        // 读取请求头中的token
        if (StringUtils.isNotBlank(requestToken)) {
            // 解码token
            String token = new String(Base64.decode(requestToken), StandardCharsets.UTF_8);
            // 获取token中的验证信息
            String authInfo = JWTUtil.getTokenSubject(token);
            if (!JWTUtil.isTokenExpired(token) && StringUtils.isNotBlank(authInfo) && SecurityContextHolder.getContext().getAuthentication() == null) {
                // 解析Token，获取用户信息
                String decodeSubject = AESUtil.decode(jwtSecret, authInfo);
                UserDTO userInfo = JsonUtils.toObj(decodeSubject, UserDTO.class);
                // 验证token是否有效
                String cacheToken = redisService.getString(StringUtils.format(RedisCacheKey.USER_TOKEN, userInfo.getUsername()));
                if (StringUtils.isNotBlank(cacheToken) && cacheToken.equals(token)) {
                    AuthUserDetails userDetails = (AuthUserDetails) userDetailsService.loadUserByUsername(userInfo.getUsername());
                    // 保存用户信息到当前会话
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities());
                    // 将authentication填充到安全上下文
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }
}