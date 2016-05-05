package com.server.dao.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.server.pojo.entity.Orderd;
import com.server.pojo.entity.OrderdStatistics;

public interface OrderdMapper {
	/**
	 * 查询热销商品
	 */
	List<Orderd> selectHotGoodsCodeAndType(@Param("staTime") String staTime,@Param("endTime") String endTime,@Param("cityname") String cityname);
	/**
	 * 查询到客户的所有秒杀品的订单详情
	 */
	List<Orderd> selectOrderdByCustomerMiaosha(@Param("orderd") Orderd orderd,@Param("staTime") String staTime,@Param("endTime") String endTime);
	/**
	 * 根据时间范围(多条件)查询订单(分页)
	 */
	ArrayList<Orderd> selectByPage(@Param("staTime") String staTime,
			@Param("endTime") String endTime,@Param("companyid")String companyid,@Param("condition") String condition,
			@Param("nowpage") Integer nowpage,@Param("pagesize") Integer pagesize);
	/**
	 * 根据时间范围(多条件)查询订单(数量)
	 */
	Integer selectByTimeCount(@Param("staTime") String staTime,
			@Param("endTime") String endTime,@Param("companyid")String companyid,@Param("condition") String condition);
	/**
	 * 条件查询统计
	 */
	OrderdStatistics selectOrderdStatistics(@Param("staTime") String staTime,
			@Param("endTime") String endTime,@Param("companyid")String companyid,@Param("condition") String condition);
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