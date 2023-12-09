package com.yiyan.boot.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录结果
 *
 * @author Alex Meng
 * @createDate 2023-11-20 22:54
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResultDTO {
    /**
     * token
     */
    private String accessToken;
    /**
     * 刷新token
     */
    private String refreshToken;
}
