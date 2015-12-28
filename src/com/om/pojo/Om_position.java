package com.om.pojo;

import java.sql.Date;
/**
 * 岗位/职位 实体类
 *@author ZhangRuiLong
 */
public class Om_position
{
   /**
    * 岗位编号,主键
    */
   private String positionid; 
   /**
    * 岗位代码
    */
   private String posicode;   
   /**
    * 岗位名称
    */
   private String posiname;   
   /**
    * 岗位层次
    */
   private int posilevel;   
   /**
    * 上级岗位
    */
   private String manaposi;   
   /**
    * 职务编号
    */
   private String dutyid;   
   /**
    * 机构编号
    */
   private String orgid;   
   /**
    * 岗位序列
    */
   private String positionseq;   
   /**
    * 岗位类别
    */
   private String positype;   
   /**
    * 创建时间
    */
   private String createtime;   
   /**
    * 最近更新时间
    */
   private String lastupdate;   
   /**
    * 最近更新人员
    */
   private String updator;   
   /**
    * 岗位有效开始日期
    */
   private Date startdate;   
   /**
    * 岗位有效截止日期
    */
   private Date enddate;   
   /**
    * 岗位状态
    */
   private String status;   
   /**
    * 是否叶子岗位
    */
   private String isleaf;   
   /**
    * 子节点数
    */
   private int subcount;   
    //属性方法	    
     /**
	 *设置主键"岗位编号"属性
	 *@param positionid 实体的Positionid属性
	 */
	public void setPositionid(String positionid)
	{
		this.positionid = positionid;
	}
	
	/**
	 *获取主键"岗位编号"属性
	 */
	public String getPositionid()
	{
		return this.positionid;
	}

	/**
	 *设置"岗位代码"属性
	 *@param posicode 实体的Posicode属性
	 */
	public void setPosicode(String posicode)
	{
		this.posicode = posicode;
	}
	
	/**
	 *获取"岗位代码"属性
	 */
	public String getPosicode()
	{
		return this.posicode;
	}	   

	/**
	 *设置"岗位名称"属性
	 *@param posiname 实体的Posiname属性
	 */
	public void setPosiname(String posiname)
	{
		this.posiname = posiname;
	}
	
	/**
	 *获取"岗位名称"属性
	 */
	public String getPosiname()
	{
		return this.posiname;
	}	   

	/**
	 *设置"岗位层次"属性
	 *@param posilevel 实体的Posilevel属性
	 */
	public void setPosilevel(int posilevel)
	{
		this.posilevel = posilevel;
	}
	
	/**
	 *获取"岗位层次"属性
	 */
	public int getPosilevel()
	{
		return this.posilevel;
	}	   

	/**
	 *设置"上级岗位"属性
	 *@param manaposi 实体的Manaposi属性
	 */
	public void setManaposi(String manaposi)
	{
		this.manaposi = manaposi;
	}
	
	/**
	 *获取"上级岗位"属性
	 */
	public String getManaposi()
	{
		return this.manaposi;
	}	   

	/**
	 *设置"职务编号"属性
	 *@param dutyid 实体的Dutyid属性
	 */
	public void setDutyid(String dutyid)
	{
		this.dutyid = dutyid;
	}
	
	/**
	 *获取"职务编号"属性
	 */
	public String getDutyid()
	{
		return this.dutyid;
	}	   

	/**
	 *设置"机构编号"属性
	 *@param orgid 实体的Orgid属性
	 */
	public void setOrgid(String orgid)
	{
		this.orgid = orgid;
	}
	
	/**
	 *获取"机构编号"属性
	 */
	public String getOrgid()
	{
		return this.orgid;
	}	   

	/**
	 *设置"岗位序列"属性
	 *@param positionseq 实体的Positionseq属性
	 */
	public void setPositionseq(String positionseq)
	{
		this.positionseq = positionseq;
	}
	
	/**
	 *获取"岗位序列"属性
	 */
	public String getPositionseq()
	{
		return this.positionseq;
	}	   

	/**
	 *设置"岗位类别"属性
	 *@param positype 实体的Positype属性
	 */
	public void setPositype(String positype)
	{
		this.positype = positype;
	}
	
	/**
	 *获取"岗位类别"属性
	 */
	public String getPositype()
	{
		return this.positype;
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
	 *设置"最近更新时间"属性
	 *@param lastupdate 实体的Lastupdate属性
	 */
	public void setLastupdate(String lastupdate)
	{
		this.lastupdate = lastupdate;
	}
	
	/**
	 *获取"最近更新时间"属性
	 */
	public String getLastupdate()
	{
		return this.lastupdate;
	}	   

	/**
	 *设置"最近更新人员"属性
	 *@param updator 实体的Updator属性
	 */
	public void setUpdator(String updator)
	{
		this.updator = updator;
	}
	
	/**
	 *获取"最近更新人员"属性
	 */
	public String getUpdator()
	{
		return this.updator;
	}	   

	/**
	 *设置"岗位有效开始日期"属性
	 *@param startdate 实体的Startdate属性
	 */
	public void setStartdate(Date startdate)
	{
		this.startdate = startdate;
	}
	
	/**
	 *获取"岗位有效开始日期"属性
	 */
	public Date getStartdate()
	{
		return this.startdate;
	}	   

	/**
	 *设置"岗位有效截止日期"属性
	 *@param enddate 实体的Enddate属性
	 */
	public void setEnddate(Date enddate)
	{
		this.enddate = enddate;
	}
	
	/**
	 *获取"岗位有效截止日期"属性
	 */
	public Date getEnddate()
	{
		return this.enddate;
	}	   

	/**
	 *设置"岗位状态"属性
	 *@param status 实体的Status属性
	 */
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	/**
	 *获取"岗位状态"属性
	 */
	public String getStatus()
	{
		return this.status;
	}	   

	/**
	 *设置"是否叶子岗位"属性
	 *@param isleaf 实体的Isleaf属性
	 */
	public void setIsleaf(String isleaf)
	{
		this.isleaf = isleaf;
	}
	
	/**
	 *获取"是否叶子岗位"属性
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
	public Om_position() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Om_position(
		String positionid
	 	,String posicode
	 	,String posiname
	 	,int posilevel
	 	,String manaposi
	 	,String dutyid
	 	,String orgid
	 	,String positionseq
	 	,String positype
	 	,String createtime
	 	,String lastupdate
	 	,String updator
	 	,Date startdate
	 	,Date enddate
	 	,String status
	 	,String isleaf
	 	,int subcount
		 ){
		super();
		this.positionid = positionid;
	 	this.posicode = posicode;
	 	this.posiname = posiname;
	 	this.posilevel = posilevel;
	 	this.manaposi = manaposi;
	 	this.dutyid = dutyid;
	 	this.orgid = orgid;
	 	this.positionseq = positionseq;
	 	this.positype = positype;
	 	this.createtime = createtime;
	 	this.lastupdate = lastupdate;
	 	this.updator = updator;
	 	this.startdate = startdate;
	 	this.enddate = enddate;
	 	this.status = status;
	 	this.isleaf = isleaf;
	 	this.subcount = subcount;
	}
}

