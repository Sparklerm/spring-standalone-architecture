package com.yiyan.boot.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Alex Meng
 * @createDate 2023-11-2 20:31
 */
@Data
public class ResourceCategoryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 分类名称
     */
    private String name;
}
