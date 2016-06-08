package com.server.dao.mapper;

import com.server.pojo.entity.Largecusprice;

public interface LargecuspriceMapper {
	/**
	 * 根据主键删除
	 */
    int deleteByPrimaryKey(String largecuspriceid);
    /**
     * 选择性添加
     */
    int insertSelective(Largecusprice record);
    /**
     * 根据主键选择性修改
     */
    int updateByPrimaryKeySelective(Largecusprice record);
}