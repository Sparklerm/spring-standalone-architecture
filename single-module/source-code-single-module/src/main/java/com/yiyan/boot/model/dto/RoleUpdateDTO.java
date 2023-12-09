package com.yiyan.boot.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Alex Meng
 * @createDate 2023-11-23 02:27
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleUpdateDTO {
    /**
     * 角色ID
     */
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 启用状态：0->禁用；1->启用
     */
    private Integer status;

    /**
     * 是否为用户注册时绑定的默认角色：0->不是；1->是
     */
    private Integer isDefault;

}
