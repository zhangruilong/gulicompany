package com.server.pojo;
/**
 * 进销存变动表
 * @author tao
 *
 */
public class InventoryVO {

   /**
    * 商品名称
    */
   private String goodsname;   
   /**
    * 规格
    */
   private String goodsunits;   
   /**
    * 仓库名称
    */
   private String storehousename;   
   /**
    * 上期存数量
    */
   private Integer quondamnum;   
   /**
    * 进货数量
    */
   private Integer innum;   
   /**
    * 销售数量
    */
   private Integer outnum;   
   /**
    * 完好退货数量
    */
   private Integer outback;   
   /**
    * 采购退货数量
    */
   private Integer inback;   
   /**
    * 本期存数量
    */
   private Integer goodsnumnum;
   /**
    * 商品名称
    */
	public String getGoodsname() {
		return goodsname;
	}
	/**
	    * 商品名称
	    */
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	/**
	    * 规格
	    */
	public String getGoodsunits() {
		return goodsunits;
	}
	/**
	    * 规格
	    */
	public void setGoodsunits(String goodsunits) {
		this.goodsunits = goodsunits;
	}
	/**
	    * 仓库名称
	    */
	public String getStorehousename() {
		return storehousename;
	}
	/**
	    * 仓库名称
	    */
	public void setStorehousename(String storehousename) {
		this.storehousename = storehousename;
	}
	/**
	    * 上期存数量
	    */
	public Integer getQuondamnum() {
		return quondamnum;
	}
	/**
	    * 上期存数量
	    */
	public void setQuondamnum(Integer quondamnum) {
		this.quondamnum = quondamnum;
	}
	/**
	    * 进货数量
	    */
	public Integer getInnum() {
		return innum;
	}
	/**
	    * 进货数量
	    */
	public void setInnum(Integer innum) {
		this.innum = innum;
	}
	/**
	    * 销售数量
	    */
	public Integer getOutnum() {
		return outnum;
	}
	/**
	    * 销售数量
	    */
	public void setOutnum(Integer outnum) {
		this.outnum = outnum;
	}
	/**
	    * 完好退货数量
	    */
	public Integer getOutback() {
		return outback;
	}
	/**
	    * 完好退货数量
	    */
	public void setOutback(Integer outback) {
		this.outback = outback;
	}
	/**
	    * 采购退货数量
	    */
	public Integer getInback() {
		return inback;
	}
	/**
	    * 采购退货数量
	    */
	public void setInback(Integer inback) {
		this.inback = inback;
	}
	/**
	    * 本期存数量
	    */
	public Integer getGoodsnumnum() {
		return goodsnumnum;
	}
	/**
	    * 本期存数量
	    */
	public void setGoodsnumnum(Integer goodsnumnum) {
		this.goodsnumnum = goodsnumnum;
	}   
}
