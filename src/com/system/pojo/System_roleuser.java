package com.system.pojo;

import java.sql.Date;
/**
 * 角色人员 实体类
 *@author ZhangRuiLong
 */
public class System_roleuser
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
    * USERID
    */
   private String userid;   
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
	public System_roleuser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public System_roleuser(
		String id
	 	,String roleid
	 	,String userid
		 ){
		super();
		this.id = id;
	 	this.roleid = roleid;
	 	this.userid = userid;
	}
}

