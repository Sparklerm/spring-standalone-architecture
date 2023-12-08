package ${groupId}.service.auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录结果
 *
 * @author Alex Meng
 * @createDate 2023-11-20 0020 下午 10:54
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResultDTO {
    /**
     * token
     */
    private String token;
    /**
     * 刷新token
     */
    private String refreshToken;
}
