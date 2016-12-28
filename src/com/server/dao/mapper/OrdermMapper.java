package com.server.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.server.pojo.entity.Orderm;
import com.server.pojo.entity.Ordermview;
import com.server.pojo.entity.PrintInfo;

public interface OrdermMapper {
	/**
	 * 修改订单打印次数
	 */
	int updatePrintCount(@Param("ordermid") String ordermid,@Param("ordermprinttimes") String ordermprinttimes
			,@Param("updor") String updor);
	/**
	 * 根据ordermid查询打印订单时需要的信息：订单、客户、经销商 信息
	 */
	List<PrintInfo> selectPrintInfo(String[] ordermids);
	/**
	 * 查询订单总实际金额
	 */
	Integer selectOMrightMoney();
	/**
	 * 查询最新的一条订单信息
	 */
	Orderm selectNewestOrderm(@Param("ordermcompany") String ordermcompany);
	/**
	 * 根据ordermcustomer和orderdcode(商品编码)查询订单
	 */
	Integer selectOrderByCustomerGoods(@Param("ordermcustomer") String ordermcustomer,
			@Param("orderdcode") String orderdcode,@Param("orderdtype") String orderdtype,
			@Param("staTime") String staTime,@Param("endTime") String endTime);
	/**
	 * 根据供应商(和条件)查询订单(分页)
	 */
	List<Ordermview> selectByPage(@Param("staTime") String staTime,
			@Param("endTime") String endTime,@Param("orderm") Orderm record,
			@Param("nowpage") Integer nowpage,@Param("pagesize") Integer pagesize);
	/**
	 * 根据供应商(和条件)查询订单(数量)
	 */
	Integer selectByCompanyCount(@Param("staTime") String staTime,
			@Param("endTime") String endTime,@Param("orderm") Orderm record);
	/**
	 * 根据供应商(和条件)查询订单
	 */
	List<Ordermview> selectByCompany(@Param("staTime") String staTime,
			@Param("endTime") String endTime,@Param("orderm") Orderm record);
	/**
	 * 根据主键删除
	 */
    int deleteByPrimaryKey(String ordermid);
    /**
     * 选择性添加
     */
    int insertSelective(Orderm record);
    /**
     * 根据主键查询订单、订单详情、客户信息和业务员
     */
    Orderm selectByPrimaryKey(String ordermid);
    /**
     * 根据主键选择性修改
     */
    int updateByPrimaryKeySelective(Orderm record);
}