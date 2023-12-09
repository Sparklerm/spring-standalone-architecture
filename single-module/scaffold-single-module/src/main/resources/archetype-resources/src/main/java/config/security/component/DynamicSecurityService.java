package ${groupId}.config.security.component;

import org.springframework.security.access.ConfigAttribute;

import java.util.Map;

/**
 * 动态权限相关业务接口
 *
 * @author Alex Meng
 * @createDate 2023-11-21 01:04
 */
public interface DynamicSecurityService {
    /**
     * 加载资源ANT通配符和资源对应MAP
     */
    Map<String, ConfigAttribute> loadDataSource();
}
