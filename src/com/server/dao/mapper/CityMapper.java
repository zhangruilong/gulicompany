package com.server.dao.mapper;

import java.util.List;

import com.server.pojo.entity.City;

public interface CityMapper {
	/**
	 * 查询所有地区的父类
	 */
	List<City> selectAllParent();
	/**
	 * 条件查询
	 */
	List<City> selectByCityparent(City city);
	/**
	 * 查询所有地区
	 */
	List<City> selectAllCity();
	/**
	 * 根据主键删除
	 */
    int deleteByPrimaryKey(String cityid);
    /**
     * 选择性添加
     */
    int insert(City record);
    /**
     * 根据主键查询
     */
    City selectByPrimaryKey(String cityid);
    /**
     * 根据主键选择性修改
     */
    int updateByPrimaryKeySelective(City record);
}