package com.server.dao.mapper;

import java.util.List;

import com.server.pojo.entity.Scant;

public interface ScantMapper {
	/**
	 * 查询全部 标品和小类名称
	 */
	List<Scant> selectAllScant();
	/**
	 * 根据主键删除
	 */
    int deleteByPrimaryKey(String scantid);
    /**
     * 选择性添加
     */
    int insertSelective(Scant record);
    /**
     * 根据主键查询
     */
    Scant selectByPrimaryKey(String scantid);
    /**
     * 根据主键选择性修改
     */
    int updateByPrimaryKeySelective(Scant record);
}