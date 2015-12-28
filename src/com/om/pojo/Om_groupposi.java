package com.om.pojo;

import java.sql.Date;
/**
 * 工作组岗位列表 实体类
 *@author ZhangRuiLong
 */
public class Om_groupposi
{
   /**
    * groposid,主键
    */
   private String groposid; 
   /**
    * 工作组编号
    */
   private String groupid;   
   /**
    * 岗位编号
    */
   private String positionid;   
    //属性方法	    
     /**
	 *设置主键"groposid"属性
	 *@param groposid 实体的Groposid属性
	 */
	public void setGroposid(String groposid)
	{
		this.groposid = groposid;
	}
	
	/**
	 *获取主键"groposid"属性
	 */
	public String getGroposid()
	{
		return this.groposid;
	}

	/**
	 *设置"工作组编号"属性
	 *@param groupid 实体的Groupid属性
	 */
	public void setGroupid(String groupid)
	{
		this.groupid = groupid;
	}
	
	/**
	 *获取"工作组编号"属性
	 */
	public String getGroupid()
	{
		return this.groupid;
	}	   

	/**
	 *设置"岗位编号"属性
	 *@param positionid 实体的Positionid属性
	 */
	public void setPositionid(String positionid)
	{
		this.positionid = positionid;
	}
	
	/**
	 *获取"岗位编号"属性
	 */
	public String getPositionid()
	{
		return this.positionid;
	}	   
	public Om_groupposi() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Om_groupposi(
		String groposid
	 	,String groupid
	 	,String positionid
		 ){
		super();
		this.groposid = groposid;
	 	this.groupid = groupid;
	 	this.positionid = positionid;
	}
}

