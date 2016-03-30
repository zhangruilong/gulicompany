package com.server.pojo.entity;

import java.sql.Date;
import java.util.List;
/**
 * 订单 实体类
 *@author ZhangRuiLong
 */
public class Orderm
{
   /**
    * 订单ID,主键
    */
   private String ordermid; 
   /**
    * 客户ID
    */
   private String ordermcustomer;   
   /**
    * 经销商ID
    */
   private String ordermcompany;   
   /**
    * 编码
    */
   private String ordermcode;   
   /**
    * 种类数
    */
   private Integer ordermnum;   
   /**
    * 下单金额
    */
   private String ordermmoney;   
   /**
    * 实际金额
    */
   private String ordermrightmoney;   
   /**
    * 支付方式
    */
   private String ordermway;   
   /**
    * 状态
    */
   private String ordermstatue;   
   /**
    * 备注
    */
   private String ordermdetail;   
   /**
    * 下单时间
    */
   private String ordermtime;   
   /**
    * 联系人
    */
   private String ordermconnect;   
   /**
    * 手机
    */
   private String ordermphone;   
   /**
    * 地址
    */
   private String ordermaddress;   
   /**
    * 修改时间
    */
   private String updtime;   
   /**
    * 修改人
    */
   private String updor;   
   /**
    * 业务员ID
    */
   private String ordermemp;   
   /**
    * 关联orderd
    */
   private List<Orderd> orderdList;
   /**
    * 客户
    */
   private Customer orderdCustomer;
    //属性方法	    
     /**
	 *设置主键"订单ID"属性
	 *@param ordermid 实体的Ordermid属性
	 */
	public void setOrdermid(String ordermid)
	{
		this.ordermid = ordermid;
	}
	
	/**
	 *获取主键"订单ID"属性
	 */
	public String getOrdermid()
	{
		return this.ordermid;
	}

	/**
	 *设置"客户ID"属性
	 *@param ordermcustomer 实体的Ordermcustomer属性
	 */
	public void setOrdermcustomer(String ordermcustomer)
	{
		this.ordermcustomer = ordermcustomer;
	}
	
	/**
	 *获取"客户ID"属性
	 */
	public String getOrdermcustomer()
	{
		return this.ordermcustomer;
	}	   

	/**
	 *设置"经销商ID"属性
	 *@param ordermcompany 实体的Ordermcompany属性
	 */
	public void setOrdermcompany(String ordermcompany)
	{
		this.ordermcompany = ordermcompany;
	}
	
	/**
	 *获取"经销商ID"属性
	 */
	public String getOrdermcompany()
	{
		return this.ordermcompany;
	}	   

	/**
	 *设置"编码"属性
	 *@param ordermcode 实体的Ordermcode属性
	 */
	public void setOrdermcode(String ordermcode)
	{
		this.ordermcode = ordermcode;
	}
	
	/**
	 *获取"编码"属性
	 */
	public String getOrdermcode()
	{
		return this.ordermcode;
	}	   

	/**
	 *设置"种类数"属性
	 *@param ordermnum 实体的Ordermnum属性
	 */
	public void setOrdermnum(Integer ordermnum)
	{
		this.ordermnum = ordermnum;
	}
	
	/**
	 *获取"种类数"属性
	 */
	public Integer getOrdermnum()
	{
		return this.ordermnum;
	}	   

	/**
	 *设置"下单金额"属性
	 *@param ordermmoney 实体的Ordermmoney属性
	 */
	public void setOrdermmoney(String ordermmoney)
	{
		this.ordermmoney = ordermmoney;
	}
	
	/**
	 *获取"下单金额"属性
	 */
	public String getOrdermmoney()
	{
		return this.ordermmoney;
	}	   

	/**
	 *设置"实际金额"属性
	 *@param ordermrightmoney 实体的Ordermrightmoney属性
	 */
	public void setOrdermrightmoney(String ordermrightmoney)
	{
		this.ordermrightmoney = ordermrightmoney;
	}
	
	/**
	 *获取"实际金额"属性
	 */
	public String getOrdermrightmoney()
	{
		return this.ordermrightmoney;
	}	   

	/**
	 *设置"支付方式"属性
	 *@param ordermway 实体的Ordermway属性
	 */
	public void setOrdermway(String ordermway)
	{
		this.ordermway = ordermway;
	}
	
	/**
	 *获取"支付方式"属性
	 */
	public String getOrdermway()
	{
		return this.ordermway;
	}	   

	/**
	 *设置"状态"属性
	 *@param ordermstatue 实体的Ordermstatue属性
	 */
	public void setOrdermstatue(String ordermstatue)
	{
		this.ordermstatue = ordermstatue;
	}
	
	/**
	 *获取"状态"属性
	 */
	public String getOrdermstatue()
	{
		return this.ordermstatue;
	}	   

	/**
	 *设置"备注"属性
	 *@param ordermdetail 实体的Ordermdetail属性
	 */
	public void setOrdermdetail(String ordermdetail)
	{
		this.ordermdetail = ordermdetail;
	}
	
	/**
	 *获取"备注"属性
	 */
	public String getOrdermdetail()
	{
		return this.ordermdetail;
	}	   

	/**
	 *设置"下单时间"属性
	 *@param ordermtime 实体的Ordermtime属性
	 */
	public void setOrdermtime(String ordermtime)
	{
		this.ordermtime = ordermtime;
	}
	
	/**
	 *获取"下单时间"属性
	 */
	public String getOrdermtime()
	{
		return this.ordermtime;
	}	   

	/**
	 *设置"联系人"属性
	 *@param ordermconnect 实体的Ordermconnect属性
	 */
	public void setOrdermconnect(String ordermconnect)
	{
		this.ordermconnect = ordermconnect;
	}
	
	/**
	 *获取"联系人"属性
	 */
	public String getOrdermconnect()
	{
		return this.ordermconnect;
	}	   

	/**
	 *设置"手机"属性
	 *@param ordermphone 实体的Ordermphone属性
	 */
	public void setOrdermphone(String ordermphone)
	{
		this.ordermphone = ordermphone;
	}
	
	/**
	 *获取"手机"属性
	 */
	public String getOrdermphone()
	{
		return this.ordermphone;
	}	   

	/**
	 *设置"地址"属性
	 *@param ordermaddress 实体的Ordermaddress属性
	 */
	public void setOrdermaddress(String ordermaddress)
	{
		this.ordermaddress = ordermaddress;
	}
	
	/**
	 *获取"地址"属性
	 */
	public String getOrdermaddress()
	{
		return this.ordermaddress;
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

	/**
	 *设置"业务员ID"属性
	 *@param ordermemp 实体的Ordermemp属性
	 */
	public void setOrdermemp(String ordermemp)
	{
		this.ordermemp = ordermemp;
	}
	
	/**
	 *获取"业务员ID"属性
	 */
	public String getOrdermemp()
	{
		return this.ordermemp;
	}	   
	
	public List<Orderd> getOrderdList() {
		return orderdList;
	}

	public void setOrderdList(List<Orderd> orderdList) {
		this.orderdList = orderdList;
	}

	public Customer getOrderdCustomer() {
		return orderdCustomer;
	}

	public void setOrderdCustomer(Customer orderdCustomer) {
		this.orderdCustomer = orderdCustomer;
	}

	public Orderm() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Orderm(
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

