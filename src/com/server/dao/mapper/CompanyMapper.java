package com.server.dao.mapper;

import java.util.List;

import com.server.pojo.entity.Company;

public interface CompanyMapper {
	/**
	 * 根据条件查询经销商
	 */
	List<Company> selectCompanyByCondition(Company company);
	/**
	 * 根据主键删除
	 */
    int deleteByPrimaryKey(String companyid);
    /**
     * 选择性添加
     */
    int insertSelective(Company record);
    /**
     * 根据主键查询
     */
    Company selectByPrimaryKey(String companyid);
    /**
     * 根据主键选择性修改
     */
    int updateByPrimaryKeySelective(Company record);
}