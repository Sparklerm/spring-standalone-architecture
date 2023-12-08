package ${groupId}.config.security;

import ${groupId}.common.utils.JwtUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Jwt配置类
 */
@Slf4j
@Component
@Data
public class JwtConfig {
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expirationTime}")
    private long expirationTime;
    @Value("${jwt.tokenHeader}")
    private String header;

    @PostConstruct
    public void jwtInit() {
        JwtUtils.initialize(issuer, secretKey, expirationTime, header);
        log.info("JWTUtil初始化完成");
    }
}
