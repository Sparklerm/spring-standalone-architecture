package com.yiyan.boot.config.security;

import com.yiyan.boot.common.utils.JwtUtils;
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
    @Value("${jwt.secret: 123456}")
    private String secretKey;
    @Value("${jwt.issuer: boot_app}")
    private String issuer;
    @Value("${jwt.expirationTime: 3600}")
    private long expirationTime;
    @Value("${jwt.header: Authorization}")
    private String header;

    @PostConstruct
    public void jwtInit() {
        JwtUtils.initialize(issuer, secretKey, expirationTime, header);
        log.info("JWTUtil初始化完成");
    }
}
