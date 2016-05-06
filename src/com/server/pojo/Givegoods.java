package com.server.pojo;

import java.sql.Date;
/**
 * 买赠 实体类
 *@author ZhangRuiLong
 */
public class Givegoods
{
   /**
    * 买赠ID,主键
    */
   private String givegoodsid; 
   /**
    * 经销商ID
    */
   private String givegoodscompany;   
   /**
    * 编码
    */
   private String givegoodscode;   
   /**
    * 名称
    */
   private String givegoodsname;   
   /**
    * 描述
    */
   private String givegoodsdetail;   
   /**
    * 规格
    */
   private String givegoodsunits;   
   /**
    * 单位
    */
   private String givegoodsunit;   
   /**
    * 售价
    */
   private String givegoodsprice;   
   /**
    * 现价
    */
   private String givegoodsorgprice;   
   /**
    * 个人限购
    */
   private int givegoodsnum;   
   /**
    * 小类
    */
   private String givegoodsclass;   
   /**
    * 图片
    */
   private String givegoodsimage;   
   /**
    * 状态
    */
   private String givegoodsstatue;   
   /**
    * 创建时间
    */
   private String createtime;   
   /**
    * 创建人
    */
   private String creator;   
   /**
    * 顺序
    */
   private int givegoodsseq;   
    //属性方法	    
     /**
	 *设置主键"买赠ID"属性
	 *@param givegoodsid 实体的Givegoodsid属性
	 */
	public void setGivegoodsid(String givegoodsid)
	{
		this.givegoodsid = givegoodsid;
	}
	
	/**
	 *获取主键"买赠ID"属性
	 */
	public String getGivegoodsid()
	{
		return this.givegoodsid;
	}

	/**
	 *设置"经销商ID"属性
	 *@param givegoodscompany 实体的Givegoodscompany属性
	 */
	public void setGivegoodscompany(String givegoodscompany)
	{
		this.givegoodscompany = givegoodscompany;
	}
	
	/**
	 *获取"经销商ID"属性
	 */
	public String getGivegoodscompany()
	{
		return this.givegoodscompany;
	}	   

	/**
	 *设置"编码"属性
	 *@param givegoodscode 实体的Givegoodscode属性
	 */
	public void setGivegoodscode(String givegoodscode)
	{
		this.givegoodscode = givegoodscode;
	}
	
	/**
	 *获取"编码"属性
	 */
	public String getGivegoodscode()
	{
		return this.givegoodscode;
	}	   

	/**
	 *设置"名称"属性
	 *@param givegoodsname 实体的Givegoodsname属性
	 */
	public void setGivegoodsname(String givegoodsname)
	{
		this.givegoodsname = givegoodsname;
	}
	
	/**
	 *获取"名称"属性
	 */
	public String getGivegoodsname()
	{
		return this.givegoodsname;
	}	   

	/**
	 *设置"描述"属性
	 *@param givegoodsdetail 实体的Givegoodsdetail属性
	 */
	public void setGivegoodsdetail(String givegoodsdetail)
	{
		this.givegoodsdetail = givegoodsdetail;
	}
	
	/**
	 *获取"描述"属性
	 */
	public String getGivegoodsdetail()
	{
		return this.givegoodsdetail;
	}	   

	/**
	 *设置"规格"属性
	 *@param givegoodsunits 实体的Givegoodsunits属性
	 */
	public void setGivegoodsunits(String givegoodsunits)
	{
		this.givegoodsunits = givegoodsunits;
	}
	
	/**
	 *获取"规格"属性
	 */
	public String getGivegoodsunits()
	{
		return this.givegoodsunits;
	}	   

	/**
	 *设置"单位"属性
	 *@param givegoodsunit 实体的Givegoodsunit属性
	 */
	public void setGivegoodsunit(String givegoodsunit)
	{
		this.givegoodsunit = givegoodsunit;
	}
	
	/**
	 *获取"单位"属性
	 */
	public String getGivegoodsunit()
	{
		return this.givegoodsunit;
	}	   

	/**
	 *设置"售价"属性
	 *@param givegoodsprice 实体的Givegoodsprice属性
	 */
	public void setGivegoodsprice(String givegoodsprice)
	{
		this.givegoodsprice = givegoodsprice;
	}
	
	/**
	 *获取"售价"属性
	 */
	public String getGivegoodsprice()
	{
		return this.givegoodsprice;
	}	   

	/**
	 *设置"现价"属性
	 *@param givegoodsorgprice 实体的Givegoodsorgprice属性
	 */
	public void setGivegoodsorgprice(String givegoodsorgprice)
	{
		this.givegoodsorgprice = givegoodsorgprice;
	}
	
	/**
	 *获取"现价"属性
	 */
	public String getGivegoodsorgprice()
	{
		return this.givegoodsorgprice;
	}	   

	/**
	 *设置"个人限购"属性
	 *@param givegoodsnum 实体的Givegoodsnum属性
	 */
	public void setGivegoodsnum(int givegoodsnum)
	{
		this.givegoodsnum = givegoodsnum;
	}
	
	/**
	 *获取"个人限购"属性
	 */
	public int getGivegoodsnum()
	{
		return this.givegoodsnum;
	}	   

	/**
	 *设置"小类"属性
	 *@param givegoodsclass 实体的Givegoodsclass属性
	 */
	public void setGivegoodsclass(String givegoodsclass)
	{
		this.givegoodsclass = givegoodsclass;
	}
	
	/**
	 *获取"小类"属性
	 */
	public String getGivegoodsclass()
	{
		return this.givegoodsclass;
	}	   

	/**
	 *设置"图片"属性
	 *@param givegoodsimage 实体的Givegoodsimage属性
	 */
	public void setGivegoodsimage(String givegoodsimage)
	{
		this.givegoodsimage = givegoodsimage;
	}
	
	/**
	 *获取"图片"属性
	 */
	public String getGivegoodsimage()
	{
		return this.givegoodsimage;
	}	   

	/**
	 *设置"状态"属性
	 *@param givegoodsstatue 实体的Givegoodsstatue属性
	 */
	public void setGivegoodsstatue(String givegoodsstatue)
	{
		this.givegoodsstatue = givegoodsstatue;
	}
	
	/**
	 *获取"状态"属性
	 */
	public String getGivegoodsstatue()
	{
		return this.givegoodsstatue;
	}	   

	/**
	 *设置"创建时间"属性
	 *@param createtime 实体的Createtime属性
	 */
	public void setCreatetime(String createtime)
	{
		this.createtime = createtime;
	}
	
	/**
	 *获取"创建时间"属性
	 */
	public String getCreatetime()
	{
		return this.createtime;
	}	   

	/**
	 *设置"创建人"属性
	 *@param creator 实体的Creator属性
	 */
	public void setCreator(String creator)
	{
		this.creator = creator;
	}
	
	/**
	 *获取"创建人"属性
	 */
	public String getCreator()
	{
		return this.creator;
	}	   

	/**
	 *设置"顺序"属性
	 *@param givegoodsseq 实体的Givegoodsseq属性
	 */
	public void setGivegoodsseq(int givegoodsseq)
	{
		this.givegoodsseq = givegoodsseq;
	}
	
	/**
	 *获取"顺序"属性
	 */
	public int getGivegoodsseq()
	{
		return this.givegoodsseq;
	}	   
	public Givegoods() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Givegoods(
		String givegoodsid
	 	,String givegoodscompany
	 	,String givegoodscode
	 	,String givegoodsname
	 	,String givegoodsdetail
	 	,String givegoodsunits
	 	,String givegoodsunit
	 	,String givegoodsprice
	 	,String givegoodsorgprice
	 	,int givegoodsnum
	 	,String givegoodsclass
	 	,String givegoodsimage
	 	,String givegoodsstatue
	 	,String createtime
	 	,String creator
	 	,int givegoodsseq
		 ){
		super();
		this.givegoodsid = givegoodsid;
	 	this.givegoodscompany = givegoodscompany;
	 	this.givegoodscode = givegoodscode;
	 	this.givegoodsname = givegoodsname;
	 	this.givegoodsdetail = givegoodsdetail;
	 	this.givegoodsunits = givegoodsunits;
	 	this.givegoodsunit = givegoodsunit;
	 	this.givegoodsprice = givegoodsprice;
	 	this.givegoodsorgprice = givegoodsorgprice;
	 	this.givegoodsnum = givegoodsnum;
	 	this.givegoodsclass = givegoodsclass;
	 	this.givegoodsimage = givegoodsimage;
	 	this.givegoodsstatue = givegoodsstatue;
	 	this.createtime = createtime;
	 	this.creator = creator;
	 	this.givegoodsseq = givegoodsseq;
	}
}

