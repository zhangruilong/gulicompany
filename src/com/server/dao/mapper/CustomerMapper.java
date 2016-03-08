package com.server.dao.mapper;

import java.util.List;

import com.server.pojo.entity.Customer;

public interface CustomerMapper {
	/**
	 * 根据条件(手机号)查询客户
	 */
	Customer selectByPhone(Customer customer);
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