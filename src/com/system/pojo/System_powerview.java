package com.system.pojo;

import java.sql.Date;
/**
 * system_powerview 实体类
 *@author ZhangRuiLong
 */
public class System_powerview
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
    * parentid
    */
   private String parentid;   
   /**
    * menulevel
    */
   private String menulevel;   
   /**
    * entrance
    */
   private String entrance;   
   /**
    * menuorder
    */
   private String menuorder;   
   /**
    * iconcls
    */
   private String iconcls;   
   /**
    * hreftarget
    */
   private String hreftarget;   
   /**
    * parentname
    */
   private String parentname;   
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
	 *设置"menuorder"属性
	 *@param menuorder 实体的Menuorder属性
	 */
	public void setMenuorder(String menuorder)
	{
		this.menuorder = menuorder;
	}
	
	/**
	 *获取"menuorder"属性
	 */
	public String getMenuorder()
	{
		return this.menuorder;
	}	   

	/**
	 *设置"iconcls"属性
	 *@param iconcls 实体的Iconcls属性
	 */
	public void setIconcls(String iconcls)
	{
		this.iconcls = iconcls;
	}
	
	/**
	 *获取"iconcls"属性
	 */
	public String getIconcls()
	{
		return this.iconcls;
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
	public System_powerview() {
		super();
		// TODO Auto-generated constructor stub
	}
	public System_powerview(
		String id
	 	,String code
	 	,String name
	 	,String detail
	 	,String parentid
	 	,String menulevel
	 	,String entrance
	 	,String menuorder
	 	,String iconcls
	 	,String hreftarget
	 	,String parentname
		 ){
		super();
		this.id = id;
	 	this.code = code;
	 	this.name = name;
	 	this.detail = detail;
	 	this.parentid = parentid;
	 	this.menulevel = menulevel;
	 	this.entrance = entrance;
	 	this.menuorder = menuorder;
	 	this.iconcls = iconcls;
	 	this.hreftarget = hreftarget;
	 	this.parentname = parentname;
	}
}

