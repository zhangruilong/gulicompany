package com.server.dao.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.server.pojo.entity.Orderd;

public interface OrderdMapper {
	/**
	 * 根据时间范围(多条件)查询订单
	 */
	ArrayList<Orderd> selectByTime(@Param("staTime") String staTime,
			@Param("endTime") String endTime,@Param("companyid")String companyid,@Param("condition") String condition);
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