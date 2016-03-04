package com.server.pojo.entity;

import java.sql.Date;
/**
 * 在线支付记录 实体类
 *@author ZhangRuiLong
 */
public class Pay
{
   /**
    * 在线支付ID,主键
    */
   private String payid; 
   /**
    * 订单ID
    */
   private String payorderm;   
   /**
    * 备注
    */
   private String paydetail;   
   /**
    * 状态
    */
   private String paystatue;   
   /**
    * 修改时间
    */
   private String updtime;   
   /**
    * 修改人
    */
   private String updor;   
    //属性方法	    
     /**
	 *设置主键"在线支付ID"属性
	 *@param payid 实体的Payid属性
	 */
	public void setPayid(String payid)
	{
		this.payid = payid;
	}
	
	/**
	 *获取主键"在线支付ID"属性
	 */
	public String getPayid()
	{
		return this.payid;
	}

	/**
	 *设置"订单ID"属性
	 *@param payorderm 实体的Payorderm属性
	 */
	public void setPayorderm(String payorderm)
	{
		this.payorderm = payorderm;
	}
	
	/**
	 *获取"订单ID"属性
	 */
	public String getPayorderm()
	{
		return this.payorderm;
	}	   

	/**
	 *设置"备注"属性
	 *@param paydetail 实体的Paydetail属性
	 */
	public void setPaydetail(String paydetail)
	{
		this.paydetail = paydetail;
	}
	
	/**
	 *获取"备注"属性
	 */
	public String getPaydetail()
	{
		return this.paydetail;
	}	   

	/**
	 *设置"状态"属性
	 *@param paystatue 实体的Paystatue属性
	 */
	public void setPaystatue(String paystatue)
	{
		this.paystatue = paystatue;
	}
	
	/**
	 *获取"状态"属性
	 */
	public String getPaystatue()
	{
		return this.paystatue;
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

	/**
	 *设置"修改人"属性
	 *@param updor 实体的Updor属性
	 */
	public void setUpdor(String updor)
	{
		this.updor = updor;
	}
	
	/**
	 *获取"修改人"属性
	 */
	public String getUpdor()
	{
		return this.updor;
	}	   
	public Pay() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Pay(
		String payid
	 	,String payorderm
	 	,String paydetail
	 	,String paystatue
	 	,String updtime
	 	,String updor
		 ){
		super();
		this.payid = payid;
	 	this.payorderm = payorderm;
	 	this.paydetail = paydetail;
	 	this.paystatue = paystatue;
	 	this.updtime = updtime;
	 	this.updor = updor;
	}
}

