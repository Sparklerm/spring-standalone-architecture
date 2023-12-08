package ${groupId}.common.constant;

/**
 * Redis 缓存key
 *
 * @author Alex Meng
 * @createDate 2023-11-23 0023 上午 07:57
 */
public class RedisCacheKey {
    public static final String ROOT = "boot:";

    /**
     * 用户信息缓存 1. username
     */
    public static final String USER_INFO = ROOT + "user:info:{}";

    /**
     * 用户权限缓存 1. username
     */
    public static final String USER_PERMISSION = ROOT + "user:permission:{}";

    /**
     * 用户列表缓存
     */
    public static final String USER_LIST = ROOT + "user:list:";

    /**
     * 用户资源缓存
     */
    public static final String USER_RESOURCE = ROOT + "user:resource:";

    /**
     * 用户角色缓存
     */
    public static final String USER_ROLE = ROOT + "user:role:";

    /**
     * 用户登录Token 1. username
     */
    public static final String USER_TOKEN = ROOT + "user:token:{}";

    /**
     * 资源列表缓存
     */
    public static final String RESOURCE_LIST = ROOT + "resource:list:";

    /**
     * 资源列表缓存
     */
    public static final String RESOURCE_ALL = ROOT + "resource:all:";

    /**
     * 资源分类列表缓存
     */
    public static final String RESOURCE_CATEGORY_ALL = ROOT + "resource_category:all";

    /**
     * 角色列表缓存
     */
    public static final String ROLE_LIST = ROOT + "role:list:";
    /**
     * 角色列表缓存
     */
    public static final String ROLE_ALL = ROOT + "role:all:";
    /**
     * 角色资源缓存
     */
    public static final String ROLE_RESOURCE = ROOT + "role:resource:";


}
