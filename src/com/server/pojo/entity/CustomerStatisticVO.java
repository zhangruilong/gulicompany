package com.server.pojo.entity;
/**
 * 客户统计类
 */
public class CustomerStatisticVO extends Customer {
	
	private Integer odm_num;				//订单总数
	private Float odm_money;				//订单总金额
	private String cccreatetime;			//业务员名称
	
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
