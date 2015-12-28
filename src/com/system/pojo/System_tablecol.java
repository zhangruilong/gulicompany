package com.system.pojo;

import java.sql.Date;
/**
 * 数据字典 实体类
 *@author ZhangRuiLong
 */
public class System_tablecol
{
   /**
    * ID,主键
    */
   private String id; 
   /**
    * 录入表代码
    */
   private String tablecode;   
   /**
    * 录入表名称
    */
   private String tablename;   
   /**
    * 字段序号
    */
   private int colno;   
   /**
    * 字段列名
    */
   private String colcode;   
   /**
    * 字段中文名
    */
   private String colname;   
   /**
    * 数据类型
    */
   private String coltype;   
   /**
    * 字段长度
    */
   private int collength;   
   /**
    * 字段默认值
    */
   private String coldefault;   
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
	 *设置"录入表代码"属性
	 *@param tablecode 实体的Tablecode属性
	 */
	public void setTablecode(String tablecode)
	{
		this.tablecode = tablecode;
	}
	
	/**
	 *获取"录入表代码"属性
	 */
	public String getTablecode()
	{
		return this.tablecode;
	}	   

	/**
	 *设置"录入表名称"属性
	 *@param tablename 实体的Tablename属性
	 */
	public void setTablename(String tablename)
	{
		this.tablename = tablename;
	}
	
	/**
	 *获取"录入表名称"属性
	 */
	public String getTablename()
	{
		return this.tablename;
	}	   

	/**
	 *设置"字段序号"属性
	 *@param colno 实体的Colno属性
	 */
	public void setColno(int colno)
	{
		this.colno = colno;
	}
	
	/**
	 *获取"字段序号"属性
	 */
	public int getColno()
	{
		return this.colno;
	}	   

	/**
	 *设置"字段列名"属性
	 *@param colcode 实体的Colcode属性
	 */
	public void setColcode(String colcode)
	{
		this.colcode = colcode;
	}
	
	/**
	 *获取"字段列名"属性
	 */
	public String getColcode()
	{
		return this.colcode;
	}	   

	/**
	 *设置"字段中文名"属性
	 *@param colname 实体的Colname属性
	 */
	public void setColname(String colname)
	{
		this.colname = colname;
	}
	
	/**
	 *获取"字段中文名"属性
	 */
	public String getColname()
	{
		return this.colname;
	}	   

	/**
	 *设置"数据类型"属性
	 *@param coltype 实体的Coltype属性
	 */
	public void setColtype(String coltype)
	{
		this.coltype = coltype;
	}
	
	/**
	 *获取"数据类型"属性
	 */
	public String getColtype()
	{
		return this.coltype;
	}	   

	/**
	 *设置"字段长度"属性
	 *@param collength 实体的Collength属性
	 */
	public void setCollength(int collength)
	{
		this.collength = collength;
	}
	
	/**
	 *获取"字段长度"属性
	 */
	public int getCollength()
	{
		return this.collength;
	}	   

	/**
	 *设置"字段默认值"属性
	 *@param coldefault 实体的Coldefault属性
	 */
	public void setColdefault(String coldefault)
	{
		this.coldefault = coldefault;
	}
	
	/**
	 *获取"字段默认值"属性
	 */
	public String getColdefault()
	{
		return this.coldefault;
	}	   
	public System_tablecol() {
		super();
		// TODO Auto-generated constructor stub
	}
	public System_tablecol(
		String id
	 	,String tablecode
	 	,String tablename
	 	,int colno
	 	,String colcode
	 	,String colname
	 	,String coltype
	 	,int collength
	 	,String coldefault
		 ){
		super();
		this.id = id;
	 	this.tablecode = tablecode;
	 	this.tablename = tablename;
	 	this.colno = colno;
	 	this.colcode = colcode;
	 	this.colname = colname;
	 	this.coltype = coltype;
	 	this.collength = collength;
	 	this.coldefault = coldefault;
	}
}

