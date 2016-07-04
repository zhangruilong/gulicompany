package com.server.dao.mapper;

import java.util.List;

import com.server.pojo.entity.Emp;

public interface EmpMapper {
	/**
	 * 根据供应商id查询业务员
	 */
	List<Emp> selectEmpByCompany(String companyid);
	
    int deleteByPrimaryKey(String empid);

    int insertSelective(Emp record);

    Emp selectByPrimaryKey(String empid);

    int updateByPrimaryKeySelective(Emp record);

}