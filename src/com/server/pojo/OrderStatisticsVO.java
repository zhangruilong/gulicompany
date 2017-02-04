package com.server.pojo;

public class OrderStatisticsVO {

	private String orderdcode;
	
	private String orderdname;
	
	private String orderdunits;
	
	private String orderdunit;
	
	private String orderdprice;
	
	private Integer sumorderdnum;
	
	private Integer sumorderdmoney;
	
	private Integer sumorderdrightmoney;
	
	private Integer orderdweight;
	
	public Integer getSumorderdnum() {
		return sumorderdnum;
	}

	public void setSumorderdnum(Integer sumorderdnum) {
		this.sumorderdnum = sumorderdnum;
	}

	public Integer getOrderdweight() {
		return orderdweight;
	}

	public void setOrderdweight(Integer orderdweight) {
		this.orderdweight = orderdweight;
	}

	public String getOrderdcode() {
		return orderdcode;
	}

	public void setOrderdcode(String orderdcode) {
		this.orderdcode = orderdcode;
	}

	public String getOrderdname() {
		return orderdname;
	}

	public void setOrderdname(String orderdname) {
		this.orderdname = orderdname;
	}

	public String getOrderdunits() {
		return orderdunits;
	}

	public void setOrderdunits(String orderdunits) {
		this.orderdunits = orderdunits;
	}

	public String getOrderdunit() {
		return orderdunit;
	}

	public void setOrderdunit(String orderdunit) {
		this.orderdunit = orderdunit;
	}

	public String getOrderdprice() {
		return orderdprice;
	}

	public void setOrderdprice(String orderdprice) {
		this.orderdprice = orderdprice;
	}

	public Integer getSumorderdmoney() {
		return sumorderdmoney;
	}

	public void setSumorderdmoney(Integer sumorderdmoney) {
		this.sumorderdmoney = sumorderdmoney;
	}

	public Integer getSumorderdrightmoney() {
		return sumorderdrightmoney;
	}

	public void setSumorderdrightmoney(Integer sumorderdrightmoney) {
		this.sumorderdrightmoney = sumorderdrightmoney;
	}
	
	
}
