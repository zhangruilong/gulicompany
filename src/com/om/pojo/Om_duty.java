package com.om.pojo;

import java.sql.Date;
/**
 * 职务定义表 实体类
 *@author ZhangRuiLong
 */
public class Om_duty
{
   /**
    * 职务编号,主键
    */
   private String dutyid; 
   /**
    * 职务代码
    */
   private String dutycode;   
   /**
    * 职务名称
    */
   private String dutyname;   
   /**
    * 上级职务编号
    */
   private String parentduty;   
   /**
    * 职务层次
    */
   private int dutylevel;   
   /**
    * 职务序列号
    */
   private String dutyseq;   
   /**
    * 职务套别
    */
   private String dutytype;   
   /**
    * 是否叶子节点
    */
   private String isleaf;   
   /**
    * 子节点数
    */
   private int subcount;   
   /**
    * 备注
    */
   private String remark;   
    //属性方法	    
     /**
	 *设置主键"职务编号"属性
	 *@param dutyid 实体的Dutyid属性
	 */
	public void setDutyid(String dutyid)
	{
		this.dutyid = dutyid;
	}
	
	/**
	 *获取主键"职务编号"属性
	 */
	public String getDutyid()
	{
		return this.dutyid;
	}

	/**
	 *设置"职务代码"属性
	 *@param dutycode 实体的Dutycode属性
	 */
	public void setDutycode(String dutycode)
	{
		this.dutycode = dutycode;
	}
	
	/**
	 *获取"职务代码"属性
	 */
	public String getDutycode()
	{
		return this.dutycode;
	}	   

	/**
	 *设置"职务名称"属性
	 *@param dutyname 实体的Dutyname属性
	 */
	public void setDutyname(String dutyname)
	{
		this.dutyname = dutyname;
	}
	
	/**
	 *获取"职务名称"属性
	 */
	public String getDutyname()
	{
		return this.dutyname;
	}	   

	/**
	 *设置"上级职务编号"属性
	 *@param parentduty 实体的Parentduty属性
	 */
	public void setParentduty(String parentduty)
	{
		this.parentduty = parentduty;
	}
	
	/**
	 *获取"上级职务编号"属性
	 */
	public String getParentduty()
	{
		return this.parentduty;
	}	   

	/**
	 *设置"职务层次"属性
	 *@param dutylevel 实体的Dutylevel属性
	 */
	public void setDutylevel(int dutylevel)
	{
		this.dutylevel = dutylevel;
	}
	
	/**
	 *获取"职务层次"属性
	 */
	public int getDutylevel()
	{
		return this.dutylevel;
	}	   

	/**
	 *设置"职务序列号"属性
	 *@param dutyseq 实体的Dutyseq属性
	 */
	public void setDutyseq(String dutyseq)
	{
		this.dutyseq = dutyseq;
	}
	
	/**
	 *获取"职务序列号"属性
	 */
	public String getDutyseq()
	{
		return this.dutyseq;
	}	   

	/**
	 *设置"职务套别"属性
	 *@param dutytype 实体的Dutytype属性
	 */
	public void setDutytype(String dutytype)
	{
		this.dutytype = dutytype;
	}
	
	/**
	 *获取"职务套别"属性
	 */
	public String getDutytype()
	{
		return this.dutytype;
	}	   

	/**
	 *设置"是否叶子节点"属性
	 *@param isleaf 实体的Isleaf属性
	 */
	public void setIsleaf(String isleaf)
	{
		this.isleaf = isleaf;
	}
	
	/**
	 *获取"是否叶子节点"属性
	 */
	public String getIsleaf()
	{
		return this.isleaf;
	}	   

	/**
	 *设置"子节点数"属性
	 *@param subcount 实体的Subcount属性
	 */
	public void setSubcount(int subcount)
	{
		this.subcount = subcount;
	}
	
	/**
	 *获取"子节点数"属性
	 */
	public int getSubcount()
	{
		return this.subcount;
	}	   

	/**
	 *设置"备注"属性
	 *@param remark 实体的Remark属性
	 */
	public void setRemark(String remark)
	{
		this.remark = remark;
	}
	
	/**
	 *获取"备注"属性
	 */
	public String getRemark()
	{
		return this.remark;
	}	   
	public Om_duty() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Om_duty(
		String dutyid
	 	,String dutycode
	 	,String dutyname
	 	,String parentduty
	 	,int dutylevel
	 	,String dutyseq
	 	,String dutytype
	 	,String isleaf
	 	,int subcount
	 	,String remark
		 ){
		super();
		this.dutyid = dutyid;
	 	this.dutycode = dutycode;
	 	this.dutyname = dutyname;
	 	this.parentduty = parentduty;
	 	this.dutylevel = dutylevel;
	 	this.dutyseq = dutyseq;
	 	this.dutytype = dutytype;
	 	this.isleaf = isleaf;
	 	this.subcount = subcount;
	 	this.remark = remark;
	}
}

