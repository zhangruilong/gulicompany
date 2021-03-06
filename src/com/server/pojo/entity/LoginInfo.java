package com.server.pojo.entity;

public class LoginInfo {

	private String companyid;		//经销商id
	
	private String username;		//用户名称
	
	private String power;			//权限
	
	private String loginname;		//登录账号
	
	private String companycode;     //编码
   
	private String companyshop;  	//店铺
	
	private String comusername;		//经销商的联系人姓名
	
	private String companyphone;	//经销商电话
	
	private String empcode;			//业务员职位和名字
	
	private String companycity;		//经销商城市ID
	
	private String comarea;			//经销商服务区域

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
	
	/**
	 *设置"经销商的联系人姓名"属性
	 *@param username 实体的Username属性
	 */
	public void setComusername(String comusername)
	{
		this.comusername = comusername;
	}
	
	/**
	 *获取"经销商的联系人姓名"属性
	 */
	public String getComusername()
	{
		return this.comusername;
	}	   
	
	/**
	 *设置"经销商电话"属性
	 *@param companyphone 实体的Companyphone属性
	 */
	public void setCompanyphone(String companyphone)
	{
		this.companyphone = companyphone;
	}
	
	/**
	 *获取"经销商电话"属性
	 */
	public String getCompanyphone()
	{
		return this.companyphone;
	}	   
	
	/**
	 *设置"业务员职位和名字"属性
	 *@param empcode 实体的Empcode属性
	 */
	public void setEmpcode(String empcode)
	{
		this.empcode = empcode;
	}
	
	/**
	 *获取"业务员职位和名字"属性
	 */
	public String getEmpcode()
	{
		return this.empcode;
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
	 *设置"经销商服务区域"属性
	 */
	public void setComarea(String comarea)
	{
		this.comarea = comarea;
	}
	
	/**
	 *获取"经销商服务区域"属性
	 */
	public String getComarea()
	{
		return this.comarea;
	}	   

}
