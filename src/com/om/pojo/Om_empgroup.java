package com.om.pojo;

import java.sql.Date;
/**
 * 人员工作组对应关系 实体类
 *@author ZhangRuiLong
 */
public class Om_empgroup
{
   /**
    * 工作组人员ID,主键
    */
   private String groempid; 
   /**
    * 工作组编号
    */
   private String groupid;   
   /**
    * 人员编号
    */
   private String empid;   
   /**
    * 工作组职务
    */
   private String groupduty;   
   /**
    * 备注
    */
   private String remark;   
    //属性方法	    
     /**
	 *设置主键"工作组人员ID"属性
	 *@param groempid 实体的Groempid属性
	 */
	public void setGroempid(String groempid)
	{
		this.groempid = groempid;
	}
	
	/**
	 *获取主键"工作组人员ID"属性
	 */
	public String getGroempid()
	{
		return this.groempid;
	}

	/**
	 *设置"工作组编号"属性
	 *@param groupid 实体的Groupid属性
	 */
	public void setGroupid(String groupid)
	{
		this.groupid = groupid;
	}
	
	/**
	 *获取"工作组编号"属性
	 */
	public String getGroupid()
	{
		return this.groupid;
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
	 *设置"工作组职务"属性
	 *@param groupduty 实体的Groupduty属性
	 */
	public void setGroupduty(String groupduty)
	{
		this.groupduty = groupduty;
	}
	
	/**
	 *获取"工作组职务"属性
	 */
	public String getGroupduty()
	{
		return this.groupduty;
	}	   

	/**
	 *设置"备注"属性
	 *@param remark 实体的Remark属性
	 */
	public void setRemark(String remark)
	{
		this.remark = remark;
	}
	
	/**
	 *获取"备注"属性
	 */
	public String getRemark()
	{
		return this.remark;
	}	   
	public Om_empgroup() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Om_empgroup(
		String groempid
	 	,String groupid
	 	,String empid
	 	,String groupduty
	 	,String remark
		 ){
		super();
		this.groempid = groempid;
	 	this.groupid = groupid;
	 	this.empid = empid;
	 	this.groupduty = groupduty;
	 	this.remark = remark;
	}
}

