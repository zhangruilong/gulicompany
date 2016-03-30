package com.server.dao.mapper;

import com.server.pojo.entity.Feedback;

public interface FeedbackMapper {
	/**
	 * 根据主键删除
	 */
    int deleteByPrimaryKey(String feedbackid);
    /**
     * 选择性添加
     */
    int insertSelective(Feedback record);
    /**
     * 根据主键查询
     */
    Feedback selectByPrimaryKey(String feedbackid);
    /**
     * 根据主键选择性修改
     */
    int updateByPrimaryKeySelective(Feedback record);
}