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
	 * 根据商品编号查询商品
	 */
	Goods selectByGoodsCode(@Param("goodscode") String goodscode,@Param("comid") String comid,@Param("goodsunits") String goodsunits);
	/**
	 * 大客户商品和价格(信息条数)
	 */
	Integer selectlagerCusGoodsCount(@Param("goods") Goods goods,@Param("customerid") String customerid);
	/**
	 * 大客户商品和价格
	 */
	List<Goods> selectlagerCusGoods(@Param("goods") Goods goods,
    		@Param("nowpage") Integer nowpage,@Param("pagesize") Integer pagesize,@Param("customerid") String customerid);
	/**
	 * 查询全部餐饮商品(信息条数)
	 */
	Integer selectAllCanyinGoodsCount(Goods goods);
	/**
	 * 查询全部餐饮商品
	 */
	List<Goods> selectAllCanyinGoods(@Param("goods") Goods goods,
    		@Param("nowpage") Integer nowpage,@Param("pagesize") Integer pagesize);
	/**
	 * 根据编码查询商品
	 */
	List<Goods> selectByGoods(@Param("goodscode") String goodscode,@Param("customertype") String customertype,@Param("customerlevel") String customerlevel);
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