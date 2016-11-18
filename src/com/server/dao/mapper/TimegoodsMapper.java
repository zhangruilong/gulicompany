package com.server.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.server.pojo.entity.Timegoods;

public interface TimegoodsMapper {
	/**
	 * 根据编码和规格和经销商查询
	 */
	Timegoods selectByCodeUnitsCom(@Param("comid") String comid,@Param("timegoodscode") String timegoodscode,
			@Param("timegoodsunits") String timegoodsunits);
	/**
	 * 根据编码和规格和经销商查询(数量)
	 */
	Integer selectComRepeatGoods(@Param("comid") String comid,@Param("timegoodscode") String timegoodscode,
			@Param("timegoodsunits") String timegoodsunits);
	/**
	 * 根据编码查询
	 */
	List<Timegoods> selectByCode(String timegoodscode);
	/**
     * 根据条件查询(数量)
     */
    Integer selectByConditionCount(Timegoods timegoods);
	/**
     * 根据条件查询
     */
    List<Timegoods> selectByCondition(@Param("timegoods") Timegoods timegoods,
    		@Param("nowpage") Integer nowpage,@Param("pagesize") Integer pagesize);
	/**
	 * 根据主键删除
	 */
    int deleteByPrimaryKey(String timegoodsid);
    /**
     * 选择性添加
     */
    int insertSelective(Timegoods record);
    /**
     * 根据主键查询
     */
    Timegoods selectByPrimaryKey(String timegoodsid);
    /**
     * 根据主键选择性修改
     */
    int updateByPrimaryKeySelective(Timegoods record);

}