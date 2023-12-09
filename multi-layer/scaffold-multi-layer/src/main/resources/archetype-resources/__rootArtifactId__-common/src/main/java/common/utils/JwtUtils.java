package ${groupId}.common.utils;

import cn.hutool.crypto.SecureUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * JWT工具类
 *
 * @author Alex Meng
 * @createDate 2023-10-30 22:27
 */
public class JwtUtils {

    /**
     * 默认JWT标签头
     */
    public static final String HEADER = "Authorization";
    private static JwtConfig jwtConfig;

    private JwtUtils() {
    }

    /**
     * 初始化参数
     *
     * @param issuer             签发者
     * @param secretKey          密钥 最小长度：4
     * @param expirationTime     Token过期时间 单位：秒
     * @param issuers            签发者列表 校验签发者时使用
     * @param signatureAlgorithm 加密算法
     * @param audience           接受者
     */
    public static void initialize(String issuer, String secretKey, long expirationTime, String header, List<String> issuers, SignatureAlgorithm signatureAlgorithm, String audience) {
        jwtConfig = new JwtConfig();
        jwtConfig.setHeader(StringUtils.isNotBlank(header) ? header : HEADER);
        jwtConfig.setIssuer(issuer);
        jwtConfig.setSecretKey(secretKey);
        jwtConfig.setExpirationTime(expirationTime);
        if (CollectionUtils.isEmpty(issuers)) {
            issuers = Collections.singletonList(issuer);
        }
        jwtConfig.setIssuers(issuers);
        jwtConfig.setSignatureAlgorithm(signatureAlgorithm);
        jwtConfig.setAudience(audience);
    }

    /**
     * 初始化参数
     *
     * @param issuer         签发者
     * @param secretKey      密钥 最小长度：4
     * @param expirationTime Token过期时间 单位：秒
     */
    public static void initialize(String issuer, String secretKey, long expirationTime, String header) {
        initialize(issuer, secretKey, expirationTime, header, null, SignatureAlgorithm.HS256, null);
    }

    /**
     * 生成 Token
     *
     * @param subject 主题
     * @return Token
     */
    public static String generateToken(String subject) {
        return generateToken(subject, jwtConfig.getExpirationTime());
    }

    /**
     * 生成 Token
     *
     * @param subject        主题
     * @param expirationTime 过期时间
     * @return Token
     */
    public static String generateToken(String subject, long expirationTime) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationTime * 1000);

        return Jwts.builder()
                .signWith(jwtConfig.getSignatureAlgorithm(), jwtConfig.getSecretKey())
                .setSubject(subject)
                .setIssuer(jwtConfig.getIssuer())
                .setAudience(jwtConfig.getAudience())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .compact();
    }

    /**
     * 生成 Token
     *
     * @param subject        主题
     * @param payload        自定义载荷
     * @param expirationTime 过期时间
     * @return Token
     */
    public static String generateToken(String subject, Map<String, Object> payload, long expirationTime) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + expirationTime * 1000);

        JwtBuilder jwtBuilder = Jwts.builder()
                .signWith(jwtConfig.getSignatureAlgorithm(), jwtConfig.getSecretKey())
                .setSubject(subject)
                .setIssuer(jwtConfig.getIssuer())
                .setAudience(jwtConfig.getAudience())
                .setIssuedAt(now)
                .setExpiration(expiration);
        jwtBuilder.addClaims(payload);
        return jwtBuilder.compact();
    }

    /**
     * 生成 Token
     *
     * @param subject 主题
     * @param payload 自定义载荷
     * @return Token
     */
    public static String generateToken(String subject, Map<String, Object> payload) {
        return generateToken(subject, payload, jwtConfig.getExpirationTime());
    }

    /**
     * 生成 Token
     *
     * @param payload 自定义载荷
     * @return Token
     */
    public static String generateToken(Map<String, Object> payload) {
        return generateToken(null, payload, jwtConfig.getExpirationTime());
    }

    /**
     * 生成 Token
     *
     * @param payload        自定义载荷
     * @param expirationTime 过期时间
     * @return Token
     */
    public static String generateToken(Map<String, Object> payload, long expirationTime) {
        return generateToken(null, payload, expirationTime);
    }


    /**
     * 加密token
     *
     * @param token     token
     * @param secretKey 密钥
     * @return 加密后的token
     */
    public static String encodeToken(String token, String secretKey) {
        return SecureUtil.aes(secretKey.getBytes()).encryptHex(token);
    }

    /**
     * 解密token
     *
     * @param token     token
     * @param secretKey 密钥
     * @return 解密后的token
     */
    public static String decodeToken(String token, String secretKey) {
        return SecureUtil.aes(secretKey.getBytes()).decryptStr(token);
    }

    /**
     * 验证 Token
     *
     * @param token token
     * @return 验证通过返回true，否则返回false
     */
    public static boolean verifyToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtConfig.getSecretKey()).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 获取 Token 中的声明信息
     *
     * @param token token
     * @return Token 中的声明信息
     */
    public static Claims getTokenClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtConfig.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 获取 Token 过期时间
     *
     * @param token token
     * @return Token 过期时间
     */
    public static Date getTokenExpiresAt(String token) {
        return getTokenClaims(token).getExpiration();
    }

    /**
     * 获取 Token 主题
     *
     * @param token token
     * @return Token 主题
     */
    public static String getTokenSubject(String token) {
        return getTokenClaims(token).getSubject();
    }

    /**
     * 验证 Token 签发者
     *
     * @param token token
     * @return 验证通过返回true，否则返回false
     */
    public static boolean verifyTokenIssuer(String token) {
        boolean flag = false;
        for (String issuer : jwtConfig.issuers) {
            if (getTokenClaims(token).getIssuer().equals(issuer)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * 判断token是否已经失效
     */
    public static boolean isTokenExpired(String token) {
        Date expiredDate = getTokenExpiresAt(token);
        return expiredDate.before(new Date());
    }

    /**
     * 验证token是否还有效
     *
     * @param token   客户端传入的token
     * @param subject 主题信息
     */
    public static boolean validateToken(String token, String subject) {
        String tokenSubject = getTokenSubject(token);
        return tokenSubject.equals(subject) && !isTokenExpired(token);
    }


    @Data
    public static class JwtConfig {
        /**
         * JwtToken 标签头
         */
        private String header;
        /**
         * 签发者
         */
        private String issuer;
        /**
         * 密钥
         */
        private String secretKey;
        /**
         * Token 过期时间
         */
        private long expirationTime;
        /**
         * 签发者列表
         */
        private List<String> issuers;
        /**
         * 加密算法
         */
        private SignatureAlgorithm signatureAlgorithm;
        /**
         * 接受者
         */
        private String audience;
    }
}
