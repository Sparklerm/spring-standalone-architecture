package ${groupId}.common.constant;

import ${groupId}.common.utils.cache.CaffeineCacheConfig;

import java.util.Arrays;
import java.util.List;

/**
 * 缓存常量
 *
 * @author Alex Meng
 * @createDate 2023-11-21 23:45
 */
public class CacheConstants {

    private CacheConstants() {
    }

    public static final String USER_CACHE_NAME = "userCache";
    public static final String VERIFY_CODE_CACHE_NAME = "verifyCodeCache";
    public static final List<CaffeineCacheConfig> LOCAL_CACHE_CONFIG = Arrays.asList(
            CaffeineCacheConfig.builder()
                    .cacheName(USER_CACHE_NAME)
                    .initialCapacity(100)
                    .duration(7200)
                    .timeUnit(Constants.CAFFEINE_DEFAULT_TIME_UNIT)
                    .build(),
            CaffeineCacheConfig.builder()
                    .cacheName(VERIFY_CODE_CACHE_NAME)
                    .initialCapacity(100)
                    .duration(300)
                    .timeUnit(Constants.CAFFEINE_DEFAULT_TIME_UNIT)
                    .build()
    );
}
