package com.server.pojo.entity;

/**
 * 城市和县及街道 实体类
 *@author ZhangRuiLong
 */
public class City
{
   /**
    * 城市ID,主键
    */
   private String cityid; 
   /**
    * 编码
    */
   private String citycode;   
   /**
    * 名称
    */
   private String cityname;   
   /**
    * 父类
    */
   private String cityparent;   
   /**
    * 描述
    */
   private String citydetail;   
   /**
    * 状态
    */
   private String citystatue;   
    //属性方法	    
     /**
	 *设置主键"城市ID"属性
	 *@param cityid 实体的Cityid属性
	 */
	public void setCityid(String cityid)
	{
		this.cityid = cityid;
	}
	
	/**
	 *获取主键"城市ID"属性
	 */
	public String getCityid()
	{
		return this.cityid;
	}

	/**
	 *设置"编码"属性
	 *@param citycode 实体的Citycode属性
	 */
	public void setCitycode(String citycode)
	{
		this.citycode = citycode;
	}
	
	/**
	 *获取"编码"属性
	 */
	public String getCitycode()
	{
		return this.citycode;
	}	   

	/**
	 *设置"名称"属性
	 *@param cityname 实体的Cityname属性
	 */
	public void setCityname(String cityname)
	{
		this.cityname = cityname;
	}
	
	/**
	 *获取"名称"属性
	 */
	public String getCityname()
	{
		return this.cityname;
	}	   

	/**
	 *设置"父类"属性
	 *@param cityparent 实体的Cityparent属性
	 */
	public void setCityparent(String cityparent)
	{
		this.cityparent = cityparent;
	}
	
	/**
	 *获取"父类"属性
	 */
	public String getCityparent()
	{
		return this.cityparent;
	}	   

	/**
	 *设置"描述"属性
	 *@param citydetail 实体的Citydetail属性
	 */
	public void setCitydetail(String citydetail)
	{
		this.citydetail = citydetail;
	}
	
	/**
	 *获取"描述"属性
	 */
	public String getCitydetail()
	{
		return this.citydetail;
	}	   

	/**
	 *设置"状态"属性
	 *@param citystatue 实体的Citystatue属性
	 */
	public void setCitystatue(String citystatue)
	{
		this.citystatue = citystatue;
	}
	
	/**
	 *获取"状态"属性
	 */
	public String getCitystatue()
	{
		return this.citystatue;
	}	   
	public City() {
		super();
		// TODO Auto-generated constructor stub
	}
	public City(
		String cityid
	 	,String citycode
	 	,String cityname
	 	,String cityparent
	 	,String citydetail
	 	,String citystatue
		 ){
		super();
		this.cityid = cityid;
	 	this.citycode = citycode;
	 	this.cityname = cityname;
	 	this.cityparent = cityparent;
	 	this.citydetail = citydetail;
	 	this.citystatue = citystatue;
	}
}

