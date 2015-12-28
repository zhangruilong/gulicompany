package com.om.pojo;

import java.sql.Date;
/**
 * om_grouptreeview 实体类
 *@author ZhangRuiLong
 */
public class Om_grouptreeview
{
   /**
    * id,主键
    */
   private String id; 
   /**
    * code
    */
   private String code;   
   /**
    * name
    */
   private String name;   
   /**
    * detail
    */
   private String detail;   
   /**
    * leaf
    */
   private String leaf;   
   /**
    * parentid
    */
   private String parentid;   
    //属性方法	    
     /**
	 *设置主键"id"属性
	 *@param id 实体的Id属性
	 */
	public void setId(String id)
	{
		this.id = id;
	}
	
	/**
	 *获取主键"id"属性
	 */
	public String getId()
	{
		return this.id;
	}

	/**
	 *设置"code"属性
	 *@param code 实体的Code属性
	 */
	public void setCode(String code)
	{
		this.code = code;
	}
	
	/**
	 *获取"code"属性
	 */
	public String getCode()
	{
		return this.code;
	}	   

	/**
	 *设置"name"属性
	 *@param name 实体的Name属性
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 *获取"name"属性
	 */
	public String getName()
	{
		return this.name;
	}	   

	/**
	 *设置"detail"属性
	 *@param detail 实体的Detail属性
	 */
	public void setDetail(String detail)
	{
		this.detail = detail;
	}
	
	/**
	 *获取"detail"属性
	 */
	public String getDetail()
	{
		return this.detail;
	}	   

	/**
	 *设置"leaf"属性
	 *@param leaf 实体的Leaf属性
	 */
	public void setLeaf(String leaf)
	{
		this.leaf = leaf;
	}
	
	/**
	 *获取"leaf"属性
	 */
	public String getLeaf()
	{
		return this.leaf;
	}	   

	/**
	 *设置"parentid"属性
	 *@param parentid 实体的Parentid属性
	 */
	public void setParentid(String parentid)
	{
		this.parentid = parentid;
	}
	
	/**
	 *获取"parentid"属性
	 */
	public String getParentid()
	{
		return this.parentid;
	}	   
	public Om_grouptreeview() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Om_grouptreeview(
		String id
	 	,String code
	 	,String name
	 	,String detail
	 	,String leaf
	 	,String parentid
		 ){
		super();
		this.id = id;
	 	this.code = code;
	 	this.name = name;
	 	this.detail = detail;
	 	this.leaf = leaf;
	 	this.parentid = parentid;
	}
}

