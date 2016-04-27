package com.server.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.server.pojo.entity.Goods;
/**
 * 商品
 * @author taolichao
 *
 */
public interface GoodsMapper {
	/**
	 * 根据编码查询商品
	 */
	List<Goods> selectByGoods(String goodscode);
	/**
	 * 查询信息总条数
	 */
	Integer selectByConditionCount(Goods goods);
	/**
     * 根据分页查询
     */
    List<Goods> selectByCondition(@Param("goods") Goods goods,
    		@Param("nowpage") Integer nowpage,@Param("pagesize") Integer pagesize);
	/**
	 * 根据主键删除
	 */
    int deleteByPrimaryKey(String goodsid);
    /**
     * 选择性添加
     */
    int insertSelective(Goods record);
    /**
     * 根据主键查询商品和价格和类名 
     */
    Goods selectByPrimaryKey(String goodsid);
    /**
     * 根据主键选择性修改
     */
    int updateByPrimaryKeySelective(Goods record);
}