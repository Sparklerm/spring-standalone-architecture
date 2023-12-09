package com.yiyan.boot.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yiyan.boot.model.po.ResourceCategoryPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IResourceCategoryDao extends BaseMapper<ResourceCategoryPO> {

    /**
     * 查询所有后台资源分类
     */
    List<ResourceCategoryPO> selectAll();

    /**
     * 根据名称查询后台资源分类
     *
     * @param name 分类名称
     * @return 后台资源分类
     */
    ResourceCategoryPO selectByName(String name);
}




