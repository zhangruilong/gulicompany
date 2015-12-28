package com.system.pojo;

import java.sql.Date;
/**
 * 快捷菜单 实体类
 *@author ZhangRuiLong
 */
public class System_power_quick
{
   /**
    * ID,主键
    */
   private String id; 
   /**
    * USERID
    */
   private String userid;   
   /**
    * POWERID
    */
   private String powerid;   
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
	 *设置"USERID"属性
	 *@param userid 实体的Userid属性
	 */
	public void setUserid(String userid)
	{
		this.userid = userid;
	}
	
	/**
	 *获取"USERID"属性
	 */
	public String getUserid()
	{
		return this.userid;
	}	   

	/**
	 *设置"POWERID"属性
	 *@param powerid 实体的Powerid属性
	 */
	public void setPowerid(String powerid)
	{
		this.powerid = powerid;
	}
	
	/**
	 *获取"POWERID"属性
	 */
	public String getPowerid()
	{
		return this.powerid;
	}	   
	public System_power_quick() {
		super();
		// TODO Auto-generated constructor stub
	}
	public System_power_quick(
		String id
	 	,String userid
	 	,String powerid
		 ){
		super();
		this.id = id;
	 	this.userid = userid;
	 	this.powerid = powerid;
	}
}

