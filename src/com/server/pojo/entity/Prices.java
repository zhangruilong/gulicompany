package com.server.pojo.entity;

import java.sql.Date;
/**
 * 价格体系 实体类
 *@author ZhangRuiLong
 */
public class Prices
{
   /**
    * 价格体系ID,主键
    */
   private String pricesid; 
   /**
    * 商品ID
    */
   private String pricesgoods;   
   /**
    * 分类
    */
   private String pricesclass;   
   /**
    * 等级
    */
   private int priceslevel;   
   /**
    * 单品价
    */
   private Float pricesprice;   
   /**
    * 单品单位
    */
   private String pricesunit;   
   /**
    * 套装价
    */
   private Float pricesprice2;   
   /**
    * 套装单位
    */
   private String pricesunit2;   
   /**
    * 创建时间
    */
   private String createtime;   
   /**
    * 修改时间
    */
   private String updtime;   
   /**
    * 创建人
    */
   private String creator;   
   /**
    * 修改人
    */
   private String updor;   
    //属性方法	    
     /**
	 *设置主键"价格体系ID"属性
	 *@param pricesid 实体的Pricesid属性
	 */
	public void setPricesid(String pricesid)
	{
		this.pricesid = pricesid;
	}
	
	/**
	 *获取主键"价格体系ID"属性
	 */
	public String getPricesid()
	{
		return this.pricesid;
	}

	/**
	 *设置"商品ID"属性
	 *@param pricesgoods 实体的Pricesgoods属性
	 */
	public void setPricesgoods(String pricesgoods)
	{
		this.pricesgoods = pricesgoods;
	}
	
	/**
	 *获取"商品ID"属性
	 */
	public String getPricesgoods()
	{
		return this.pricesgoods;
	}	   

	/**
	 *设置"分类"属性
	 *@param pricesclass 实体的Pricesclass属性
	 */
	public void setPricesclass(String pricesclass)
	{
		this.pricesclass = pricesclass;
	}
	
	/**
	 *获取"分类"属性
	 */
	public String getPricesclass()
	{
		return this.pricesclass;
	}	   

	/**
	 *设置"等级"属性
	 *@param priceslevel 实体的Priceslevel属性
	 */
	public void setPriceslevel(int priceslevel)
	{
		this.priceslevel = priceslevel;
	}
	
	/**
	 *获取"等级"属性
	 */
	public int getPriceslevel()
	{
		return this.priceslevel;
	}	   

	/**
	 *设置"单品价"属性
	 *@param pricesprice 实体的Pricesprice属性
	 */
	public void setPricesprice(Float pricesprice)
	{
		this.pricesprice = pricesprice;
	}
	
	/**
	 *获取"单品价"属性
	 */
	public Float getPricesprice()
	{
		return this.pricesprice;
	}	   

	/**
	 *设置"单品单位"属性
	 *@param pricesunit 实体的Pricesunit属性
	 */
	public void setPricesunit(String pricesunit)
	{
		this.pricesunit = pricesunit;
	}
	
	/**
	 *获取"单品单位"属性
	 */
	public String getPricesunit()
	{
		return this.pricesunit;
	}	   

	/**
	 *设置"套装价"属性
	 *@param pricesprice2 实体的Pricesprice2属性
	 */
	public void setPricesprice2(Float pricesprice2)
	{
		this.pricesprice2 = pricesprice2;
	}
	
	/**
	 *获取"套装价"属性
	 */
	public Float getPricesprice2()
	{
		return this.pricesprice2;
	}	   

	/**
	 *设置"套装单位"属性
	 *@param pricesunit2 实体的Pricesunit2属性
	 */
	public void setPricesunit2(String pricesunit2)
	{
		this.pricesunit2 = pricesunit2;
	}
	
	/**
	 *获取"套装单位"属性
	 */
	public String getPricesunit2()
	{
		return this.pricesunit2;
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

	/**
	 *设置"修改时间"属性
	 *@param updtime 实体的Updtime属性
	 */
	public void setUpdtime(String updtime)
	{
		this.updtime = updtime;
	}
	
	/**
	 *获取"修改时间"属性
	 */
	public String getUpdtime()
	{
		return this.updtime;
	}	   

	/**
	 *设置"创建人"属性
	 *@param creator 实体的Creator属性
	 */
	public void setCreator(String creator)
	{
		this.creator = creator;
	}
	
	/**
	 *获取"创建人"属性
	 */
	public String getCreator()
	{
		return this.creator;
	}	   

	/**
	 *设置"修改人"属性
	 *@param updor 实体的Updor属性
	 */
	public void setUpdor(String updor)
	{
		this.updor = updor;
	}
	
	/**
	 *获取"修改人"属性
	 */
	public String getUpdor()
	{
		return this.updor;
	}	   
	public Prices() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Prices(
		String pricesid
	 	,String pricesgoods
	 	,String pricesclass
	 	,int priceslevel
	 	,Float pricesprice
	 	,String pricesunit
	 	,Float pricesprice2
	 	,String pricesunit2
	 	,String createtime
	 	,String updtime
	 	,String creator
	 	,String updor
		 ){
		super();
		this.pricesid = pricesid;
	 	this.pricesgoods = pricesgoods;
	 	this.pricesclass = pricesclass;
	 	this.priceslevel = priceslevel;
	 	this.pricesprice = pricesprice;
	 	this.pricesunit = pricesunit;
	 	this.pricesprice2 = pricesprice2;
	 	this.pricesunit2 = pricesunit2;
	 	this.createtime = createtime;
	 	this.updtime = updtime;
	 	this.creator = creator;
	 	this.updor = updor;
	}
}

