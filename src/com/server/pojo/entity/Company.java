package com.server.pojo.entity;

import java.util.List;
/**
 * 经销商 实体类
 *@author ZhangRuiLong
 */
public class Company
{
   /**
    * 经销商ID,主键
    */
   private String companyid; 
   /**
    * 编码
    */
   private String companycode;   
   /**
    * 姓名
    */
   private String username;   
   /**
    * 手机
    */
   private String companyphone;   
   /**
    * 店铺
    */
   private String companyshop;   
   /**
    * 城市和县ID
    */
   private String companycity;   
   /**
    * 街道地址
    */
   private String companyaddress;   
   /**
    * 描述
    */
   private String companydetail;   
   /**
    * 状态
    */
   private String companystatue;   
   /**
    * 账号
    */
   private String loginname;   
   /**
    * 密码
    */
   private String password;   
   /**
    * 创建时间
    */
   private String createtime;   
   /**
    * 修改时间
    */
   private String updtime;   
   /**
    * 关联城市
    */
   private City city;
   /**
    * 关联促销品
    */
   private List<Timegoods> timegoodsList;
   /**
    * 关联商品
    */
   private List<Goods> goodsList;
    //属性方法	    
     /**
	 *设置主键"经销商ID"属性
	 *@param companyid 实体的Companyid属性
	 */
	public void setCompanyid(String companyid)
	{
		this.companyid = companyid;
	}
	
	/**
	 *获取主键"经销商ID"属性
	 */
	public String getCompanyid()
	{
		return this.companyid;
	}

	/**
	 *设置"编码"属性
	 *@param companycode 实体的Companycode属性
	 */
	public void setCompanycode(String companycode)
	{
		this.companycode = companycode;
	}
	
	/**
	 *获取"编码"属性
	 */
	public String getCompanycode()
	{
		return this.companycode;
	}	   

	/**
	 *设置"姓名"属性
	 *@param username 实体的Username属性
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	/**
	 *获取"姓名"属性
	 */
	public String getUsername()
	{
		return this.username;
	}	   

	/**
	 *设置"手机"属性
	 *@param companyphone 实体的Companyphone属性
	 */
	public void setCompanyphone(String companyphone)
	{
		this.companyphone = companyphone;
	}
	
	/**
	 *获取"手机"属性
	 */
	public String getCompanyphone()
	{
		return this.companyphone;
	}	   

	/**
	 *设置"店铺"属性
	 *@param companyshop 实体的Companyshop属性
	 */
	public void setCompanyshop(String companyshop)
	{
		this.companyshop = companyshop;
	}
	
	/**
	 *获取"店铺"属性
	 */
	public String getCompanyshop()
	{
		return this.companyshop;
	}	   

	/**
	 *设置"城市和县ID"属性
	 *@param companycity 实体的Companycity属性
	 */
	public void setCompanycity(String companycity)
	{
		this.companycity = companycity;
	}
	
	/**
	 *获取"城市和县ID"属性
	 */
	public String getCompanycity()
	{
		return this.companycity;
	}	   

	/**
	 *设置"街道地址"属性
	 *@param companyaddress 实体的Companyaddress属性
	 */
	public void setCompanyaddress(String companyaddress)
	{
		this.companyaddress = companyaddress;
	}
	
	/**
	 *获取"街道地址"属性
	 */
	public String getCompanyaddress()
	{
		return this.companyaddress;
	}	   

	/**
	 *设置"描述"属性
	 *@param companydetail 实体的Companydetail属性
	 */
	public void setCompanydetail(String companydetail)
	{
		this.companydetail = companydetail;
	}
	
	/**
	 *获取"描述"属性
	 */
	public String getCompanydetail()
	{
		return this.companydetail;
	}	   

	/**
	 *设置"状态"属性
	 *@param companystatue 实体的Companystatue属性
	 */
	public void setCompanystatue(String companystatue)
	{
		this.companystatue = companystatue;
	}
	
	/**
	 *获取"状态"属性
	 */
	public String getCompanystatue()
	{
		return this.companystatue;
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
	
	
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public List<Timegoods> getTimegoodsList() {
		return timegoodsList;
	}

	public void setTimegoodsList(List<Timegoods> timegoodsList) {
		this.timegoodsList = timegoodsList;
	}

	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}

	public Company() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Company(
		String companyid
	 	,String companycode
	 	,String username
	 	,String companyphone
	 	,String companyshop
	 	,String companycity
	 	,String companyaddress
	 	,String companydetail
	 	,String companystatue
	 	,String loginname
	 	,String password
	 	,String createtime
	 	,String updtime
		 ){
		super();
		this.companyid = companyid;
	 	this.companycode = companycode;
	 	this.username = username;
	 	this.companyphone = companyphone;
	 	this.companyshop = companyshop;
	 	this.companycity = companycity;
	 	this.companyaddress = companyaddress;
	 	this.companydetail = companydetail;
	 	this.companystatue = companystatue;
	 	this.loginname = loginname;
	 	this.password = password;
	 	this.createtime = createtime;
	 	this.updtime = updtime;
	}
}

