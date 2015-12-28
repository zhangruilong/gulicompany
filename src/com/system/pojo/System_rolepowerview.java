package com.system.pojo;

import java.sql.Date;
/**
 * system_rolepowerview 实体类
 *@author ZhangRuiLong
 */
public class System_rolepowerview
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
    * powerid
    */
   private String powerid;   
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
    * powercode
    */
   private String powercode;   
   /**
    * powername
    */
   private String powername;   
   /**
    * powerdetail
    */
   private String powerdetail;   
   /**
    * powerparentname
    */
   private String powerparentname;   
   /**
    * powermenulevel
    */
   private String powermenulevel;   
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
	 *设置"powerid"属性
	 *@param powerid 实体的Powerid属性
	 */
	public void setPowerid(String powerid)
	{
		this.powerid = powerid;
	}
	
	/**
	 *获取"powerid"属性
	 */
	public String getPowerid()
	{
		return this.powerid;
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
	 *设置"powercode"属性
	 *@param powercode 实体的Powercode属性
	 */
	public void setPowercode(String powercode)
	{
		this.powercode = powercode;
	}
	
	/**
	 *获取"powercode"属性
	 */
	public String getPowercode()
	{
		return this.powercode;
	}	   

	/**
	 *设置"powername"属性
	 *@param powername 实体的Powername属性
	 */
	public void setPowername(String powername)
	{
		this.powername = powername;
	}
	
	/**
	 *获取"powername"属性
	 */
	public String getPowername()
	{
		return this.powername;
	}	   

	/**
	 *设置"powerdetail"属性
	 *@param powerdetail 实体的Powerdetail属性
	 */
	public void setPowerdetail(String powerdetail)
	{
		this.powerdetail = powerdetail;
	}
	
	/**
	 *获取"powerdetail"属性
	 */
	public String getPowerdetail()
	{
		return this.powerdetail;
	}	   

	/**
	 *设置"powerparentname"属性
	 *@param powerparentname 实体的Powerparentname属性
	 */
	public void setPowerparentname(String powerparentname)
	{
		this.powerparentname = powerparentname;
	}
	
	/**
	 *获取"powerparentname"属性
	 */
	public String getPowerparentname()
	{
		return this.powerparentname;
	}	   

	/**
	 *设置"powermenulevel"属性
	 *@param powermenulevel 实体的Powermenulevel属性
	 */
	public void setPowermenulevel(String powermenulevel)
	{
		this.powermenulevel = powermenulevel;
	}
	
	/**
	 *获取"powermenulevel"属性
	 */
	public String getPowermenulevel()
	{
		return this.powermenulevel;
	}	   
	public System_rolepowerview() {
		super();
		// TODO Auto-generated constructor stub
	}
	public System_rolepowerview(
		String id
	 	,String roleid
	 	,String powerid
	 	,String rolecode
	 	,String rolename
	 	,String roledetail
	 	,String powercode
	 	,String powername
	 	,String powerdetail
	 	,String powerparentname
	 	,String powermenulevel
		 ){
		super();
		this.id = id;
	 	this.roleid = roleid;
	 	this.powerid = powerid;
	 	this.rolecode = rolecode;
	 	this.rolename = rolename;
	 	this.roledetail = roledetail;
	 	this.powercode = powercode;
	 	this.powername = powername;
	 	this.powerdetail = powerdetail;
	 	this.powerparentname = powerparentname;
	 	this.powermenulevel = powermenulevel;
	}
}

