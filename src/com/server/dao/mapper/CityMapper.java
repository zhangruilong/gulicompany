package com.server.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.server.pojo.City;
/**
 * 
 * @author taolichao
 *
 */
public interface CityMapper {
	/**
     * 根据主键查询
     */
	List<City> selectAllCity();
	/**
	 * 根据主键删除
	 */
    int deleteByPrimaryKey(String cityid);
    /**
     * 选择性添加
     */
    int insertSelective(City record);
    /**
     * 根据主键查询
     */
    City selectByPrimaryKey(String cityid);
    /**
     * 根据主键选择性修改
     */
    int updateByPrimaryKeySelective(City record);
}