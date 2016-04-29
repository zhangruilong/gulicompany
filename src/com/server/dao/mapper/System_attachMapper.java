package com.server.dao.mapper;

import java.util.List;

import com.server.pojo.entity.System_attach;

public interface System_attachMapper {
	/**
	 * 根据分类和外键(FID)查询
	 */
	List<System_attach> selectByClassifyAndFid(System_attach attach);
	/**
	 * 根据主键删除
	 */
    int deleteByPrimaryKey(String id);
    /**
     * 选择性添加
     */
    int insertSelective(System_attach record);
    /**
     * 根据主键查询
     */
    System_attach selectByPrimaryKey(String id);
    /**
     * 根据主键选择性修改
     */
    int updateByPrimaryKeySelective(System_attach record);
}