package com.system.pojo;

import java.sql.Date;
/**
 * 快捷菜单 实体类
 *@author ZhangRuiLong
 */
public class System_power
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
   /**
    * 父节点
    */
   private String parentid;   
   /**
    * 类型
    */
   private String menulevel;   
   /**
    * 入口
    */
   private String entrance;   
   /**
    * 菜单顺序
    */
   private String menuorder;   
   /**
    * 图片
    */
   private String iconcls;   
   /**
    * 打开方式
    */
   private String hreftarget;   
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

	/**
	 *设置"父节点"属性
	 *@param parentid 实体的Parentid属性
	 */
	public void setParentid(String parentid)
	{
		this.parentid = parentid;
	}
	
	/**
	 *获取"父节点"属性
	 */
	public String getParentid()
	{
		return this.parentid;
	}	   

	/**
	 *设置"类型"属性
	 *@param menulevel 实体的Menulevel属性
	 */
	public void setMenulevel(String menulevel)
	{
		this.menulevel = menulevel;
	}
	
	/**
	 *获取"类型"属性
	 */
	public String getMenulevel()
	{
		return this.menulevel;
	}	   

	/**
	 *设置"入口"属性
	 *@param entrance 实体的Entrance属性
	 */
	public void setEntrance(String entrance)
	{
		this.entrance = entrance;
	}
	
	/**
	 *获取"入口"属性
	 */
	public String getEntrance()
	{
		return this.entrance;
	}	   

	/**
	 *设置"菜单顺序"属性
	 *@param menuorder 实体的Menuorder属性
	 */
	public void setMenuorder(String menuorder)
	{
		this.menuorder = menuorder;
	}
	
	/**
	 *获取"菜单顺序"属性
	 */
	public String getMenuorder()
	{
		return this.menuorder;
	}	   

	/**
	 *设置"图片"属性
	 *@param iconcls 实体的Iconcls属性
	 */
	public void setIconcls(String iconcls)
	{
		this.iconcls = iconcls;
	}
	
	/**
	 *获取"图片"属性
	 */
	public String getIconcls()
	{
		return this.iconcls;
	}	   

	/**
	 *设置"打开方式"属性
	 *@param hreftarget 实体的Hreftarget属性
	 */
	public void setHreftarget(String hreftarget)
	{
		this.hreftarget = hreftarget;
	}
	
	/**
	 *获取"打开方式"属性
	 */
	public String getHreftarget()
	{
		return this.hreftarget;
	}	   
	public System_power() {
		super();
		// TODO Auto-generated constructor stub
	}
	public System_power(
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
	}
}

