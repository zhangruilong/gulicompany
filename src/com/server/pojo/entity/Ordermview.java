package com.server.pojo.entity;

import java.sql.Date;
/**
 * ordermview 实体类
 *@author ZhangRuiLong
 */
public class Ordermview
{
   /**
    * ordermid,主键
    */
   private String ordermid; 
   /**
    * ordermcustomer
    */
   private String ordermcustomer;   
   /**
    * ordermcompany
    */
   private String ordermcompany;   
   /**
    * ordermcode
    */
   private String ordermcode;   
   /**
    * ordermnum
    */
   private int ordermnum;   
   /**
    * ordermmoney
    */
   private String ordermmoney;   
   /**
    * ordermrightmoney
    */
   private String ordermrightmoney;   
   /**
    * ordermway
    */
   private String ordermway;   
   /**
    * ordermstatue
    */
   private String ordermstatue;   
   /**
    * ordermdetail
    */
   private String ordermdetail;   
   /**
    * ordermtime
    */
   private String ordermtime;   
   /**
    * ordermconnect
    */
   private String ordermconnect;   
   /**
    * ordermphone
    */
   private String ordermphone;   
   /**
    * ordermaddress
    */
   private String ordermaddress;   
   /**
    * customershop
    */
   private String customershop;   
   /**
    * 客户类型
    */
   private String ordermcustype;
   /**
    * 客户等级
    */
   private String ordermcuslevel;
   /**
    * updtime
    */
   private String updtime;   
   /**
    * updor
    */
   private String updor;   
   /**
    * ordermemp
    */
   private String ordermemp;   
    //属性方法	    
     /**
	 *设置主键"ordermid"属性
	 *@param ordermid 实体的Ordermid属性
	 */
	public void setOrdermid(String ordermid)
	{
		this.ordermid = ordermid;
	}
	
	/**
	 *获取主键"ordermid"属性
	 */
	public String getOrdermid()
	{
		return this.ordermid;
	}

	public String getOrdermcuslevel() {
		return ordermcuslevel;
	}

	public void setOrdermcuslevel(String ordermcuslevel) {
		this.ordermcuslevel = ordermcuslevel;
	}

	public String getOrdermcustype() {
		return ordermcustype;
	}

	public void setOrdermcustype(String ordermcustype) {
		this.ordermcustype = ordermcustype;
	}

	/**
	 *设置"ordermcustomer"属性
	 *@param ordermcustomer 实体的Ordermcustomer属性
	 */
	public void setOrdermcustomer(String ordermcustomer)
	{
		this.ordermcustomer = ordermcustomer;
	}
	
	/**
	 *获取"ordermcustomer"属性
	 */
	public String getOrdermcustomer()
	{
		return this.ordermcustomer;
	}	   

	/**
	 *设置"ordermcompany"属性
	 *@param ordermcompany 实体的Ordermcompany属性
	 */
	public void setOrdermcompany(String ordermcompany)
	{
		this.ordermcompany = ordermcompany;
	}
	
	/**
	 *获取"ordermcompany"属性
	 */
	public String getOrdermcompany()
	{
		return this.ordermcompany;
	}	   

	/**
	 *设置"ordermcode"属性
	 *@param ordermcode 实体的Ordermcode属性
	 */
	public void setOrdermcode(String ordermcode)
	{
		this.ordermcode = ordermcode;
	}
	
	/**
	 *获取"ordermcode"属性
	 */
	public String getOrdermcode()
	{
		return this.ordermcode;
	}	   

	/**
	 *设置"ordermnum"属性
	 *@param ordermnum 实体的Ordermnum属性
	 */
	public void setOrdermnum(int ordermnum)
	{
		this.ordermnum = ordermnum;
	}
	
	/**
	 *获取"ordermnum"属性
	 */
	public int getOrdermnum()
	{
		return this.ordermnum;
	}	   

	/**
	 *设置"ordermmoney"属性
	 *@param ordermmoney 实体的Ordermmoney属性
	 */
	public void setOrdermmoney(String ordermmoney)
	{
		this.ordermmoney = ordermmoney;
	}
	
	/**
	 *获取"ordermmoney"属性
	 */
	public String getOrdermmoney()
	{
		return this.ordermmoney;
	}	   

	/**
	 *设置"ordermrightmoney"属性
	 *@param ordermrightmoney 实体的Ordermrightmoney属性
	 */
	public void setOrdermrightmoney(String ordermrightmoney)
	{
		this.ordermrightmoney = ordermrightmoney;
	}
	
	/**
	 *获取"ordermrightmoney"属性
	 */
	public String getOrdermrightmoney()
	{
		return this.ordermrightmoney;
	}	   

	/**
	 *设置"ordermway"属性
	 *@param ordermway 实体的Ordermway属性
	 */
	public void setOrdermway(String ordermway)
	{
		this.ordermway = ordermway;
	}
	
	/**
	 *获取"ordermway"属性
	 */
	public String getOrdermway()
	{
		return this.ordermway;
	}	   

	/**
	 *设置"ordermstatue"属性
	 *@param ordermstatue 实体的Ordermstatue属性
	 */
	public void setOrdermstatue(String ordermstatue)
	{
		this.ordermstatue = ordermstatue;
	}
	
	/**
	 *获取"ordermstatue"属性
	 */
	public String getOrdermstatue()
	{
		return this.ordermstatue;
	}	   

	/**
	 *设置"ordermdetail"属性
	 *@param ordermdetail 实体的Ordermdetail属性
	 */
	public void setOrdermdetail(String ordermdetail)
	{
		this.ordermdetail = ordermdetail;
	}
	
	/**
	 *获取"ordermdetail"属性
	 */
	public String getOrdermdetail()
	{
		return this.ordermdetail;
	}	   

	/**
	 *设置"ordermtime"属性
	 *@param ordermtime 实体的Ordermtime属性
	 */
	public void setOrdermtime(String ordermtime)
	{
		this.ordermtime = ordermtime;
	}
	
	/**
	 *获取"ordermtime"属性
	 */
	public String getOrdermtime()
	{
		return this.ordermtime;
	}	   

	/**
	 *设置"ordermconnect"属性
	 *@param ordermconnect 实体的Ordermconnect属性
	 */
	public void setOrdermconnect(String ordermconnect)
	{
		this.ordermconnect = ordermconnect;
	}
	
	/**
	 *获取"ordermconnect"属性
	 */
	public String getOrdermconnect()
	{
		return this.ordermconnect;
	}	   

	/**
	 *设置"ordermphone"属性
	 *@param ordermphone 实体的Ordermphone属性
	 */
	public void setOrdermphone(String ordermphone)
	{
		this.ordermphone = ordermphone;
	}
	
	/**
	 *获取"ordermphone"属性
	 */
	public String getOrdermphone()
	{
		return this.ordermphone;
	}	   

	/**
	 *设置"ordermaddress"属性
	 *@param ordermaddress 实体的Ordermaddress属性
	 */
	public void setOrdermaddress(String ordermaddress)
	{
		this.ordermaddress = ordermaddress;
	}
	
	/**
	 *获取"ordermaddress"属性
	 */
	public String getOrdermaddress()
	{
		return this.ordermaddress;
	}	   

	/**
	 *设置"updtime"属性
	 *@param updtime 实体的Updtime属性
	 */
	public void setUpdtime(String updtime)
	{
		this.updtime = updtime;
	}
	
	/**
	 *获取"updtime"属性
	 */
	public String getUpdtime()
	{
		return this.updtime;
	}	   

	/**
	 *设置"updor"属性
	 *@param updor 实体的Updor属性
	 */
	public void setUpdor(String updor)
	{
		this.updor = updor;
	}
	
	/**
	 *获取"updor"属性
	 */
	public String getUpdor()
	{
		return this.updor;
	}	   

	/**
	 *设置"ordermemp"属性
	 *@param ordermemp 实体的Ordermemp属性
	 */
	public void setOrdermemp(String ordermemp)
	{
		this.ordermemp = ordermemp;
	}
	
	/**
	 *获取"ordermemp"属性
	 */
	public String getOrdermemp()
	{
		return this.ordermemp;
	}	   

	public String getCustomershop() {
		return customershop;
	}

	public void setCustomershop(String customershop) {
		this.customershop = customershop;
	}

	public Ordermview() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Ordermview(
		String ordermid
	 	,String ordermcustomer
	 	,String ordermcompany
	 	,String ordermcode
	 	,int ordermnum
	 	,String ordermmoney
	 	,String ordermrightmoney
	 	,String ordermway
	 	,String ordermstatue
	 	,String ordermdetail
	 	,String ordermtime
	 	,String ordermconnect
	 	,String ordermphone
	 	,String ordermaddress
	 	,String updtime
	 	,String updor
	 	,String ordermemp
		 ){
		super();
		this.ordermid = ordermid;
	 	this.ordermcustomer = ordermcustomer;
	 	this.ordermcompany = ordermcompany;
	 	this.ordermcode = ordermcode;
	 	this.ordermnum = ordermnum;
	 	this.ordermmoney = ordermmoney;
	 	this.ordermrightmoney = ordermrightmoney;
	 	this.ordermway = ordermway;
	 	this.ordermstatue = ordermstatue;
	 	this.ordermdetail = ordermdetail;
	 	this.ordermtime = ordermtime;
	 	this.ordermconnect = ordermconnect;
	 	this.ordermphone = ordermphone;
	 	this.ordermaddress = ordermaddress;
	 	this.updtime = updtime;
	 	this.updor = updor;
	 	this.ordermemp = ordermemp;
	}
}

