package com.server.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.server.pojo.entity.Emp;

public interface EmpMapper {
	/**
	 * 业务员登录
	 */
	List<Emp> selectEmpLogin(@Param("loginname") String loginname,@Param("password") String password);
	/**
	 * 查询全部业务员
	 */
	List<Emp> selectAllEmp(@Param("condition") String condition,@Param("companyid") String companyid);
	/**
	 * 根据供应商id查询业务员
	 */
	List<Emp> selectEmpByCompany(String companyid);
	
    int deleteByPrimaryKey(String empid);

    int insertSelective(Emp record);

    Emp selectByPrimaryKey(String empid);

    int updateByPrimaryKeySelective(Emp record);

}