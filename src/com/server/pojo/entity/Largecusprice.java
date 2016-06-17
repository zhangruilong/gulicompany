package com.server.pojo.entity;

public class Largecusprice {
    private String largecuspriceid;

    private String largecuspricecompany;

    private String largecuspricecustomer;

    private String largecuspricegoods;

    private String largecuspriceprice;

    private String largecuspricedetail;

    private String largecuspricecreatetime;
	/**
	 * 修改时间
	 */
    private String largecuspricecreator;

    private String largecuspriceprice2;

    private String largecuspriceunit;

    private String largecuspriceunit2;

    private Goods lcpGoods;
    
    public String getLargecuspriceid() {
        return largecuspriceid;
    }

    public void setLargecuspriceid(String largecuspriceid) {
        this.largecuspriceid = largecuspriceid == null ? null : largecuspriceid.trim();
    }

    public String getLargecuspricecompany() {
        return largecuspricecompany;
    }

    public void setLargecuspricecompany(String largecuspricecompany) {
        this.largecuspricecompany = largecuspricecompany == null ? null : largecuspricecompany.trim();
    }

    public String getLargecuspricecustomer() {
        return largecuspricecustomer;
    }

    public void setLargecuspricecustomer(String largecuspricecustomer) {
        this.largecuspricecustomer = largecuspricecustomer == null ? null : largecuspricecustomer.trim();
    }

    public String getLargecuspricegoods() {
        return largecuspricegoods;
    }

    public void setLargecuspricegoods(String largecuspricegoods) {
        this.largecuspricegoods = largecuspricegoods == null ? null : largecuspricegoods.trim();
    }

    public String getLargecuspriceprice() {
        return largecuspriceprice;
    }

    public void setLargecuspriceprice(String largecuspriceprice) {
        this.largecuspriceprice = largecuspriceprice == null ? null : largecuspriceprice.trim();
    }

    public String getLargecuspricedetail() {
        return largecuspricedetail;
    }

    public void setLargecuspricedetail(String largecuspricedetail) {
        this.largecuspricedetail = largecuspricedetail == null ? null : largecuspricedetail.trim();
    }

    public String getLargecuspricecreatetime() {
        return largecuspricecreatetime;
    }

    public void setLargecuspricecreatetime(String largecuspricecreatetime) {
        this.largecuspricecreatetime = largecuspricecreatetime == null ? null : largecuspricecreatetime.trim();
    }
    /**
	 * 修改时间
	 */
    public String getLargecuspricecreator() {
        return largecuspricecreator;
    }
    /**
	 * 修改时间
	 */
    public void setLargecuspricecreator(String largecuspricecreator) {
        this.largecuspricecreator = largecuspricecreator == null ? null : largecuspricecreator.trim();
    }

    public String getLargecuspriceprice2() {
        return largecuspriceprice2;
    }

    public void setLargecuspriceprice2(String largecuspriceprice2) {
        this.largecuspriceprice2 = largecuspriceprice2 == null ? null : largecuspriceprice2.trim();
    }

    public String getLargecuspriceunit() {
        return largecuspriceunit;
    }

    public void setLargecuspriceunit(String largecuspriceunit) {
        this.largecuspriceunit = largecuspriceunit == null ? null : largecuspriceunit.trim();
    }

    public String getLargecuspriceunit2() {
        return largecuspriceunit2;
    }

    public void setLargecuspriceunit2(String largecuspriceunit2) {
        this.largecuspriceunit2 = largecuspriceunit2 == null ? null : largecuspriceunit2.trim();
    }

	public Goods getLcpGoods() {
		return lcpGoods;
	}

	public void setLcpGoods(Goods lcpGoods) {
		this.lcpGoods = lcpGoods;
	}

    
}