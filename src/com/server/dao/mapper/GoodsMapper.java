package com.server.dao.mapper;

import java.util.List;

import com.server.pojo.entity.Goods;
/**
 * 商品
 * @author taolichao
 *
 */
public interface GoodsMapper {
	/**
     * 根据条件查询
     */
    List<Goods> selectByCondition(Goods record);
	/**
	 * 根据主键删除
	 */
    int deleteByPrimaryKey(String goodsid);
    /**
     * 选择性添加
     */
    int insertSelective(Goods record);
    /**
     * 根据主键查询
     */
    Goods selectByPrimaryKey(String goodsid);
    /**
     * 根据主键选择性修改
     */
    int updateByPrimaryKeySelective(Goods record);
}