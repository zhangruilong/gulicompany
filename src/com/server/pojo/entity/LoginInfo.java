package com.server.pojo.entity;

public class LoginInfo {

	private String companyid;		//经销商id
	
	private String username;		//用户名称
	
	private String power;			//权限
	
	private String loginname;		//登录账号
	/**
     * 编码
     */
   private String companycode;   
   /**
    * 店铺
    */
   private String companyshop;  

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
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
}
