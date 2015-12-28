package com.om.pojo;

import java.sql.Date;
/**
 * 机构信息表 实体类
 *@author ZhangRuiLong
 */
public class Om_organization
{
   /**
    * 机构编号,主键
    */
   private String orgid; 
   /**
    * 机构代码
    */
   private String orgcode;   
   /**
    * 机构名称
    */
   private String orgname;   
   /**
    * 机构层次
    */
   private int orglevel;   
   /**
    * 机构等级
    */
   private String orgdegree;   
   /**
    * 父机构编号
    */
   private String parentorgid;   
   /**
    * 机构序列
    */
   private String orgseq;   
   /**
    * 机构类型
    */
   private String orgtype;   
   /**
    * 机构地址
    */
   private String orgaddr;   
   /**
    * 邮编
    */
   private String zipcode;   
   /**
    * 机构主管岗位
    */
   private String manaposition;   
   /**
    * 机构主管人员
    */
   private String managerid;   
   /**
    * 机构管理员
    */
   private String orgmanager;   
   /**
    * 联系人
    */
   private String linkman;   
   /**
    * 联系电话
    */
   private String linktel;   
   /**
    * 电子邮件
    */
   private String email;   
   /**
    * 网站地址
    */
   private String weburl;   
   /**
    * 生效日期
    */
   private Date startdate;   
   /**
    * 失效日期
    */
   private Date enddate;   
   /**
    * 机构状态
    */
   private String status;   
   /**
    * 所属地域
    */
   private String area;   
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
    * 排列顺序编号
    */
   private int sortno;   
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
	 *设置主键"机构编号"属性
	 *@param orgid 实体的Orgid属性
	 */
	public void setOrgid(String orgid)
	{
		this.orgid = orgid;
	}
	
	/**
	 *获取主键"机构编号"属性
	 */
	public String getOrgid()
	{
		return this.orgid;
	}

	/**
	 *设置"机构代码"属性
	 *@param orgcode 实体的Orgcode属性
	 */
	public void setOrgcode(String orgcode)
	{
		this.orgcode = orgcode;
	}
	
	/**
	 *获取"机构代码"属性
	 */
	public String getOrgcode()
	{
		return this.orgcode;
	}	   

	/**
	 *设置"机构名称"属性
	 *@param orgname 实体的Orgname属性
	 */
	public void setOrgname(String orgname)
	{
		this.orgname = orgname;
	}
	
	/**
	 *获取"机构名称"属性
	 */
	public String getOrgname()
	{
		return this.orgname;
	}	   

	/**
	 *设置"机构层次"属性
	 *@param orglevel 实体的Orglevel属性
	 */
	public void setOrglevel(int orglevel)
	{
		this.orglevel = orglevel;
	}
	
	/**
	 *获取"机构层次"属性
	 */
	public int getOrglevel()
	{
		return this.orglevel;
	}	   

	/**
	 *设置"机构等级"属性
	 *@param orgdegree 实体的Orgdegree属性
	 */
	public void setOrgdegree(String orgdegree)
	{
		this.orgdegree = orgdegree;
	}
	
	/**
	 *获取"机构等级"属性
	 */
	public String getOrgdegree()
	{
		return this.orgdegree;
	}	   

	/**
	 *设置"父机构编号"属性
	 *@param parentorgid 实体的Parentorgid属性
	 */
	public void setParentorgid(String parentorgid)
	{
		this.parentorgid = parentorgid;
	}
	
	/**
	 *获取"父机构编号"属性
	 */
	public String getParentorgid()
	{
		return this.parentorgid;
	}	   

	/**
	 *设置"机构序列"属性
	 *@param orgseq 实体的Orgseq属性
	 */
	public void setOrgseq(String orgseq)
	{
		this.orgseq = orgseq;
	}
	
	/**
	 *获取"机构序列"属性
	 */
	public String getOrgseq()
	{
		return this.orgseq;
	}	   

	/**
	 *设置"机构类型"属性
	 *@param orgtype 实体的Orgtype属性
	 */
	public void setOrgtype(String orgtype)
	{
		this.orgtype = orgtype;
	}
	
	/**
	 *获取"机构类型"属性
	 */
	public String getOrgtype()
	{
		return this.orgtype;
	}	   

	/**
	 *设置"机构地址"属性
	 *@param orgaddr 实体的Orgaddr属性
	 */
	public void setOrgaddr(String orgaddr)
	{
		this.orgaddr = orgaddr;
	}
	
	/**
	 *获取"机构地址"属性
	 */
	public String getOrgaddr()
	{
		return this.orgaddr;
	}	   

	/**
	 *设置"邮编"属性
	 *@param zipcode 实体的Zipcode属性
	 */
	public void setZipcode(String zipcode)
	{
		this.zipcode = zipcode;
	}
	
	/**
	 *获取"邮编"属性
	 */
	public String getZipcode()
	{
		return this.zipcode;
	}	   

	/**
	 *设置"机构主管岗位"属性
	 *@param manaposition 实体的Manaposition属性
	 */
	public void setManaposition(String manaposition)
	{
		this.manaposition = manaposition;
	}
	
	/**
	 *获取"机构主管岗位"属性
	 */
	public String getManaposition()
	{
		return this.manaposition;
	}	   

	/**
	 *设置"机构主管人员"属性
	 *@param managerid 实体的Managerid属性
	 */
	public void setManagerid(String managerid)
	{
		this.managerid = managerid;
	}
	
	/**
	 *获取"机构主管人员"属性
	 */
	public String getManagerid()
	{
		return this.managerid;
	}	   

	/**
	 *设置"机构管理员"属性
	 *@param orgmanager 实体的Orgmanager属性
	 */
	public void setOrgmanager(String orgmanager)
	{
		this.orgmanager = orgmanager;
	}
	
	/**
	 *获取"机构管理员"属性
	 */
	public String getOrgmanager()
	{
		return this.orgmanager;
	}	   

	/**
	 *设置"联系人"属性
	 *@param linkman 实体的Linkman属性
	 */
	public void setLinkman(String linkman)
	{
		this.linkman = linkman;
	}
	
	/**
	 *获取"联系人"属性
	 */
	public String getLinkman()
	{
		return this.linkman;
	}	   

	/**
	 *设置"联系电话"属性
	 *@param linktel 实体的Linktel属性
	 */
	public void setLinktel(String linktel)
	{
		this.linktel = linktel;
	}
	
	/**
	 *获取"联系电话"属性
	 */
	public String getLinktel()
	{
		return this.linktel;
	}	   

	/**
	 *设置"电子邮件"属性
	 *@param email 实体的Email属性
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	/**
	 *获取"电子邮件"属性
	 */
	public String getEmail()
	{
		return this.email;
	}	   

	/**
	 *设置"网站地址"属性
	 *@param weburl 实体的Weburl属性
	 */
	public void setWeburl(String weburl)
	{
		this.weburl = weburl;
	}
	
	/**
	 *获取"网站地址"属性
	 */
	public String getWeburl()
	{
		return this.weburl;
	}	   

	/**
	 *设置"生效日期"属性
	 *@param startdate 实体的Startdate属性
	 */
	public void setStartdate(Date startdate)
	{
		this.startdate = startdate;
	}
	
	/**
	 *获取"生效日期"属性
	 */
	public Date getStartdate()
	{
		return this.startdate;
	}	   

	/**
	 *设置"失效日期"属性
	 *@param enddate 实体的Enddate属性
	 */
	public void setEnddate(Date enddate)
	{
		this.enddate = enddate;
	}
	
	/**
	 *获取"失效日期"属性
	 */
	public Date getEnddate()
	{
		return this.enddate;
	}	   

	/**
	 *设置"机构状态"属性
	 *@param status 实体的Status属性
	 */
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	/**
	 *获取"机构状态"属性
	 */
	public String getStatus()
	{
		return this.status;
	}	   

	/**
	 *设置"所属地域"属性
	 *@param area 实体的Area属性
	 */
	public void setArea(String area)
	{
		this.area = area;
	}
	
	/**
	 *获取"所属地域"属性
	 */
	public String getArea()
	{
		return this.area;
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
	 *设置"排列顺序编号"属性
	 *@param sortno 实体的Sortno属性
	 */
	public void setSortno(int sortno)
	{
		this.sortno = sortno;
	}
	
	/**
	 *获取"排列顺序编号"属性
	 */
	public int getSortno()
	{
		return this.sortno;
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
	public Om_organization() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Om_organization(
		String orgid
	 	,String orgcode
	 	,String orgname
	 	,int orglevel
	 	,String orgdegree
	 	,String parentorgid
	 	,String orgseq
	 	,String orgtype
	 	,String orgaddr
	 	,String zipcode
	 	,String manaposition
	 	,String managerid
	 	,String orgmanager
	 	,String linkman
	 	,String linktel
	 	,String email
	 	,String weburl
	 	,Date startdate
	 	,Date enddate
	 	,String status
	 	,String area
	 	,String createtime
	 	,String lastupdate
	 	,String updator
	 	,int sortno
	 	,String isleaf
	 	,int subcount
	 	,String remark
		 ){
		super();
		this.orgid = orgid;
	 	this.orgcode = orgcode;
	 	this.orgname = orgname;
	 	this.orglevel = orglevel;
	 	this.orgdegree = orgdegree;
	 	this.parentorgid = parentorgid;
	 	this.orgseq = orgseq;
	 	this.orgtype = orgtype;
	 	this.orgaddr = orgaddr;
	 	this.zipcode = zipcode;
	 	this.manaposition = manaposition;
	 	this.managerid = managerid;
	 	this.orgmanager = orgmanager;
	 	this.linkman = linkman;
	 	this.linktel = linktel;
	 	this.email = email;
	 	this.weburl = weburl;
	 	this.startdate = startdate;
	 	this.enddate = enddate;
	 	this.status = status;
	 	this.area = area;
	 	this.createtime = createtime;
	 	this.lastupdate = lastupdate;
	 	this.updator = updator;
	 	this.sortno = sortno;
	 	this.isleaf = isleaf;
	 	this.subcount = subcount;
	 	this.remark = remark;
	}
}

