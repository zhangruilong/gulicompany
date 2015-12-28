package com.system.pojo;

import java.sql.Date;
/**
 * system_power_quickview 实体类
 *@author ZhangRuiLong
 */
public class System_power_quickview
{
   /**
    * id,主键
    */
   private String id; 
   /**
    * userid
    */
   private String userid;   
   /**
    * powerid
    */
   private String powerid;   
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
    * parentname
    */
   private String parentname;   
   /**
    * menulevel
    */
   private String menulevel;   
   /**
    * entrance
    */
   private String entrance;   
   /**
    * hreftarget
    */
   private String hreftarget;   
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
	 *设置"parentname"属性
	 *@param parentname 实体的Parentname属性
	 */
	public void setParentname(String parentname)
	{
		this.parentname = parentname;
	}
	
	/**
	 *获取"parentname"属性
	 */
	public String getParentname()
	{
		return this.parentname;
	}	   

	/**
	 *设置"menulevel"属性
	 *@param menulevel 实体的Menulevel属性
	 */
	public void setMenulevel(String menulevel)
	{
		this.menulevel = menulevel;
	}
	
	/**
	 *获取"menulevel"属性
	 */
	public String getMenulevel()
	{
		return this.menulevel;
	}	   

	/**
	 *设置"entrance"属性
	 *@param entrance 实体的Entrance属性
	 */
	public void setEntrance(String entrance)
	{
		this.entrance = entrance;
	}
	
	/**
	 *获取"entrance"属性
	 */
	public String getEntrance()
	{
		return this.entrance;
	}	   

	/**
	 *设置"hreftarget"属性
	 *@param hreftarget 实体的Hreftarget属性
	 */
	public void setHreftarget(String hreftarget)
	{
		this.hreftarget = hreftarget;
	}
	
	/**
	 *获取"hreftarget"属性
	 */
	public String getHreftarget()
	{
		return this.hreftarget;
	}	   
	public System_power_quickview() {
		super();
		// TODO Auto-generated constructor stub
	}
	public System_power_quickview(
		String id
	 	,String userid
	 	,String powerid
	 	,String code
	 	,String name
	 	,String detail
	 	,String parentname
	 	,String menulevel
	 	,String entrance
	 	,String hreftarget
		 ){
		super();
		this.id = id;
	 	this.userid = userid;
	 	this.powerid = powerid;
	 	this.code = code;
	 	this.name = name;
	 	this.detail = detail;
	 	this.parentname = parentname;
	 	this.menulevel = menulevel;
	 	this.entrance = entrance;
	 	this.hreftarget = hreftarget;
	}
}

