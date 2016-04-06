package com.server.pojo.entity;

import java.sql.Date;
/**
 * 标品库 实体类
 *@author ZhangRuiLong
 */
public class Scant
{
   /**
    * 标品ID,主键
    */
   private String scantid; 
   /**
    * 编码
    */
   private String scantcode;   
   /**
    * 名称
    */
   private String scantname;   
   /**
    * 描述
    */
   private String scantdetail;   
   /**
    * 规格
    */
   private String scantunits;   
   /**
    * 小类ID
    */
   private String scantclass;   
   /**
    * 图片
    */
   private String scantimage;   
   /**
    * 状态
    */
   private String scantstatue;   
   /**
    * 关联小类
    */
   private Goodsclass goodsclass;
    //属性方法	    
     /**
	 *设置主键"标品ID"属性
	 *@param scantid 实体的Scantid属性
	 */
	public void setScantid(String scantid)
	{
		this.scantid = scantid;
	}
	
	/**
	 *获取主键"标品ID"属性
	 */
	public String getScantid()
	{
		return this.scantid;
	}

	/**
	 *设置"编码"属性
	 *@param scantcode 实体的Scantcode属性
	 */
	public void setScantcode(String scantcode)
	{
		this.scantcode = scantcode;
	}
	
	/**
	 *获取"编码"属性
	 */
	public String getScantcode()
	{
		return this.scantcode;
	}	   

	/**
	 *设置"名称"属性
	 *@param scantname 实体的Scantname属性
	 */
	public void setScantname(String scantname)
	{
		this.scantname = scantname;
	}
	
	/**
	 *获取"名称"属性
	 */
	public String getScantname()
	{
		return this.scantname;
	}	   

	/**
	 *设置"描述"属性
	 *@param scantdetail 实体的Scantdetail属性
	 */
	public void setScantdetail(String scantdetail)
	{
		this.scantdetail = scantdetail;
	}
	
	/**
	 *获取"描述"属性
	 */
	public String getScantdetail()
	{
		return this.scantdetail;
	}	   

	/**
	 *设置"规格"属性
	 *@param scantunits 实体的Scantunits属性
	 */
	public void setScantunits(String scantunits)
	{
		this.scantunits = scantunits;
	}
	
	/**
	 *获取"规格"属性
	 */
	public String getScantunits()
	{
		return this.scantunits;
	}	   

	/**
	 *设置"小类ID"属性
	 *@param scantclass 实体的Scantclass属性
	 */
	public void setScantclass(String scantclass)
	{
		this.scantclass = scantclass;
	}
	
	/**
	 *获取"小类ID"属性
	 */
	public String getScantclass()
	{
		return this.scantclass;
	}	   

	/**
	 *设置"图片"属性
	 *@param scantimage 实体的Scantimage属性
	 */
	public void setScantimage(String scantimage)
	{
		this.scantimage = scantimage;
	}
	
	/**
	 *获取"图片"属性
	 */
	public String getScantimage()
	{
		return this.scantimage;
	}	   

	/**
	 *设置"状态"属性
	 *@param scantstatue 实体的Scantstatue属性
	 */
	public void setScantstatue(String scantstatue)
	{
		this.scantstatue = scantstatue;
	}
	
	/**
	 *获取"状态"属性
	 */
	public String getScantstatue()
	{
		return this.scantstatue;
	}	   
	
	public Goodsclass getGoodsclass() {
		return goodsclass;
	}

	public void setGoodsclass(Goodsclass goodsclass) {
		this.goodsclass = goodsclass;
	}

	public Scant() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Scant(
		String scantid
	 	,String scantcode
	 	,String scantname
	 	,String scantdetail
	 	,String scantunits
	 	,String scantclass
	 	,String scantimage
	 	,String scantstatue
		 ){
		super();
		this.scantid = scantid;
	 	this.scantcode = scantcode;
	 	this.scantname = scantname;
	 	this.scantdetail = scantdetail;
	 	this.scantunits = scantunits;
	 	this.scantclass = scantclass;
	 	this.scantimage = scantimage;
	 	this.scantstatue = scantstatue;
	}
}

