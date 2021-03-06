package com.server.pojo.entity;

import java.sql.Date;
/**
 * 年货和组合商品 实体类
 *@author ZhangRuiLong
 */
public class Bkgoods
{
   /**
    * 促销品ID,主键
    */
   private String bkgoodsid; 
   /**
    * 经销商ID
    */
   private String bkgoodscompany;   
   /**
    * 编码
    */
   private String bkgoodscode;   
   /**
    * 名称
    */
   private String bkgoodsname;   
   /**
    * 描述
    */
   private String bkgoodsdetail;   
   /**
    * 规格
    */
   private String bkgoodsunits;   
   /**
    * 单位
    */
   private String bkgoodsunit;   
   /**
    * 原价
    */
   private Float bkgoodsprice;   
   /**
    * 现价
    */
   private Float bkgoodsorgprice;   
   /**
    * 限量
    */
   private Integer bkgoodsnum;   
   /**
    * 小类名称
    */
   private String bkgoodsclass;   
   /**
    * 图片
    */
   private String bkgoodsimage;   
   /**
    * 状态
    */
   private String bkgoodsstatue;   
   /**
    * 创建时间
    */
   private String bkcreatetime;   
   /**
    * 创建人
    */
   private String bkcreator;   
   /**
    * 顺序
    */
   private Integer bkgoodsseq;   
   /**
    * 客户范围
    */
   private String bkgoodsscope;   
   /**
    * 品牌
    */
   private String bkgoodsbrand;   
   /**
    * 总限购
    */
   private Integer bkgoodsallnum;   
   /**
    * 剩余数量
    */
   private Integer bkgoodssurplus;   
   /**
    * 重量
    */
   private String bkgoodsweight;   
   /**
    * 分类
    */
   private String bkgoodstype;   
   /**
    * 修改时间
    */
   private String bkgoodsupdtime;   
   /**
    * 修改人
    */
   private String bkgoodsupdor;   
    //属性方法	    
     /**
	 *设置主键"促销品ID"属性
	 *@param bkgoodsid 实体的Bkgoodsid属性
	 */
	public void setBkgoodsid(String bkgoodsid)
	{
		this.bkgoodsid = bkgoodsid;
	}
	
	/**
	 *获取主键"促销品ID"属性
	 */
	public String getBkgoodsid()
	{
		return this.bkgoodsid;
	}

	/**
	 *设置"经销商ID"属性
	 *@param bkgoodscompany 实体的Bkgoodscompany属性
	 */
	public void setBkgoodscompany(String bkgoodscompany)
	{
		this.bkgoodscompany = bkgoodscompany;
	}
	
	/**
	 *获取"经销商ID"属性
	 */
	public String getBkgoodscompany()
	{
		return this.bkgoodscompany;
	}	   

	/**
	 *设置"编码"属性
	 *@param bkgoodscode 实体的Bkgoodscode属性
	 */
	public void setBkgoodscode(String bkgoodscode)
	{
		this.bkgoodscode = bkgoodscode;
	}
	
	/**
	 *获取"编码"属性
	 */
	public String getBkgoodscode()
	{
		return this.bkgoodscode;
	}	   

	/**
	 *设置"名称"属性
	 *@param bkgoodsname 实体的Bkgoodsname属性
	 */
	public void setBkgoodsname(String bkgoodsname)
	{
		this.bkgoodsname = bkgoodsname;
	}
	
	/**
	 *获取"名称"属性
	 */
	public String getBkgoodsname()
	{
		return this.bkgoodsname;
	}	   

	/**
	 *设置"描述"属性
	 *@param bkgoodsdetail 实体的Bkgoodsdetail属性
	 */
	public void setBkgoodsdetail(String bkgoodsdetail)
	{
		this.bkgoodsdetail = bkgoodsdetail;
	}
	
	/**
	 *获取"描述"属性
	 */
	public String getBkgoodsdetail()
	{
		return this.bkgoodsdetail;
	}	   

	/**
	 *设置"规格"属性
	 *@param bkgoodsunits 实体的Bkgoodsunits属性
	 */
	public void setBkgoodsunits(String bkgoodsunits)
	{
		this.bkgoodsunits = bkgoodsunits;
	}
	
	/**
	 *获取"规格"属性
	 */
	public String getBkgoodsunits()
	{
		return this.bkgoodsunits;
	}	   

	/**
	 *设置"单位"属性
	 *@param bkgoodsunit 实体的Bkgoodsunit属性
	 */
	public void setBkgoodsunit(String bkgoodsunit)
	{
		this.bkgoodsunit = bkgoodsunit;
	}
	
	/**
	 *获取"单位"属性
	 */
	public String getBkgoodsunit()
	{
		return this.bkgoodsunit;
	}	   

	/**
	 *设置"原价"属性
	 *@param bkgoodsprice 实体的Bkgoodsprice属性
	 */
	public void setBkgoodsprice(Float bkgoodsprice)
	{
		this.bkgoodsprice = bkgoodsprice;
	}
	
	/**
	 *获取"原价"属性
	 */
	public Float getBkgoodsprice()
	{
		return this.bkgoodsprice;
	}	   

	/**
	 *设置"现价"属性
	 *@param bkgoodsorgprice 实体的Bkgoodsorgprice属性
	 */
	public void setBkgoodsorgprice(Float bkgoodsorgprice)
	{
		this.bkgoodsorgprice = bkgoodsorgprice;
	}
	
	/**
	 *获取"现价"属性
	 */
	public Float getBkgoodsorgprice()
	{
		return this.bkgoodsorgprice;
	}	   

	/**
	 *设置"限量"属性
	 *@param bkgoodsnum 实体的Bkgoodsnum属性
	 */
	public void setBkgoodsnum(Integer bkgoodsnum)
	{
		this.bkgoodsnum = bkgoodsnum;
	}
	
	/**
	 *获取"限量"属性
	 */
	public Integer getBkgoodsnum()
	{
		return this.bkgoodsnum;
	}	   

	/**
	 *设置"小类名称"属性
	 *@param bkgoodsclass 实体的Bkgoodsclass属性
	 */
	public void setBkgoodsclass(String bkgoodsclass)
	{
		this.bkgoodsclass = bkgoodsclass;
	}
	
	/**
	 *获取"小类名称"属性
	 */
	public String getBkgoodsclass()
	{
		return this.bkgoodsclass;
	}	   

	/**
	 *设置"图片"属性
	 *@param bkgoodsimage 实体的Bkgoodsimage属性
	 */
	public void setBkgoodsimage(String bkgoodsimage)
	{
		this.bkgoodsimage = bkgoodsimage;
	}
	
	/**
	 *获取"图片"属性
	 */
	public String getBkgoodsimage()
	{
		return this.bkgoodsimage;
	}	   

	/**
	 *设置"状态"属性
	 *@param bkgoodsstatue 实体的Bkgoodsstatue属性
	 */
	public void setBkgoodsstatue(String bkgoodsstatue)
	{
		this.bkgoodsstatue = bkgoodsstatue;
	}
	
	/**
	 *获取"状态"属性
	 */
	public String getBkgoodsstatue()
	{
		return this.bkgoodsstatue;
	}	   

	/**
	 *设置"创建时间"属性
	 *@param bkcreatetime 实体的Bkcreatetime属性
	 */
	public void setBkcreatetime(String bkcreatetime)
	{
		this.bkcreatetime = bkcreatetime;
	}
	
	/**
	 *获取"创建时间"属性
	 */
	public String getBkcreatetime()
	{
		return this.bkcreatetime;
	}	   

	/**
	 *设置"创建人"属性
	 *@param bkcreator 实体的Bkcreator属性
	 */
	public void setBkcreator(String bkcreator)
	{
		this.bkcreator = bkcreator;
	}
	
	/**
	 *获取"创建人"属性
	 */
	public String getBkcreator()
	{
		return this.bkcreator;
	}	   

	/**
	 *设置"顺序"属性
	 *@param bkgoodsseq 实体的Bkgoodsseq属性
	 */
	public void setBkgoodsseq(Integer bkgoodsseq)
	{
		this.bkgoodsseq = bkgoodsseq;
	}
	
	/**
	 *获取"顺序"属性
	 */
	public Integer getBkgoodsseq()
	{
		return this.bkgoodsseq;
	}	   

	/**
	 *设置"客户范围"属性
	 *@param bkgoodsscope 实体的Bkgoodsscope属性
	 */
	public void setBkgoodsscope(String bkgoodsscope)
	{
		this.bkgoodsscope = bkgoodsscope;
	}
	
	/**
	 *获取"客户范围"属性
	 */
	public String getBkgoodsscope()
	{
		return this.bkgoodsscope;
	}	   

	/**
	 *设置"品牌"属性
	 *@param bkgoodsbrand 实体的Bkgoodsbrand属性
	 */
	public void setBkgoodsbrand(String bkgoodsbrand)
	{
		this.bkgoodsbrand = bkgoodsbrand;
	}
	
	/**
	 *获取"品牌"属性
	 */
	public String getBkgoodsbrand()
	{
		return this.bkgoodsbrand;
	}	   

	/**
	 *设置"总限购"属性
	 *@param bkgoodsallnum 实体的Bkgoodsallnum属性
	 */
	public void setBkgoodsallnum(Integer bkgoodsallnum)
	{
		this.bkgoodsallnum = bkgoodsallnum;
	}
	
	/**
	 *获取"总限购"属性
	 */
	public Integer getBkgoodsallnum()
	{
		return this.bkgoodsallnum;
	}	   

	/**
	 *设置"剩余数量"属性
	 *@param bkgoodssurplus 实体的Bkgoodssurplus属性
	 */
	public void setBkgoodssurplus(Integer bkgoodssurplus)
	{
		this.bkgoodssurplus = bkgoodssurplus;
	}
	
	/**
	 *获取"剩余数量"属性
	 */
	public Integer getBkgoodssurplus()
	{
		return this.bkgoodssurplus;
	}	   

	/**
	 *设置"重量"属性
	 *@param bkgoodsweight 实体的Bkgoodsweight属性
	 */
	public void setBkgoodsweight(String bkgoodsweight)
	{
		this.bkgoodsweight = bkgoodsweight;
	}
	
	/**
	 *获取"重量"属性
	 */
	public String getBkgoodsweight()
	{
		return this.bkgoodsweight;
	}	   

	/**
	 *设置"分类"属性
	 *@param bkgoodstype 实体的Bkgoodstype属性
	 */
	public void setBkgoodstype(String bkgoodstype)
	{
		this.bkgoodstype = bkgoodstype;
	}
	
	/**
	 *获取"分类"属性
	 */
	public String getBkgoodstype()
	{
		return this.bkgoodstype;
	}	   

	/**
	 *设置"修改时间"属性
	 *@param bkgoodsupdtime 实体的Bkgoodsupdtime属性
	 */
	public void setBkgoodsupdtime(String bkgoodsupdtime)
	{
		this.bkgoodsupdtime = bkgoodsupdtime;
	}
	
	/**
	 *获取"修改时间"属性
	 */
	public String getBkgoodsupdtime()
	{
		return this.bkgoodsupdtime;
	}	   

	/**
	 *设置"修改人"属性
	 *@param bkgoodsupdor 实体的Bkgoodsupdor属性
	 */
	public void setBkgoodsupdor(String bkgoodsupdor)
	{
		this.bkgoodsupdor = bkgoodsupdor;
	}
	
	/**
	 *获取"修改人"属性
	 */
	public String getBkgoodsupdor()
	{
		return this.bkgoodsupdor;
	}	   
	public Bkgoods() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Bkgoods(
		String bkgoodsid
	 	,String bkgoodscompany
	 	,String bkgoodscode
	 	,String bkgoodsname
	 	,String bkgoodsdetail
	 	,String bkgoodsunits
	 	,String bkgoodsunit
	 	,Float bkgoodsprice
	 	,Float bkgoodsorgprice
	 	,Integer bkgoodsnum
	 	,String bkgoodsclass
	 	,String bkgoodsimage
	 	,String bkgoodsstatue
	 	,String bkcreatetime
	 	,String bkcreator
	 	,Integer bkgoodsseq
	 	,String bkgoodsscope
	 	,String bkgoodsbrand
	 	,Integer bkgoodsallnum
	 	,Integer bkgoodssurplus
	 	,String bkgoodsweight
	 	,String bkgoodstype
	 	,String bkgoodsupdtime
	 	,String bkgoodsupdor
		 ){
		super();
		this.bkgoodsid = bkgoodsid;
	 	this.bkgoodscompany = bkgoodscompany;
	 	this.bkgoodscode = bkgoodscode;
	 	this.bkgoodsname = bkgoodsname;
	 	this.bkgoodsdetail = bkgoodsdetail;
	 	this.bkgoodsunits = bkgoodsunits;
	 	this.bkgoodsunit = bkgoodsunit;
	 	this.bkgoodsprice = bkgoodsprice;
	 	this.bkgoodsorgprice = bkgoodsorgprice;
	 	this.bkgoodsnum = bkgoodsnum;
	 	this.bkgoodsclass = bkgoodsclass;
	 	this.bkgoodsimage = bkgoodsimage;
	 	this.bkgoodsstatue = bkgoodsstatue;
	 	this.bkcreatetime = bkcreatetime;
	 	this.bkcreator = bkcreator;
	 	this.bkgoodsseq = bkgoodsseq;
	 	this.bkgoodsscope = bkgoodsscope;
	 	this.bkgoodsbrand = bkgoodsbrand;
	 	this.bkgoodsallnum = bkgoodsallnum;
	 	this.bkgoodssurplus = bkgoodssurplus;
	 	this.bkgoodsweight = bkgoodsweight;
	 	this.bkgoodstype = bkgoodstype;
	 	this.bkgoodsupdtime = bkgoodsupdtime;
	 	this.bkgoodsupdor = bkgoodsupdor;
	}
}

