package com.yiyan.boot.common.model.base;

import lombok.Data;

/**
 * 查询排序
 *
 * @author Alex Meng
 * @createDate 2023-10-15 13:25
 */
@Data
public class QueryOrder {
    /**
     * 排序字段
     */
    private String sortField;
    /**
     * 排序规则。默认升序
     */
    private String sortOrder;
}
