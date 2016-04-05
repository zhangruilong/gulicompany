package com.server.pojo.entity;

public class OrderdStatistics {
	/**
	 * 总数量
	 */
	private String numtotal;
	/**
	 * 单价总和
	 */
	private String pricetotal;
	/**
	 * 总下单金额
	 */
	private String moneytotal;
	/**
	 * 总实际金额
	 */
	private String rightmoneytotal;
	
	public String getNumtotal() {
		return numtotal;
	}
	
	public void setNumtotal(String numtotal) {
		this.numtotal = numtotal;
	}
	
	public String getPricetotal() {
		return pricetotal;
	}
	
	public void setPricetotal(String pricetotal) {
		this.pricetotal = pricetotal;
	}
	
	public String getMoneytotal() {
		return moneytotal;
	}
	
	public void setMoneytotal(String moneytotal) {
		this.moneytotal = moneytotal;
	}
	
	public String getRightmoneytotal() {
		return rightmoneytotal;
	}
	
	public void setRightmoneytotal(String rightmoneytotal) {
		this.rightmoneytotal = rightmoneytotal;
	}
	
}
