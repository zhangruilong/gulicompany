package com.om.pojo;

import java.sql.Date;
/**
 * om_groupempview 实体类
 *@author ZhangRuiLong
 */
public class Om_groupempview
{
   /**
    * groempid,主键
    */
   private String groempid; 
   /**
    * groupid
    */
   private String groupid;   
   /**
    * empid
    */
   private String empid;   
   /**
    * groupduty
    */
   private String groupduty;   
   /**
    * remark
    */
   private String remark;   
   /**
    * empcode
    */
   private String empcode;   
   /**
    * empname
    */
   private String empname;   
   /**
    * gender
    */
   private String gender;   
   /**
    * empstatus
    */
   private String empstatus;   
    //属性方法	    
     /**
	 *设置主键"groempid"属性
	 *@param groempid 实体的Groempid属性
	 */
	public void setGroempid(String groempid)
	{
		this.groempid = groempid;
	}
	
	/**
	 *获取主键"groempid"属性
	 */
	public String getGroempid()
	{
		return this.groempid;
	}

	/**
	 *设置"groupid"属性
	 *@param groupid 实体的Groupid属性
	 */
	public void setGroupid(String groupid)
	{
		this.groupid = groupid;
	}
	
	/**
	 *获取"groupid"属性
	 */
	public String getGroupid()
	{
		return this.groupid;
	}	   

	/**
	 *设置"empid"属性
	 *@param empid 实体的Empid属性
	 */
	public void setEmpid(String empid)
	{
		this.empid = empid;
	}
	
	/**
	 *获取"empid"属性
	 */
	public String getEmpid()
	{
		return this.empid;
	}	   

	/**
	 *设置"groupduty"属性
	 *@param groupduty 实体的Groupduty属性
	 */
	public void setGroupduty(String groupduty)
	{
		this.groupduty = groupduty;
	}
	
	/**
	 *获取"groupduty"属性
	 */
	public String getGroupduty()
	{
		return this.groupduty;
	}	   

	/**
	 *设置"remark"属性
	 *@param remark 实体的Remark属性
	 */
	public void setRemark(String remark)
	{
		this.remark = remark;
	}
	
	/**
	 *获取"remark"属性
	 */
	public String getRemark()
	{
		return this.remark;
	}	   

	/**
	 *设置"empcode"属性
	 *@param empcode 实体的Empcode属性
	 */
	public void setEmpcode(String empcode)
	{
		this.empcode = empcode;
	}
	
	/**
	 *获取"empcode"属性
	 */
	public String getEmpcode()
	{
		return this.empcode;
	}	   

	/**
	 *设置"empname"属性
	 *@param empname 实体的Empname属性
	 */
	public void setEmpname(String empname)
	{
		this.empname = empname;
	}
	
	/**
	 *获取"empname"属性
	 */
	public String getEmpname()
	{
		return this.empname;
	}	   

	/**
	 *设置"gender"属性
	 *@param gender 实体的Gender属性
	 */
	public void setGender(String gender)
	{
		this.gender = gender;
	}
	
	/**
	 *获取"gender"属性
	 */
	public String getGender()
	{
		return this.gender;
	}	   

	/**
	 *设置"empstatus"属性
	 *@param empstatus 实体的Empstatus属性
	 */
	public void setEmpstatus(String empstatus)
	{
		this.empstatus = empstatus;
	}
	
	/**
	 *获取"empstatus"属性
	 */
	public String getEmpstatus()
	{
		return this.empstatus;
	}	   
	public Om_groupempview() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Om_groupempview(
		String groempid
	 	,String groupid
	 	,String empid
	 	,String groupduty
	 	,String remark
	 	,String empcode
	 	,String empname
	 	,String gender
	 	,String empstatus
		 ){
		super();
		this.groempid = groempid;
	 	this.groupid = groupid;
	 	this.empid = empid;
	 	this.groupduty = groupduty;
	 	this.remark = remark;
	 	this.empcode = empcode;
	 	this.empname = empname;
	 	this.gender = gender;
	 	this.empstatus = empstatus;
	}
}

