package com.server.dao.mapper;

import com.server.pojo.entity.Customer;

public interface CustomerMapper {
	/**
	 * 根据客户id查询收藏的商品
	 */
	Customer selectCollectGoodsById(String customerid);
	/**
	 * 根据主键删除
	 */
    int deleteByPrimaryKey(String customerid);
    /**
     * 选择性添加
     */
    int insertSelective(Customer record);
    /**
     * 根据主键查询
     */
    Customer selectByPrimaryKey(String customerid);
    /**
     * 根据主键选择性修改
     */
    int updateByPrimaryKeySelective(Customer record);
}