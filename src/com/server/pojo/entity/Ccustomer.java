package com.server.pojo.entity;

import java.sql.Date;
/**
 * 经销商和客户 实体类
 *@author ZhangRuiLong
 */
public class Ccustomer
{
   /**
    * 经销商和客户ID,主键
    */
   private String ccustomerid; 
   /**
    * 经销商ID
    */
   private String ccustomercompany;   
   /**
    * 客户ID
    */
   private String ccustomercustomer;   
   /**
    * 描述(客户和供应商的关系等级)
    */
   private String ccustomerdetail;   
   /**
    * 创建时间(客户经理)
    */
   private String createtime;   
   /**
    * 创建人
    */
   private String creator;
   /**
    * 关联客户
    */
   private Customer customer;
    //属性方法	    
     /**
	 *设置主键"经销商和客户ID"属性
	 *@param ccustomerid 实体的Ccustomerid属性
	 */
	public void setCcustomerid(String ccustomerid)
	{
		this.ccustomerid = ccustomerid;
	}
	
	/**
	 *获取主键"经销商和客户ID"属性
	 */
	public String getCcustomerid()
	{
		return this.ccustomerid;
	}

	/**
	 *设置"经销商ID"属性
	 *@param ccustomercompany 实体的Ccustomercompany属性
	 */
	public void setCcustomercompany(String ccustomercompany)
	{
		this.ccustomercompany = ccustomercompany;
	}
	
	/**
	 *获取"经销商ID"属性
	 */
	public String getCcustomercompany()
	{
		return this.ccustomercompany;
	}	   

	/**
	 *设置"客户ID"属性
	 *@param ccustomercustomer 实体的Ccustomercustomer属性
	 */
	public void setCcustomercustomer(String ccustomercustomer)
	{
		this.ccustomercustomer = ccustomercustomer;
	}
	
	/**
	 *获取"客户ID"属性
	 */
	public String getCcustomercustomer()
	{
		return this.ccustomercustomer;
	}	   

	/**
	 *设置"描述"属性
	 *@param ccustomerdetail 实体的Ccustomerdetail属性
	 */
	public void setCcustomerdetail(String ccustomerdetail)
	{
		this.ccustomerdetail = ccustomerdetail;
	}
	
	/**
	 *获取"描述"属性
	 */
	public String getCcustomerdetail()
	{
		return this.ccustomerdetail;
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
	 *设置"创建人"属性
	 *@param creator 实体的Creator属性
	 */
	public void setCreator(String creator)
	{
		this.creator = creator;
	}
	
	/**
	 *获取"创建人"属性
	 */
	public String getCreator()
	{
		return this.creator;
	}	   
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Ccustomer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Ccustomer(
		String ccustomerid
	 	,String ccustomercompany
	 	,String ccustomercustomer
	 	,String ccustomerdetail
	 	,String createtime
	 	,String creator
		 ){
		super();
		this.ccustomerid = ccustomerid;
	 	this.ccustomercompany = ccustomercompany;
	 	this.ccustomercustomer = ccustomercustomer;
	 	this.ccustomerdetail = ccustomerdetail;
	 	this.createtime = createtime;
	 	this.creator = creator;
	}
}

