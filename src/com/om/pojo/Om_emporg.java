package com.om.pojo;

import java.sql.Date;
/**
 * 人员机构关系表 实体类
 *@author ZhangRuiLong
 */
public class Om_emporg
{
   /**
    * orgempid,主键
    */
   private String orgempid; 
   /**
    * 机构编号
    */
   private String orgid;   
   /**
    * 人员编号
    */
   private String empid;   
   /**
    * 是否主机构
    */
   private String ismain;   
    //属性方法	    
     /**
	 *设置主键"orgempid"属性
	 *@param orgempid 实体的Orgempid属性
	 */
	public void setOrgempid(String orgempid)
	{
		this.orgempid = orgempid;
	}
	
	/**
	 *获取主键"orgempid"属性
	 */
	public String getOrgempid()
	{
		return this.orgempid;
	}

	/**
	 *设置"机构编号"属性
	 *@param orgid 实体的Orgid属性
	 */
	public void setOrgid(String orgid)
	{
		this.orgid = orgid;
	}
	
	/**
	 *获取"机构编号"属性
	 */
	public String getOrgid()
	{
		return this.orgid;
	}	   

	/**
	 *设置"人员编号"属性
	 *@param empid 实体的Empid属性
	 */
	public void setEmpid(String empid)
	{
		this.empid = empid;
	}
	
	/**
	 *获取"人员编号"属性
	 */
	public String getEmpid()
	{
		return this.empid;
	}	   

	/**
	 *设置"是否主机构"属性
	 *@param ismain 实体的Ismain属性
	 */
	public void setIsmain(String ismain)
	{
		this.ismain = ismain;
	}
	
	/**
	 *获取"是否主机构"属性
	 */
	public String getIsmain()
	{
		return this.ismain;
	}	   
	public Om_emporg() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Om_emporg(
		String orgempid
	 	,String orgid
	 	,String empid
	 	,String ismain
		 ){
		super();
		this.orgempid = orgempid;
	 	this.orgid = orgid;
	 	this.empid = empid;
	 	this.ismain = ismain;
	}
}

