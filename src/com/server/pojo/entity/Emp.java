package com.server.pojo.entity;

import java.sql.Date;
/**
 * 业务员 实体类
 *@author ZhangRuiLong
 */
public class Emp
{
   /**
    * 业务员ID,主键
    */
   private String empid; 
   /**
    * 经销商ID
    */
   private String empcompany;   
   /**
    * 编码
    */
   private String empcode;   
   /**
    * 账号
    */
   private String loginname;   
   /**
    * 密码
    */
   private String password;   
   /**
    * 描述(姓名)
    */
   private String empdetail;   
   /**
    * 状态
    */
   private String empstatue;   
   /**
    * 创建时间
    */
   private String createtime;   
   /**
    * 修改时间
    */
   private String updtime;   
    //属性方法	    
     /**
	 *设置主键"业务员ID"属性
	 *@param empid 实体的Empid属性
	 */
	public void setEmpid(String empid)
	{
		this.empid = empid;
	}
	
	/**
	 *获取主键"业务员ID"属性
	 */
	public String getEmpid()
	{
		return this.empid;
	}

	/**
	 *设置"经销商ID"属性
	 *@param empcompany 实体的Empcompany属性
	 */
	public void setEmpcompany(String empcompany)
	{
		this.empcompany = empcompany;
	}
	
	/**
	 *获取"经销商ID"属性
	 */
	public String getEmpcompany()
	{
		return this.empcompany;
	}	   

	/**
	 *设置"编码"属性
	 *@param empcode 实体的Empcode属性
	 */
	public void setEmpcode(String empcode)
	{
		this.empcode = empcode;
	}
	
	/**
	 *获取"编码"属性
	 */
	public String getEmpcode()
	{
		return this.empcode;
	}	   

	/**
	 *设置"账号"属性
	 *@param loginname 实体的Loginname属性
	 */
	public void setLoginname(String loginname)
	{
		this.loginname = loginname;
	}
	
	/**
	 *获取"账号"属性
	 */
	public String getLoginname()
	{
		return this.loginname;
	}	   

	/**
	 *设置"密码"属性
	 *@param password 实体的Password属性
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	/**
	 *获取"密码"属性
	 */
	public String getPassword()
	{
		return this.password;
	}	   

	/**
	 *设置"描述(姓名)"属性
	 *@param empdetail 实体的Empdetail属性
	 */
	public void setEmpdetail(String empdetail)
	{
		this.empdetail = empdetail;
	}
	
	/**
	 *获取"描述(姓名)"属性
	 */
	public String getEmpdetail()
	{
		return this.empdetail;
	}	   

	/**
	 *设置"状态"属性
	 *@param empstatue 实体的Empstatue属性
	 */
	public void setEmpstatue(String empstatue)
	{
		this.empstatue = empstatue;
	}
	
	/**
	 *获取"状态"属性
	 */
	public String getEmpstatue()
	{
		return this.empstatue;
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

	/**
	 *设置"修改时间"属性
	 *@param updtime 实体的Updtime属性
	 */
	public void setUpdtime(String updtime)
	{
		this.updtime = updtime;
	}
	
	/**
	 *获取"修改时间"属性
	 */
	public String getUpdtime()
	{
		return this.updtime;
	}	   
	public Emp() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Emp(
		String empid
	 	,String empcompany
	 	,String empcode
	 	,String loginname
	 	,String password
	 	,String empdetail
	 	,String empstatue
	 	,String createtime
	 	,String updtime
		 ){
		super();
		this.empid = empid;
	 	this.empcompany = empcompany;
	 	this.empcode = empcode;
	 	this.loginname = loginname;
	 	this.password = password;
	 	this.empdetail = empdetail;
	 	this.empstatue = empstatue;
	 	this.createtime = createtime;
	 	this.updtime = updtime;
	}
}

