package com.server.dao.mapper;

import java.util.List;

import com.server.pojo.entity.Orderd;

public interface OrderdMapper {
	/**
	 * 根据主键删除
	 */
    int deleteByPrimaryKey(String orderdid);
    /**
     * 选择性添加
     */
    int insertSelective(Orderd record);
    /**
     * 根据主键查询
     */
    Orderd selectByPrimaryKey(String orderdid);
    /**
     * 根据主键选择性修改
     */
    int updateByPrimaryKeySelective(Orderd record);
}