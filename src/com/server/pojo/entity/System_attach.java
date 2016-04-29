package com.server.pojo.entity;

import java.sql.Date;
/**
 * 附件 实体类
 *@author ZhangRuiLong
 */
public class System_attach
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
    * 分类
    */
   private String classify;   
   /**
    * 类型
    */
   private String type;   
   /**
    * 大小
    */
   private String attachsize;   
   /**
    * 外键
    */
   private String fid;   
   /**
    * 创建人员
    */
   private String creator;   
   /**
    * 创建时间
    */
   private String createtime;   
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
	 *设置"分类"属性
	 *@param classify 实体的Classify属性
	 */
	public void setClassify(String classify)
	{
		this.classify = classify;
	}
	
	/**
	 *获取"分类"属性
	 */
	public String getClassify()
	{
		return this.classify;
	}	   

	/**
	 *设置"类型"属性
	 *@param type 实体的Type属性
	 */
	public void setType(String type)
	{
		this.type = type;
	}
	
	/**
	 *获取"类型"属性
	 */
	public String getType()
	{
		return this.type;
	}	   

	/**
	 *设置"大小"属性
	 *@param attachsize 实体的Attachsize属性
	 */
	public void setAttachsize(String attachsize)
	{
		this.attachsize = attachsize;
	}
	
	/**
	 *获取"大小"属性
	 */
	public String getAttachsize()
	{
		return this.attachsize;
	}	   

	/**
	 *设置"外键"属性
	 *@param fid 实体的Fid属性
	 */
	public void setFid(String fid)
	{
		this.fid = fid;
	}
	
	/**
	 *获取"外键"属性
	 */
	public String getFid()
	{
		return this.fid;
	}	   

	/**
	 *设置"创建人员"属性
	 *@param creator 实体的Creator属性
	 */
	public void setCreator(String creator)
	{
		this.creator = creator;
	}
	
	/**
	 *获取"创建人员"属性
	 */
	public String getCreator()
	{
		return this.creator;
	}	   

	/**
	 *设置"创建时间"属性
	 *@param createtime 实体的Createtime属性
	 */
	public void setCreatetime(String createtime)
	{
		this.createtime = createtime;
	}
	
	/**
	 *获取"创建时间"属性
	 */
	public String getCreatetime()
	{
		return this.createtime;
	}	   
	public System_attach() {
		super();
		// TODO Auto-generated constructor stub
	}
	public System_attach(
		String id
	 	,String code
	 	,String name
	 	,String detail
	 	,String classify
	 	,String type
	 	,String attachsize
	 	,String fid
	 	,String creator
	 	,String createtime
		 ){
		super();
		this.id = id;
	 	this.code = code;
	 	this.name = name;
	 	this.detail = detail;
	 	this.classify = classify;
	 	this.type = type;
	 	this.attachsize = attachsize;
	 	this.fid = fid;
	 	this.creator = creator;
	 	this.createtime = createtime;
	}
}

