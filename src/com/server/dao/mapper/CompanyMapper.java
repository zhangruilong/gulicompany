package com.server.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.server.pojo.entity.Company;

public interface CompanyMapper {
	/**
     * 登录
     */
    Company selectLogin(@Param("loginname") String loginname,@Param("password") String password);
	/**
	 * 根据 '地区' 查询该地区的 '经销商'和该经销商的 '秒杀商品'
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