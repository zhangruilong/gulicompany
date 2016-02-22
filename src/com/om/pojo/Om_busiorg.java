package com.om.pojo;

import java.sql.Date;
/**
 * 业务机构 实体类
 *@author ZhangRuiLong
 */
public class Om_busiorg
{
   /**
    * 业务条线,主键
    */
   private String busidomain; 		//句话是用来测试git的
   /**
    * 业务机构编号
    */
   private String busiorgid;   
   /**
    * 业务机构名称
    */
   private String orgname;   
   /**
    * 上级业务机构
    */
   private String parentid;   
   /**
    * 机构编号
    */
   private String orgid;   
   /**
    * 业务机构层次
    */
   private int orglevel;   
   /**
    * 节点类型
    */
   private String nodetype;   
   /**
    * 机构代号
    */
   private String orgcode;   
   /**
    * 序列号
    */
   private String seqno;   
   /**
    * 主管岗位
    */
   private String manapos;   
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
    //属性方法	    
     /**
	 *设置主键"业务条线"属性
	 *@param busidomain 实体的Busidomain属性
	 */
	public void setBusidomain(String busidomain)
	{
		this.busidomain = busidomain;
	}
	
	/**
	 *获取主键"业务条线"属性
	 */
	public String getBusidomain()
	{
		return this.busidomain;
	}

	/**
	 *设置"业务机构编号"属性
	 *@param busiorgid 实体的Busiorgid属性
	 */
	public void setBusiorgid(String busiorgid)
	{
		this.busiorgid = busiorgid;
	}
	
	/**
	 *获取"业务机构编号"属性
	 */
	public String getBusiorgid()
	{
		return this.busiorgid;
	}	   

	/**
	 *设置"业务机构名称"属性
	 *@param orgname 实体的Orgname属性
	 */
	public void setOrgname(String orgname)
	{
		this.orgname = orgname;
	}
	
	/**
	 *获取"业务机构名称"属性
	 */
	public String getOrgname()
	{
		return this.orgname;
	}	   

	/**
	 *设置"上级业务机构"属性
	 *@param parentid 实体的Parentid属性
	 */
	public void setParentid(String parentid)
	{
		this.parentid = parentid;
	}
	
	/**
	 *获取"上级业务机构"属性
	 */
	public String getParentid()
	{
		return this.parentid;
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
	 *设置"业务机构层次"属性
	 *@param orglevel 实体的Orglevel属性
	 */
	public void setOrglevel(int orglevel)
	{
		this.orglevel = orglevel;
	}
	
	/**
	 *获取"业务机构层次"属性
	 */
	public int getOrglevel()
	{
		return this.orglevel;
	}	   

	/**
	 *设置"节点类型"属性
	 *@param nodetype 实体的Nodetype属性
	 */
	public void setNodetype(String nodetype)
	{
		this.nodetype = nodetype;
	}
	
	/**
	 *获取"节点类型"属性
	 */
	public String getNodetype()
	{
		return this.nodetype;
	}	   

	/**
	 *设置"机构代号"属性
	 *@param orgcode 实体的Orgcode属性
	 */
	public void setOrgcode(String orgcode)
	{
		this.orgcode = orgcode;
	}
	
	/**
	 *获取"机构代号"属性
	 */
	public String getOrgcode()
	{
		return this.orgcode;
	}	   

	/**
	 *设置"序列号"属性
	 *@param seqno 实体的Seqno属性
	 */
	public void setSeqno(String seqno)
	{
		this.seqno = seqno;
	}
	
	/**
	 *获取"序列号"属性
	 */
	public String getSeqno()
	{
		return this.seqno;
	}	   

	/**
	 *设置"主管岗位"属性
	 *@param manapos 实体的Manapos属性
	 */
	public void setManapos(String manapos)
	{
		this.manapos = manapos;
	}
	
	/**
	 *获取"主管岗位"属性
	 */
	public String getManapos()
	{
		return this.manapos;
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
	public Om_busiorg() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Om_busiorg(
		String busidomain
	 	,String busiorgid
	 	,String orgname
	 	,String parentid
	 	,String orgid
	 	,int orglevel
	 	,String nodetype
	 	,String orgcode
	 	,String seqno
	 	,String manapos
	 	,int sortno
	 	,String isleaf
	 	,int subcount
		 ){
		super();
		this.busidomain = busidomain;
	 	this.busiorgid = busiorgid;
	 	this.orgname = orgname;
	 	this.parentid = parentid;
	 	this.orgid = orgid;
	 	this.orglevel = orglevel;
	 	this.nodetype = nodetype;
	 	this.orgcode = orgcode;
	 	this.seqno = seqno;
	 	this.manapos = manapos;
	 	this.sortno = sortno;
	 	this.isleaf = isleaf;
	 	this.subcount = subcount;
	}
}

