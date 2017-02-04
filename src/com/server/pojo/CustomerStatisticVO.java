package com.server.pojo;
/**
 * 客户统计类
 */
public class CustomerStatisticVO {
	/**
	    * 客户ID,主键
	    */
	   private String customerid; 
	   /**
	    * 编码
	    */
	   private String customercode;   
	   /**
	    * 姓名
	    */
	   private String customername;   
	   /**
	    * 手机
	    */
	   private String customerphone;   
	   /**
	    * 密码
	    */
	   private String customerpsw;   
	   /**
	    * 店铺
	    */
	   private String customershop;   
	   /**
	    * 城市
	    */
	   private String customercity;   
	   /**
	    * 县
	    */
	   private String customerxian;   
	   /**
	    * 街道地址
	    */
	   private String customeraddress;   
	   /**
	    * 类型
	    */
	   private String customertype;   
	   /**
	    * 等级
	    */
	   private Integer customerlevel;   
	   /**
	    * openid
	    */
	   private String openid;   
	   /**
	    * 描述
	    */
	   private String customerdetail;   
	   /**
	    * 状态
	    */
	   private String customerstatue;   
	   /**
	    * 创建时间
	    */
	   private String createtime;   
	   /**
	    * 修改时间
	    */
	   private String updtime;   
	   /**
	    * 订单总数
	    */
	   private Integer odm_num;	
	   /**
	    * 订单总金额
	    */
	   private Float odm_money;	
	   /**
	    * 业务员名称
	    */
	   private String cccreatetime;	
	
	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getCustomercode() {
		return customercode;
	}

	public void setCustomercode(String customercode) {
		this.customercode = customercode;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getCustomerphone() {
		return customerphone;
	}

	public void setCustomerphone(String customerphone) {
		this.customerphone = customerphone;
	}

	public String getCustomerpsw() {
		return customerpsw;
	}

	public void setCustomerpsw(String customerpsw) {
		this.customerpsw = customerpsw;
	}

	public String getCustomershop() {
		return customershop;
	}

	public void setCustomershop(String customershop) {
		this.customershop = customershop;
	}

	public String getCustomercity() {
		return customercity;
	}

	public void setCustomercity(String customercity) {
		this.customercity = customercity;
	}

	public String getCustomerxian() {
		return customerxian;
	}

	public void setCustomerxian(String customerxian) {
		this.customerxian = customerxian;
	}

	public String getCustomeraddress() {
		return customeraddress;
	}

	public void setCustomeraddress(String customeraddress) {
		this.customeraddress = customeraddress;
	}

	public String getCustomertype() {
		return customertype;
	}

	public void setCustomertype(String customertype) {
		this.customertype = customertype;
	}

	public Integer getCustomerlevel() {
		return customerlevel;
	}

	public void setCustomerlevel(Integer customerlevel) {
		this.customerlevel = customerlevel;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getCustomerdetail() {
		return customerdetail;
	}

	public void setCustomerdetail(String customerdetail) {
		this.customerdetail = customerdetail;
	}

	public String getCustomerstatue() {
		return customerstatue;
	}

	public void setCustomerstatue(String customerstatue) {
		this.customerstatue = customerstatue;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getUpdtime() {
		return updtime;
	}

	public void setUpdtime(String updtime) {
		this.updtime = updtime;
	}

	public String getCccreatetime() {
		return cccreatetime;
	}

	public void setCccreatetime(String cccreatetime) {
		this.cccreatetime = cccreatetime;
	}

	public Integer getOdm_num() {
		return odm_num;
	}
	
	public void setOdm_num(Integer odm_num) {
		this.odm_num = odm_num;
	}
	
	public Float getOdm_money() {
		return odm_money;
	}
	
	public void setOdm_money(Float odm_money) {
		this.odm_money = odm_money;
	}
	
	
}
