package com.server.dao.mapper;

import java.util.List;

import com.server.pojo.entity.Givegoods;

public interface GivegoodsMapper {
	/**
	 * 根据编码查询
	 */
	List<Givegoods> selectByCode(String givegoodscode);
	/**
	 * 根据供应商所在地区查询买赠商品
	 */
	List<Givegoods> selectByCompanyXian(Givegoods givegoods);
	/**
	 * 根据主键删除
	 */
    int deleteByPrimaryKey(String givegoodsid);
    /**
     * 选择性添加
     */
    int insertSelective(Givegoods record);
    /**
     * 根据主键查询
     */
    Givegoods selectByPrimaryKey(String givegoodsid);
    /**
     * 根据主键选择性修改
     */
    int updateByPrimaryKeySelective(Givegoods record);
}