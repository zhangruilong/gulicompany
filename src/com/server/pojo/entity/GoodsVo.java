package com.server.pojo.entity;

public class GoodsVo {
	/**
	 * 商品类型
	 */
	private String type;
	/**
	 * 商品
	 */
	private Goods goods;
	/**
	 * 秒杀商品
	 */
	private Timegoods timegoods;
	/**
	 * 买赠商品
	 */
	private Givegoods givegoods;
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public Goods getGoods() {
		return goods;
	}
	
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	
	public Timegoods getTimegoods() {
		return timegoods;
	}
	
	public void setTimegoods(Timegoods timegoods) {
		this.timegoods = timegoods;
	}
	
	public Givegoods getGivegoods() {
		return givegoods;
	}
	
	public void setGivegoods(Givegoods givegoods) {
		this.givegoods = givegoods;
	}
	
}
