package com.server.pojo.entity;

/**
 * 模板样式 实体类
 *@author ZhangRuiLong
 */
public class System_temprule
{
   /**
    * ID,主键
    */
   private String id; 
   /**
    * 模板代码
    */
   private String tempcode;   
   /**
    * 模板名称
    */
   private String tempname;   
   /**
    * 页签序号
    */
   private Integer sheetno;   
   /**
    * 页签名称
    */
   private String sheetname;   
   /**
    * 录入表代码
    */
   private String tablecode;   
   /**
    * 表头序号
    */
   private Integer headno;   
   /**
    * 表头代码
    */
   private String headcode;   
   /**
    * 表头名称
    */
   private String headname;   
   /**
    * 表头别名
    */
   private String headnameas;   
   /**
    * 表头对应录入表字段名
    */
   private String fieldname;   
   /**
    * 表头级次
    */
   private Integer headlevel;   
   /**
    * 表头开始行
    */
   private Integer startrow;   
   /**
    * 表头结束行
    */
   private Integer endrow;   
   /**
    * 表头开始列
    */
   private Integer startcol;   
   /**
    * 表头结束列
    */
   private Integer endcol;   
   /**
    * 备注
    */
   private String detail;   
    //属性方法	    
     /**
	 *设置主键"ID"属性
	 *@param id 实体的Id属性
	 */
	public void setId(String id)
	{
		this.id = id;
	}
	
	/**
	 *获取主键"ID"属性
	 */
	public String getId()
	{
		return this.id;
	}

	/**
	 *设置"模板代码"属性
	 *@param tempcode 实体的Tempcode属性
	 */
	public void setTempcode(String tempcode)
	{
		this.tempcode = tempcode;
	}
	
	/**
	 *获取"模板代码"属性
	 */
	public String getTempcode()
	{
		return this.tempcode;
	}	   

	/**
	 *设置"模板名称"属性
	 *@param tempname 实体的Tempname属性
	 */
	public void setTempname(String tempname)
	{
		this.tempname = tempname;
	}
	
	/**
	 *获取"模板名称"属性
	 */
	public String getTempname()
	{
		return this.tempname;
	}	   

	/**
	 *设置"页签序号"属性
	 *@param sheetno 实体的Sheetno属性
	 */
	public void setSheetno(Integer sheetno)
	{
		this.sheetno = sheetno;
	}
	
	/**
	 *获取"页签序号"属性
	 */
	public Integer getSheetno()
	{
		return this.sheetno;
	}	   

	/**
	 *设置"页签名称"属性
	 *@param sheetname 实体的Sheetname属性
	 */
	public void setSheetname(String sheetname)
	{
		this.sheetname = sheetname;
	}
	
	/**
	 *获取"页签名称"属性
	 */
	public String getSheetname()
	{
		return this.sheetname;
	}	   

	/**
	 *设置"录入表代码"属性
	 *@param tablecode 实体的Tablecode属性
	 */
	public void setTablecode(String tablecode)
	{
		this.tablecode = tablecode;
	}
	
	/**
	 *获取"录入表代码"属性
	 */
	public String getTablecode()
	{
		return this.tablecode;
	}	   

	/**
	 *设置"表头序号"属性
	 *@param headno 实体的Headno属性
	 */
	public void setHeadno(Integer headno)
	{
		this.headno = headno;
	}
	
	/**
	 *获取"表头序号"属性
	 */
	public Integer getHeadno()
	{
		return this.headno;
	}	   

	/**
	 *设置"表头代码"属性
	 *@param headcode 实体的Headcode属性
	 */
	public void setHeadcode(String headcode)
	{
		this.headcode = headcode;
	}
	
	/**
	 *获取"表头代码"属性
	 */
	public String getHeadcode()
	{
		return this.headcode;
	}	   

	/**
	 *设置"表头名称"属性
	 *@param headname 实体的Headname属性
	 */
	public void setHeadname(String headname)
	{
		this.headname = headname;
	}
	
	/**
	 *获取"表头名称"属性
	 */
	public String getHeadname()
	{
		return this.headname;
	}	   

	/**
	 *设置"表头别名"属性
	 *@param headnameas 实体的Headnameas属性
	 */
	public void setHeadnameas(String headnameas)
	{
		this.headnameas = headnameas;
	}
	
	/**
	 *获取"表头别名"属性
	 */
	public String getHeadnameas()
	{
		return this.headnameas;
	}	   

	/**
	 *设置"表头对应录入表字段名"属性
	 *@param fieldname 实体的Fieldname属性
	 */
	public void setFieldname(String fieldname)
	{
		this.fieldname = fieldname;
	}
	
	/**
	 *获取"表头对应录入表字段名"属性
	 */
	public String getFieldname()
	{
		return this.fieldname;
	}	   

	/**
	 *设置"表头级次"属性
	 *@param headlevel 实体的Headlevel属性
	 */
	public void setHeadlevel(Integer headlevel)
	{
		this.headlevel = headlevel;
	}
	
	/**
	 *获取"表头级次"属性
	 */
	public Integer getHeadlevel()
	{
		return this.headlevel;
	}	   

	/**
	 *设置"表头开始行"属性
	 *@param startrow 实体的Startrow属性
	 */
	public void setStartrow(Integer startrow)
	{
		this.startrow = startrow;
	}
	
	/**
	 *获取"表头开始行"属性
	 */
	public Integer getStartrow()
	{
		return this.startrow;
	}	   

	/**
	 *设置"表头结束行"属性
	 *@param endrow 实体的Endrow属性
	 */
	public void setEndrow(Integer endrow)
	{
		this.endrow = endrow;
	}
	
	/**
	 *获取"表头结束行"属性
	 */
	public Integer getEndrow()
	{
		return this.endrow;
	}	   

	/**
	 *设置"表头开始列"属性
	 *@param startcol 实体的Startcol属性
	 */
	public void setStartcol(Integer startcol)
	{
		this.startcol = startcol;
	}
	
	/**
	 *获取"表头开始列"属性
	 */
	public Integer getStartcol()
	{
		return this.startcol;
	}	   

	/**
	 *设置"表头结束列"属性
	 *@param endcol 实体的Endcol属性
	 */
	public void setEndcol(Integer endcol)
	{
		this.endcol = endcol;
	}
	
	/**
	 *获取"表头结束列"属性
	 */
	public Integer getEndcol()
	{
		return this.endcol;
	}	   

	/**
	 *设置"备注"属性
	 *@param detail 实体的Detail属性
	 */
	public void setDetail(String detail)
	{
		this.detail = detail;
	}
	
	/**
	 *获取"备注"属性
	 */
	public String getDetail()
	{
		return this.detail;
	}	   
	public System_temprule() {
		super();
		// TODO Auto-generated constructor stub
	}
	public System_temprule(
		String id
	 	,String tempcode
	 	,String tempname
	 	,Integer sheetno
	 	,String sheetname
	 	,String tablecode
	 	,Integer headno
	 	,String headcode
	 	,String headname
	 	,String headnameas
	 	,String fieldname
	 	,Integer headlevel
	 	,Integer startrow
	 	,Integer endrow
	 	,Integer startcol
	 	,Integer endcol
	 	,String detail
		 ){
		super();
		this.id = id;
	 	this.tempcode = tempcode;
	 	this.tempname = tempname;
	 	this.sheetno = sheetno;
	 	this.sheetname = sheetname;
	 	this.tablecode = tablecode;
	 	this.headno = headno;
	 	this.headcode = headcode;
	 	this.headname = headname;
	 	this.headnameas = headnameas;
	 	this.fieldname = fieldname;
	 	this.headlevel = headlevel;
	 	this.startrow = startrow;
	 	this.endrow = endrow;
	 	this.startcol = startcol;
	 	this.endcol = endcol;
	 	this.detail = detail;
	}
}

