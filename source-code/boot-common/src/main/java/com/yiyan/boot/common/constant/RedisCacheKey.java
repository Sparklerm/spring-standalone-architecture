package com.yiyan.boot.common.constant;

/**
 * Redis 缓存key
 *
 * @author Alex Meng
 * @createDate 2023-11-23 07:57
 */
public class RedisCacheKey {
    /**
     * 应用Redis缓存根路径
     */
    public static final String ROOT = "boot:";

    /**
     * 用户模块缓存KEY
     */
    public interface User {
        /**
         * 用户模块根路径
         */
        String USER_ROOT = RedisCacheKey.ROOT + "user:";
        /**
         * 用户信息缓存 1. username
         */
        String USER_INFO = USER_ROOT + "info:{0}";
        /**
         * 用户权限缓存 1. username
         */
        String USER_PERMISSION = USER_ROOT + "permission:{0}";
        /**
         * 用户登录Token 1. username
         */
        String USER_TOKEN = USER_ROOT + "token:{0}";
    }

}
