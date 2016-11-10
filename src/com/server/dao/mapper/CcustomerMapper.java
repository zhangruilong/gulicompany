package com.server.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.server.pojo.entity.Ccustomer;

public interface CcustomerMapper {
	/**
	 * 查询一段时间内的有订单的业务员的名字
	 */
	List<String> selectTimeEmpName(@Param("staTime") String staTime,@Param("endTime") String endTime,
			@Param("companyid") String companyid);
	/**
	 * 根据关系id查询关系和客户信息
	 */
	Ccustomer selectCCusAndCusById(String ccustomerid);
	/**
	 * 根据供应商查询客户关系(数量)
	 */
	Integer selectCusByComCount(@Param("ccustomer") Ccustomer ccustomer);
	/**
	 * 根据供应商查询客户关系
	 */
	List<Ccustomer> selectCusByCom(@Param("ccustomer") Ccustomer ccustomer,
			@Param("nowpage") Integer nowpage,@Param("pagesize") Integer pagesize);
	/**
	 * 根据客户id查询所有关系系统
	 */
	List<Ccustomer> selectByCusId(String cusId);
	/**
	 * 根据主键删除
	 */
    int deleteByPrimaryKey(String ccustomerid);
    /**
     * 选择性添加
     */
    int insertSelective(Ccustomer record);
    /**
     * 根据主键查询
     */
    Ccustomer selectByPrimaryKey(String ccustomerid);
    /**
     * 根据主键选择性修改
     */
    int updateByPrimaryKeySelective(Ccustomer record);
}