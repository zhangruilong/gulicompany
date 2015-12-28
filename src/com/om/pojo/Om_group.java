package com.om.pojo;

import java.sql.Date;
/**
 * 工作组 实体类
 *@author ZhangRuiLong
 */
public class Om_group
{
   /**
    * 工作组编号,主键
    */
   private String groupid; 
   /**
    * 工作组代码
    */
   private String groupcode;   
   /**
    * 工作组名称
    */
   private String groupname;   
   /**
    * 工作组层次
    */
   private int grouplevel;   
   /**
    * 工作组描述
    */
   private String groupdesc;   
   /**
    * 工作组类型
    */
   private String grouptype;   
   /**
    * 工作组路径序列
    */
   private String groupseq;   
   /**
    * 工作组有效开始日期
    */
   private Date startdate;   
   /**
    * 工作组有效截止日期
    */
   private Date enddate;   
   /**
    * 工作组状态
    */
   private String groupstatus;   
   /**
    * 负责人
    */
   private String manager;   
   /**
    * 隶属机构
    */
   private String orgid;   
   /**
    * 父工作组编号
    */
   private String parentgroupid;   
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
    * 是否叶子节点
    */
   private String isleaf;   
   /**
    * 子节点数
    */
   private String subcount;   
    //属性方法	    
     /**
	 *设置主键"工作组编号"属性
	 *@param groupid 实体的Groupid属性
	 */
	public void setGroupid(String groupid)
	{
		this.groupid = groupid;
	}
	
	/**
	 *获取主键"工作组编号"属性
	 */
	public String getGroupid()
	{
		return this.groupid;
	}

	/**
	 *设置"工作组代码"属性
	 *@param groupcode 实体的Groupcode属性
	 */
	public void setGroupcode(String groupcode)
	{
		this.groupcode = groupcode;
	}
	
	/**
	 *获取"工作组代码"属性
	 */
	public String getGroupcode()
	{
		return this.groupcode;
	}	   

	/**
	 *设置"工作组名称"属性
	 *@param groupname 实体的Groupname属性
	 */
	public void setGroupname(String groupname)
	{
		this.groupname = groupname;
	}
	
	/**
	 *获取"工作组名称"属性
	 */
	public String getGroupname()
	{
		return this.groupname;
	}	   

	/**
	 *设置"工作组层次"属性
	 *@param grouplevel 实体的Grouplevel属性
	 */
	public void setGrouplevel(int grouplevel)
	{
		this.grouplevel = grouplevel;
	}
	
	/**
	 *获取"工作组层次"属性
	 */
	public int getGrouplevel()
	{
		return this.grouplevel;
	}	   

	/**
	 *设置"工作组描述"属性
	 *@param groupdesc 实体的Groupdesc属性
	 */
	public void setGroupdesc(String groupdesc)
	{
		this.groupdesc = groupdesc;
	}
	
	/**
	 *获取"工作组描述"属性
	 */
	public String getGroupdesc()
	{
		return this.groupdesc;
	}	   

	/**
	 *设置"工作组类型"属性
	 *@param grouptype 实体的Grouptype属性
	 */
	public void setGrouptype(String grouptype)
	{
		this.grouptype = grouptype;
	}
	
	/**
	 *获取"工作组类型"属性
	 */
	public String getGrouptype()
	{
		return this.grouptype;
	}	   

	/**
	 *设置"工作组路径序列"属性
	 *@param groupseq 实体的Groupseq属性
	 */
	public void setGroupseq(String groupseq)
	{
		this.groupseq = groupseq;
	}
	
	/**
	 *获取"工作组路径序列"属性
	 */
	public String getGroupseq()
	{
		return this.groupseq;
	}	   

	/**
	 *设置"工作组有效开始日期"属性
	 *@param startdate 实体的Startdate属性
	 */
	public void setStartdate(Date startdate)
	{
		this.startdate = startdate;
	}
	
	/**
	 *获取"工作组有效开始日期"属性
	 */
	public Date getStartdate()
	{
		return this.startdate;
	}	   

	/**
	 *设置"工作组有效截止日期"属性
	 *@param enddate 实体的Enddate属性
	 */
	public void setEnddate(Date enddate)
	{
		this.enddate = enddate;
	}
	
	/**
	 *获取"工作组有效截止日期"属性
	 */
	public Date getEnddate()
	{
		return this.enddate;
	}	   

	/**
	 *设置"工作组状态"属性
	 *@param groupstatus 实体的Groupstatus属性
	 */
	public void setGroupstatus(String groupstatus)
	{
		this.groupstatus = groupstatus;
	}
	
	/**
	 *获取"工作组状态"属性
	 */
	public String getGroupstatus()
	{
		return this.groupstatus;
	}	   

	/**
	 *设置"负责人"属性
	 *@param manager 实体的Manager属性
	 */
	public void setManager(String manager)
	{
		this.manager = manager;
	}
	
	/**
	 *获取"负责人"属性
	 */
	public String getManager()
	{
		return this.manager;
	}	   

	/**
	 *设置"隶属机构"属性
	 *@param orgid 实体的Orgid属性
	 */
	public void setOrgid(String orgid)
	{
		this.orgid = orgid;
	}
	
	/**
	 *获取"隶属机构"属性
	 */
	public String getOrgid()
	{
		return this.orgid;
	}	   

	/**
	 *设置"父工作组编号"属性
	 *@param parentgroupid 实体的Parentgroupid属性
	 */
	public void setParentgroupid(String parentgroupid)
	{
		this.parentgroupid = parentgroupid;
	}
	
	/**
	 *获取"父工作组编号"属性
	 */
	public String getParentgroupid()
	{
		return this.parentgroupid;
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
	public void setSubcount(String subcount)
	{
		this.subcount = subcount;
	}
	
	/**
	 *获取"子节点数"属性
	 */
	public String getSubcount()
	{
		return this.subcount;
	}	   
	public Om_group() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Om_group(
		String groupid
	 	,String groupcode
	 	,String groupname
	 	,int grouplevel
	 	,String groupdesc
	 	,String grouptype
	 	,String groupseq
	 	,Date startdate
	 	,Date enddate
	 	,String groupstatus
	 	,String manager
	 	,String orgid
	 	,String parentgroupid
	 	,String createtime
	 	,String lastupdate
	 	,String updator
	 	,String isleaf
	 	,String subcount
		 ){
		super();
		this.groupid = groupid;
	 	this.groupcode = groupcode;
	 	this.groupname = groupname;
	 	this.grouplevel = grouplevel;
	 	this.groupdesc = groupdesc;
	 	this.grouptype = grouptype;
	 	this.groupseq = groupseq;
	 	this.startdate = startdate;
	 	this.enddate = enddate;
	 	this.groupstatus = groupstatus;
	 	this.manager = manager;
	 	this.orgid = orgid;
	 	this.parentgroupid = parentgroupid;
	 	this.createtime = createtime;
	 	this.lastupdate = lastupdate;
	 	this.updator = updator;
	 	this.isleaf = isleaf;
	 	this.subcount = subcount;
	}
}

