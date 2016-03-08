package com.server.dao.mapper;

import java.util.List;

import com.server.pojo.entity.Orderm;

public interface OrdermMapper {
	/**
	 * 根据供应商查询订单
	 */
	List<Orderm> selectByCompany(String ordermcompany);
	/**
	 * 根据主键删除
	 */
    int deleteByPrimaryKey(String ordermid);
    /**
     * 选择性添加
     */
    int insertSelective(Orderm record);
    /**
     * 根据主键查询
     */
    Orderm selectByPrimaryKey(String ordermid);
    /**
     * 根据主键选择性修改
     */
    int updateByPrimaryKeySelective(Orderm record);
}