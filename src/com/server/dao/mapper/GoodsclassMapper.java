package com.server.dao.mapper;

import java.util.List;

import com.server.pojo.entity.Goodsclass;

public interface GoodsclassMapper {
	/**
	 * 得到全部商品小类(id和name)
	 */
	List<Goodsclass> selectAllGoodsclass();
	/**
	 * 根据主键删除
	 */
    int deleteByPrimaryKey(String goodsclassid);
    /**
     * 选择性添加
     */
    int insertSelective(Goodsclass record);
    /**
     * 根据主键查询
     */
    Goodsclass selectByPrimaryKey(String goodsclassid);
    /**
     * 根据主键选择性修改
     */
    int updateByPrimaryKeySelective(Goodsclass record);
}