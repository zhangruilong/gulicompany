package com.server.pojo;

import java.sql.Date;
/**
 * 收藏 实体类
 *@author ZhangRuiLong
 */
public class Collect
{
   /**
    * 收藏ID,主键
    */
   private String collectid; 
   /**
    * 商品ID
    */
   private String collectgoods;   
   /**
    * 客户ID
    */
   private String collectcustomer;   
   /**
    * 描述
    */
   private String collectdetail;   
   /**
    * 创建时间
    */
   private String createtime;   
   /**
    * 关联商品(一对一)
    */
   private Goods goods;
    //属性方法	    
     /**
	 *设置主键"收藏ID"属性
	 *@param collectid 实体的Collectid属性
	 */
	public void setCollectid(String collectid)
	{
		this.collectid = collectid;
	}
	
	/**
	 *获取主键"收藏ID"属性
	 */
	public String getCollectid()
	{
		return this.collectid;
	}

	/**
	 *设置"商品ID"属性
	 *@param collectgoods 实体的Collectgoods属性
	 */
	public void setCollectgoods(String collectgoods)
	{
		this.collectgoods = collectgoods;
	}
	
	/**
	 *获取"商品ID"属性
	 */
	public String getCollectgoods()
	{
		return this.collectgoods;
	}	   

	/**
	 *设置"客户ID"属性
	 *@param collectcustomer 实体的Collectcustomer属性
	 */
	public void setCollectcustomer(String collectcustomer)
	{
		this.collectcustomer = collectcustomer;
	}
	
	/**
	 *获取"客户ID"属性
	 */
	public String getCollectcustomer()
	{
		return this.collectcustomer;
	}	   

	/**
	 *设置"描述"属性
	 *@param collectdetail 实体的Collectdetail属性
	 */
	public void setCollectdetail(String collectdetail)
	{
		this.collectdetail = collectdetail;
	}
	
	/**
	 *获取"描述"属性
	 */
	public String getCollectdetail()
	{
		return this.collectdetail;
	}	   

	/**
	 *设置"创建时间"属性
	 *@param createtime 实体的Createtime属性
	 */
	public void setCreatetime(String createtime)
	{
		this.createtime = createtime;
	}
	
	/**
	 *获取"创建时间"属性
	 */
	public String getCreatetime()
	{
		return this.createtime;
	}	   
	
	
	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Collect() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Collect(
		String collectid
	 	,String collectgoods
	 	,String collectcustomer
	 	,String collectdetail
	 	,String createtime
		 ){
		super();
		this.collectid = collectid;
	 	this.collectgoods = collectgoods;
	 	this.collectcustomer = collectcustomer;
	 	this.collectdetail = collectdetail;
	 	this.createtime = createtime;
	}
}

