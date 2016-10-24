package com.server.dao.mapper;

import java.util.List;

import com.server.pojo.entity.System_temprule;

public interface System_tempruleMapper {
	/**
	 * 根据供应商id查找模板
	 */
	List<System_temprule> selectByComid(String comid);

    int deleteByPrimaryKey(String id);

    int insertSelective(System_temprule record);

    System_temprule selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(System_temprule record);
}