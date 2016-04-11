package com.server.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.server.pojo.entity.Orderm;
import com.server.pojo.entity.Ordermview;

public interface OrdermMapper {
	/**
	 * 根据供应商(和条件)查询订单
	 */
	List<Ordermview> selectByCompany(@Param("staTime") String staTime,@Param("endTime") String endTime,@Param("orderm") Orderm record);
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