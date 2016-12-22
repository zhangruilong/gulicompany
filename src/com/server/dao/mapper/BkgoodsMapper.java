package com.server.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.server.pojo.entity.Bkgoods;

public interface BkgoodsMapper {
	
	/**
	 * 查询重复商品数量
	 */
	Integer selectComRepeatGoods(@Param("comid") String comid,@Param("bkgoodscode") String bkgoodscode,
			@Param("bkgoodsunits") String bkgoodsunits);
	/**
	 * 总信息条数查询
	 */
	int queryBkgoodsCount(Bkgoods bkgoods);
	/**
	 * 分页查询
	 */
	List<Bkgoods> queryBkgoods(@Param("bkgoods") Bkgoods bkgoods,
    		@Param("nowpage") Integer nowpage,@Param("pagesize") Integer pagesize);
	
	int deleteByPrimaryKey(String bkgoodsid);
	
	Bkgoods selectByPrimaryKey(String bkgoodsid);

    int insertSelective(Bkgoods record);

    int updateByPrimaryKeySelective(Bkgoods record);

}