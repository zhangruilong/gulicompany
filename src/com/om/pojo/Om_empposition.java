package com.om.pojo;

import java.sql.Date;
/**
 * 人员岗位对应关系 实体类
 *@author ZhangRuiLong
 */
public class Om_empposition
{
   /**
    * posempid,主键
    */
   private String posempid; 
   /**
    * 岗位编号
    */
   private String positionid;   
   /**
    * 人员编号
    */
   private String empid;   
   /**
    * 是否主岗位
    */
   private String ismain;   
    //属性方法	    
     /**
	 *设置主键"posempid"属性
	 *@param posempid 实体的Posempid属性
	 */
	public void setPosempid(String posempid)
	{
		this.posempid = posempid;
	}
	
	/**
	 *获取主键"posempid"属性
	 */
	public String getPosempid()
	{
		return this.posempid;
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

	/**
	 *设置"人员编号"属性
	 *@param empid 实体的Empid属性
	 */
	public void setEmpid(String empid)
	{
		this.empid = empid;
	}
	
	/**
	 *获取"人员编号"属性
	 */
	public String getEmpid()
	{
		return this.empid;
	}	   

	/**
	 *设置"是否主岗位"属性
	 *@param ismain 实体的Ismain属性
	 */
	public void setIsmain(String ismain)
	{
		this.ismain = ismain;
	}
	
	/**
	 *获取"是否主岗位"属性
	 */
	public String getIsmain()
	{
		return this.ismain;
	}	   
	public Om_empposition() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Om_empposition(
		String posempid
	 	,String positionid
	 	,String empid
	 	,String ismain
		 ){
		super();
		this.posempid = posempid;
	 	this.positionid = positionid;
	 	this.empid = empid;
	 	this.ismain = ismain;
	}
}

