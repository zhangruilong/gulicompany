package com.server.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.server.pojo.entity.Largecusprice;

public interface LargecuspriceMapper {
	/**
	 * 根据客户id和供应商id查询客户的特殊价格商品(数量)
	 */
	Integer selectLargeCusPriceGoodsCount(Largecusprice largecusprice);
	/**
	 * 根据客户id和供应商id查询客户的特殊价格商品
	 */
	List<Largecusprice> selectLargeCusPriceGoods(@Param("largecusprice") Largecusprice largecusprice,
			@Param("nowpage") Integer nowpage,@Param("pagesize") Integer pagesize);
	/**
	 * 根据主键删除
	 */
    int deleteByPrimaryKey(String largecuspriceid);
    /**
     * 选择性添加
     */
    int insertSelective(Largecusprice record);
    /**
     * 根据主键选择性修改
     */
    int updateByPrimaryKeySelective(Largecusprice record);
}