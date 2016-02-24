package com.server.dao.mapper;

import java.util.List;

import com.server.pojo.Address;

public interface AddressMapper {
	/**
	 * 根据条件查询
	 */
	List<Address> selectByCondition(Address record);
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