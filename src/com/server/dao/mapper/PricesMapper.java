package com.server.dao.mapper;

import com.server.pojo.Prices;

public interface PricesMapper {
	/**
	 * 根据主键删除
	 */
    int deleteByPrimaryKey(String pricesid);
    /**
     * 选择性添加
     */
    int insertSelective(Prices record);
    /**
     * 根据主键查询
     */
    Prices selectByPrimaryKey(String pricesid);
    /**
     * 根据主键选择性修改
     */
    int updateByPrimaryKeySelective(Prices record);
}