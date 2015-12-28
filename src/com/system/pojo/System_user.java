package com.system.pojo;

import java.sql.Date;
/**
 * 用户 实体类
 *@author ZhangRuiLong
 */
public class System_user
{
   /**
    * ID,主键
    */
   private String id; 
   /**
    * 登录名
    */
   private String loginname;   
   /**
    * 密码
    */
   private String password;   
   /**
    * 用户名
    */
   private String username;   
   /**
    * 状态
    */
   private String statue;   
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
	 *设置"登录名"属性
	 *@param loginname 实体的Loginname属性
	 */
	public void setLoginname(String loginname)
	{
		this.loginname = loginname;
	}
	
	/**
	 *获取"登录名"属性
	 */
	public String getLoginname()
	{
		return this.loginname;
	}	   

	/**
	 *设置"密码"属性
	 *@param password 实体的Password属性
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	/**
	 *获取"密码"属性
	 */
	public String getPassword()
	{
		return this.password;
	}	   

	/**
	 *设置"用户名"属性
	 *@param username 实体的Username属性
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	/**
	 *获取"用户名"属性
	 */
	public String getUsername()
	{
		return this.username;
	}	   

	/**
	 *设置"状态"属性
	 *@param statue 实体的Statue属性
	 */
	public void setStatue(String statue)
	{
		this.statue = statue;
	}
	
	/**
	 *获取"状态"属性
	 */
	public String getStatue()
	{
		return this.statue;
	}	   
	public System_user() {
		super();
		// TODO Auto-generated constructor stub
	}
	public System_user(
		String id
	 	,String loginname
	 	,String password
	 	,String username
	 	,String statue
		 ){
		super();
		this.id = id;
	 	this.loginname = loginname;
	 	this.password = password;
	 	this.username = username;
	 	this.statue = statue;
	}
}

