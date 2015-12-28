package com.om.pojo;

import java.sql.Date;
/**
 * om_empposorgview 实体类
 *@author ZhangRuiLong
 */
public class Om_empposorgview
{
   /**
    * posempid,主键
    */
   private String posempid; 
   /**
    * positionid
    */
   private String positionid;   
   /**
    * empid
    */
   private String empid;   
   /**
    * ismain
    */
   private String ismain;   
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
   /**
    * orgname
    */
   private String orgname;   
   /**
    * posiname
    */
   private String posiname;   
   /**
    * loginname
    */
   private String loginname;   
    //属性方法	    
     /**
	 *设置主键"posempid"属性
	 *@param posempid 实体的Posempid属性
	 */
	public void setPosempid(String posempid)
	{
		this.posempid = posempid;
	}
	
	/**
	 *获取主键"posempid"属性
	 */
	public String getPosempid()
	{
		return this.posempid;
	}

	/**
	 *设置"positionid"属性
	 *@param positionid 实体的Positionid属性
	 */
	public void setPositionid(String positionid)
	{
		this.positionid = positionid;
	}
	
	/**
	 *获取"positionid"属性
	 */
	public String getPositionid()
	{
		return this.positionid;
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
	 *设置"ismain"属性
	 *@param ismain 实体的Ismain属性
	 */
	public void setIsmain(String ismain)
	{
		this.ismain = ismain;
	}
	
	/**
	 *获取"ismain"属性
	 */
	public String getIsmain()
	{
		return this.ismain;
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

	/**
	 *设置"orgname"属性
	 *@param orgname 实体的Orgname属性
	 */
	public void setOrgname(String orgname)
	{
		this.orgname = orgname;
	}
	
	/**
	 *获取"orgname"属性
	 */
	public String getOrgname()
	{
		return this.orgname;
	}	   

	/**
	 *设置"posiname"属性
	 *@param posiname 实体的Posiname属性
	 */
	public void setPosiname(String posiname)
	{
		this.posiname = posiname;
	}
	
	/**
	 *获取"posiname"属性
	 */
	public String getPosiname()
	{
		return this.posiname;
	}	   

	/**
	 *设置"loginname"属性
	 *@param loginname 实体的Loginname属性
	 */
	public void setLoginname(String loginname)
	{
		this.loginname = loginname;
	}
	
	/**
	 *获取"loginname"属性
	 */
	public String getLoginname()
	{
		return this.loginname;
	}	   
	public Om_empposorgview() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Om_empposorgview(
		String posempid
	 	,String positionid
	 	,String empid
	 	,String ismain
	 	,String empcode
	 	,String empname
	 	,String gender
	 	,String empstatus
	 	,String orgname
	 	,String posiname
	 	,String loginname
		 ){
		super();
		this.posempid = posempid;
	 	this.positionid = positionid;
	 	this.empid = empid;
	 	this.ismain = ismain;
	 	this.empcode = empcode;
	 	this.empname = empname;
	 	this.gender = gender;
	 	this.empstatus = empstatus;
	 	this.orgname = orgname;
	 	this.posiname = posiname;
	 	this.loginname = loginname;
	}
}

