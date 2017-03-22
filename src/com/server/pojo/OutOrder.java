package com.server.pojo;
/**
 * 出库单
 * @author tao
 *
 */
public class OutOrder {/**
    * ID,主键
    */
   private String idwarrantout; 
	/**
    * 订单总表ID
    */
   private String warrantoutodm;   
   /**
    * 编码
    */
   private String ordermcode;   
   /**
    * 种类数
    */
   private Integer ordermnum;   
   /**
    * 客户名称
    */
   private String customershop;   
   /**
    * 联系人
    */
   private String customername;   
   /**
    * 手机
    */
   private String customerphone;   
   /**
    * 客户ID
    */
   private String customerid;   
   /**
    * 出库时间
    */
   private String warrantoutupdwhen;   

   /**
	 *设置主键"ID"属性
	 *@param idwarrantout 实体的Idwarrantout属性
	 */
	public void setIdwarrantout(String idwarrantout)
	{
		this.idwarrantout = idwarrantout;
	}
	
	/**
	 *获取主键"ID"属性
	 */
	public String getIdwarrantout()
	{
		return this.idwarrantout;
	}
	
	/**
	 *设置"订单总表ID"属性
	 *@param warrantoutodm 实体的Warrantoutodm属性
	 */
	public void setWarrantoutodm(String warrantoutodm)
	{
		this.warrantoutodm = warrantoutodm;
	}
	
	/**
	 *获取"订单总表ID"属性
	 */
	public String getWarrantoutodm()
	{
		return this.warrantoutodm;
	}	   

	/**
	 *设置"编码"属性
	 *@param ordermcode 实体的Ordermcode属性
	 */
	public void setOrdermcode(String ordermcode)
	{
		this.ordermcode = ordermcode;
	}
	
	/**
	 *获取"编码"属性
	 */
	public String getOrdermcode()
	{
		return this.ordermcode;
	}	   

	/**
	 *设置"种类数"属性
	 *@param ordermnum 实体的Ordermnum属性
	 */
	public void setOrdermnum(Integer ordermnum)
	{
		this.ordermnum = ordermnum;
	}
	
	/**
	 *获取"种类数"属性
	 */
	public Integer getOrdermnum()
	{
		return this.ordermnum;
	}	   

	/**
	 *设置"客户名称"属性
	 *@param customershop 实体的Customershop属性
	 */
	public void setCustomershop(String customershop)
	{
		this.customershop = customershop;
	}
	
	/**
	 *获取"客户名称"属性
	 */
	public String getCustomershop()
	{
		return this.customershop;
	}	   

	/**
	 *设置"联系人"属性
	 *@param customername 实体的Customername属性
	 */
	public void setCustomername(String customername)
	{
		this.customername = customername;
	}
	
	/**
	 *获取"联系人"属性
	 */
	public String getCustomername()
	{
		return this.customername;
	}	   

	/**
	 *设置"手机"属性
	 *@param customerphone 实体的Customerphone属性
	 */
	public void setCustomerphone(String customerphone)
	{
		this.customerphone = customerphone;
	}
	
	/**
	 *获取"手机"属性
	 */
	public String getCustomerphone()
	{
		return this.customerphone;
	}	   

	/**
	 *设置"修改时间"属性
	 *@param warrantoutupdwhen 实体的Warrantoutupdwhen属性
	 */
	public void setWarrantoutupdwhen(String warrantoutupdwhen)
	{
		this.warrantoutupdwhen = warrantoutupdwhen;
	}
	
	/**
	 *获取"修改时间"属性
	 */
	public String getWarrantoutupdwhen()
	{
		return this.warrantoutupdwhen;
	}	   
	
    /**
	 *设置主键"客户ID"属性
	 *@param customerid 实体的Customerid属性
	 */
	public void setCustomerid(String customerid)
	{
		this.customerid = customerid;
	}
	
	/**
	 *获取主键"客户ID"属性
	 */
	public String getCustomerid()
	{
		return this.customerid;
	}

}
