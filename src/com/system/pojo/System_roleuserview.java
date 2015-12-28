package com.system.pojo;

import java.sql.Date;
/**
 * system_roleuserview 实体类
 *@author ZhangRuiLong
 */
public class System_roleuserview
{
   /**
    * id,主键
    */
   private String id; 
   /**
    * roleid
    */
   private String roleid;   
   /**
    * userid
    */
   private String userid;   
   /**
    * type
    */
   private String type;   
   /**
    * rolecode
    */
   private String rolecode;   
   /**
    * rolename
    */
   private String rolename;   
   /**
    * roledetail
    */
   private String roledetail;   
   /**
    * fcode
    */
   private String fcode;   
   /**
    * fname
    */
   private String fname;   
   /**
    * fdetail
    */
   private String fdetail;   
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
	 *设置"roleid"属性
	 *@param roleid 实体的Roleid属性
	 */
	public void setRoleid(String roleid)
	{
		this.roleid = roleid;
	}
	
	/**
	 *获取"roleid"属性
	 */
	public String getRoleid()
	{
		return this.roleid;
	}	   

	/**
	 *设置"userid"属性
	 *@param userid 实体的Userid属性
	 */
	public void setUserid(String userid)
	{
		this.userid = userid;
	}
	
	/**
	 *获取"userid"属性
	 */
	public String getUserid()
	{
		return this.userid;
	}	   

	/**
	 *设置"type"属性
	 *@param type 实体的Type属性
	 */
	public void setType(String type)
	{
		this.type = type;
	}
	
	/**
	 *获取"type"属性
	 */
	public String getType()
	{
		return this.type;
	}	   

	/**
	 *设置"rolecode"属性
	 *@param rolecode 实体的Rolecode属性
	 */
	public void setRolecode(String rolecode)
	{
		this.rolecode = rolecode;
	}
	
	/**
	 *获取"rolecode"属性
	 */
	public String getRolecode()
	{
		return this.rolecode;
	}	   

	/**
	 *设置"rolename"属性
	 *@param rolename 实体的Rolename属性
	 */
	public void setRolename(String rolename)
	{
		this.rolename = rolename;
	}
	
	/**
	 *获取"rolename"属性
	 */
	public String getRolename()
	{
		return this.rolename;
	}	   

	/**
	 *设置"roledetail"属性
	 *@param roledetail 实体的Roledetail属性
	 */
	public void setRoledetail(String roledetail)
	{
		this.roledetail = roledetail;
	}
	
	/**
	 *获取"roledetail"属性
	 */
	public String getRoledetail()
	{
		return this.roledetail;
	}	   

	/**
	 *设置"fcode"属性
	 *@param fcode 实体的Fcode属性
	 */
	public void setFcode(String fcode)
	{
		this.fcode = fcode;
	}
	
	/**
	 *获取"fcode"属性
	 */
	public String getFcode()
	{
		return this.fcode;
	}	   

	/**
	 *设置"fname"属性
	 *@param fname 实体的Fname属性
	 */
	public void setFname(String fname)
	{
		this.fname = fname;
	}
	
	/**
	 *获取"fname"属性
	 */
	public String getFname()
	{
		return this.fname;
	}	   

	/**
	 *设置"fdetail"属性
	 *@param fdetail 实体的Fdetail属性
	 */
	public void setFdetail(String fdetail)
	{
		this.fdetail = fdetail;
	}
	
	/**
	 *获取"fdetail"属性
	 */
	public String getFdetail()
	{
		return this.fdetail;
	}	   
	public System_roleuserview() {
		super();
		// TODO Auto-generated constructor stub
	}
	public System_roleuserview(
		String id
	 	,String roleid
	 	,String userid
	 	,String type
	 	,String rolecode
	 	,String rolename
	 	,String roledetail
	 	,String fcode
	 	,String fname
	 	,String fdetail
		 ){
		super();
		this.id = id;
	 	this.roleid = roleid;
	 	this.userid = userid;
	 	this.type = type;
	 	this.rolecode = rolecode;
	 	this.rolename = rolename;
	 	this.roledetail = roledetail;
	 	this.fcode = fcode;
	 	this.fname = fname;
	 	this.fdetail = fdetail;
	}
}

