package com.system.pojo;

import java.sql.Date;
/**
 * 角色权限 实体类
 *@author ZhangRuiLong
 */
public class System_rolepower
{
   /**
    * ID,主键
    */
   private String id; 
   /**
    * ROLEID
    */
   private String roleid;   
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
	 *设置"ROLEID"属性
	 *@param roleid 实体的Roleid属性
	 */
	public void setRoleid(String roleid)
	{
		this.roleid = roleid;
	}
	
	/**
	 *获取"ROLEID"属性
	 */
	public String getRoleid()
	{
		return this.roleid;
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
	public System_rolepower() {
		super();
		// TODO Auto-generated constructor stub
	}
	public System_rolepower(
		String id
	 	,String roleid
	 	,String powerid
		 ){
		super();
		this.id = id;
	 	this.roleid = roleid;
	 	this.powerid = powerid;
	}
}

