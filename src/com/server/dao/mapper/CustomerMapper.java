package com.server.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.server.pojo.entity.Ccustomer;
import com.server.pojo.entity.Customer;
import com.server.pojo.entity.CustomerStatisticVO;

public interface CustomerMapper {
	/**
	 * 查询一段时间内有订单的客户名称
	 */
	List<String> selectTimeCusNames(@Param("staTime") String staTime,@Param("endTime") String endTime,@Param("companyid") String companyid);
	/**
	 * 客户订单统计报表
	 */
	List<CustomerStatisticVO> selectCusStatisticReport(@Param("companyid") String companyid,@Param("staCusQuery") String staCusQuery,
			@Param("staTime") String staTime,@Param("endTime") String endTime);
	/**
	 * 统计客户下单数量和实际金额的总和
	 */
	CustomerStatisticVO selectStatisticSum(@Param("companyid") String companyid,@Param("staCusQuery") String staCusQuery,
			@Param("staTime") String staTime,@Param("endTime") String endTime);
	/**
	 * 客户统计(数量)
	 */
	Integer selectCusStatisticCount(@Param("companyid") String companyid,@Param("staCusQuery") String staCusQuery,
			@Param("staTime") String staTime,@Param("endTime") String endTime);
	/**
	 * 客户订单统计
	 */
	List<CustomerStatisticVO> selectCusStatistic(@Param("companyid") String companyid,@Param("staCusQuery") String staCusQuery,
			@Param("nowpage") Integer nowpage,@Param("pagesize") Integer pagesize,
			@Param("staTime") String staTime,@Param("endTime") String endTime);
	/**
	 * 检查用户是否已经注册过了
	 */
	Integer selectCustomerIsReg(Customer customer);
	/**
	 * 根据客户关系中的供应商和查询条件查询客户(导出报表时使用)
	 */
	List<Customer> selectCustomerByGuanxi(Ccustomer ccustomer);
	/**
	 * 根据openid查询客户
	 */
	Customer selectByOpenid(String customerphone);
	/**
	 * 根据手机号查询客户
	 */
	Customer selectPhoneToCus(String customerphone);
	/**
	 * 根据条件(手机号)查询客户
	 */
	Customer selectByPhone(Customer customer);
	/**
	 * 根据客户id查询收藏的商品
	 */
	Customer selectCollectGoodsById(@Param("customerid") String customerid,@Param("pricesclass") String pricesclass);
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