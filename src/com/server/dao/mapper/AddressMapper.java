package com.server.dao.mapper;

import java.util.List;

import com.server.pojo.entity.Address;
/**
 * 我的收货地址
 * @author taolichao
 *
 */
public interface AddressMapper {
	/**
	 * 查询默认地址(条件查询)
	 */
	List<Address> selectDefAddress(Address record);
	/**
	 * 修改用户的所有收货地址'是否默认'
	 */
	void updateCusAllAddress(Address record);
	/**
	 * 根据客户id查询
	 */
	List<Address> selectByCondition(String customerId);
	/**
	 * 根据主键删除
	 */
    int deleteByPrimaryKey(String addressid);
    /**
	 * 选择性添加
	 */
    int insertSelective(Address record);
    /**
	 * 根据主键查询
	 */
    Address selectByPrimaryKey(String addressid);
    /**
	 * 根据主键选择性修改
	 */
    int updateByPrimaryKeySelective(Address record);
}