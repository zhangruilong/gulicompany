package com.system.pojo;

import java.sql.Date;
/**
 * 角色 实体类
 *@author ZhangRuiLong
 */
public class System_role
{
   /**
    * ID,主键
    */
   private String id; 
   /**
    * 编码
    */
   private String code;   
   /**
    * 名称
    */
   private String name;   
   /**
    * 描述
    */
   private String detail;   
    //属性方法	    
     /**
	 *设置主键"ID"属性
	 *@param id 实体的Id属性
	 */
	public void setId(String id)
	{
		this.id = id;
	}
	
	/**
	 *获取主键"ID"属性
	 */
	public String getId()
	{
		return this.id;
	}

	/**
	 *设置"编码"属性
	 *@param code 实体的Code属性
	 */
	public void setCode(String code)
	{
		this.code = code;
	}
	
	/**
	 *获取"编码"属性
	 */
	public String getCode()
	{
		return this.code;
	}	   

	/**
	 *设置"名称"属性
	 *@param name 实体的Name属性
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 *获取"名称"属性
	 */
	public String getName()
	{
		return this.name;
	}	   

	/**
	 *设置"描述"属性
	 *@param detail 实体的Detail属性
	 */
	public void setDetail(String detail)
	{
		this.detail = detail;
	}
	
	/**
	 *获取"描述"属性
	 */
	public String getDetail()
	{
		return this.detail;
	}	   
	public System_role() {
		super();
		// TODO Auto-generated constructor stub
	}
	public System_role(
		String id
	 	,String code
	 	,String name
	 	,String detail
		 ){
		super();
		this.id = id;
	 	this.code = code;
	 	this.name = name;
	 	this.detail = detail;
	}
}

