package com.server.dao.mapper;

import com.server.pojo.Collect;

public interface CollectMapper {
	/**
	 * 根据主键删除
	 */
    int deleteByPrimaryKey(String collectid);
    /**
     * 选择性添加
     */
    int insertSelective(Collect record);
    /**
     * 根据主键查询
     */
    Collect selectByPrimaryKey(String collectid);
    /**
     * 根据主键选择性修改
     */
    int updateByPrimaryKeySelective(Collect record);
}