package com.server.pojo.entity;

import java.sql.Date;
/**
 * 大小类 实体类
 *@author ZhangRuiLong
 */
public class Goodsclass
{
   /**
    * 大小类ID,主键
    */
   private String goodsclassid; 
   /**
    * 编码
    */
   private String goodsclasscode;   
   /**
    * 名称
    */
   private String goodsclassname;   
   /**
    * 父类
    */
   private String goodsclassparent;   
   /**
    * 描述
    */
   private String goodsclassdetail;   
   /**
    * 状态
    */
   private String goodsclassstatue;   
    //属性方法	    
     /**
	 *设置主键"大小类ID"属性
	 *@param goodsclassid 实体的Goodsclassid属性
	 */
	public void setGoodsclassid(String goodsclassid)
	{
		this.goodsclassid = goodsclassid;
	}
	
	/**
	 *获取主键"大小类ID"属性
	 */
	public String getGoodsclassid()
	{
		return this.goodsclassid;
	}

	/**
	 *设置"编码"属性
	 *@param goodsclasscode 实体的Goodsclasscode属性
	 */
	public void setGoodsclasscode(String goodsclasscode)
	{
		this.goodsclasscode = goodsclasscode;
	}
	
	/**
	 *获取"编码"属性
	 */
	public String getGoodsclasscode()
	{
		return this.goodsclasscode;
	}	   

	/**
	 *设置"名称"属性
	 *@param goodsclassname 实体的Goodsclassname属性
	 */
	public void setGoodsclassname(String goodsclassname)
	{
		this.goodsclassname = goodsclassname;
	}
	
	/**
	 *获取"名称"属性
	 */
	public String getGoodsclassname()
	{
		return this.goodsclassname;
	}	   

	/**
	 *设置"父类"属性
	 *@param goodsclassparent 实体的Goodsclassparent属性
	 */
	public void setGoodsclassparent(String goodsclassparent)
	{
		this.goodsclassparent = goodsclassparent;
	}
	
	/**
	 *获取"父类"属性
	 */
	public String getGoodsclassparent()
	{
		return this.goodsclassparent;
	}	   

	/**
	 *设置"描述"属性
	 *@param goodsclassdetail 实体的Goodsclassdetail属性
	 */
	public void setGoodsclassdetail(String goodsclassdetail)
	{
		this.goodsclassdetail = goodsclassdetail;
	}
	
	/**
	 *获取"描述"属性
	 */
	public String getGoodsclassdetail()
	{
		return this.goodsclassdetail;
	}	   

	/**
	 *设置"状态"属性
	 *@param goodsclassstatue 实体的Goodsclassstatue属性
	 */
	public void setGoodsclassstatue(String goodsclassstatue)
	{
		this.goodsclassstatue = goodsclassstatue;
	}
	
	/**
	 *获取"状态"属性
	 */
	public String getGoodsclassstatue()
	{
		return this.goodsclassstatue;
	}	   
	public Goodsclass() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Goodsclass(
		String goodsclassid
	 	,String goodsclasscode
	 	,String goodsclassname
	 	,String goodsclassparent
	 	,String goodsclassdetail
	 	,String goodsclassstatue
		 ){
		super();
		this.goodsclassid = goodsclassid;
	 	this.goodsclasscode = goodsclasscode;
	 	this.goodsclassname = goodsclassname;
	 	this.goodsclassparent = goodsclassparent;
	 	this.goodsclassdetail = goodsclassdetail;
	 	this.goodsclassstatue = goodsclassstatue;
	}
}

