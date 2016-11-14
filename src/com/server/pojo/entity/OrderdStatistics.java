package com.server.pojo.entity;

public class OrderdStatistics {
	/**
	 * 总数量
	 */
	private String numtotal;
	/**
	 * 总重量
	 */
	private String weighttotal;
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
	
	public String getWeighttotal() {
		return weighttotal;
	}

	public void setWeighttotal(String weighttotal) {
		this.weighttotal = weighttotal;
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
