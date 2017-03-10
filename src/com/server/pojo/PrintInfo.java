package com.server.pojo;

public class PrintInfo {
	/**
	    * 订单ID,主键
	    */
	   private String ordermid; 
	   /**
	    * 客户ID
	    */
	   private String ordermcustomer;   
	   /**
	    * 经销商ID
	    */
	   private String ordermcompany;   
	   /**
	    * 编码
	    */
	   private String ordermcode;   
	   /**
	    * 种类数
	    */
	   private Integer ordermnum;   
	   /**
	    * 下单金额
	    */
	   private Float ordermmoney;   
	   /**
	    * 实际金额
	    */
	   private Float ordermrightmoney;   
	   /**
	    * 支付方式
	    */
	   private String ordermway;   
	   /**
	    * 状态
	    */
	   private String ordermstatue;   
	   /**
	    * 备注(客户留言)
	    */
	   private String ordermdetail;   
	   /**
	    * 下单时间
	    */
	   private String ordermtime;   
	   /**
	    * 联系人
	    */
	   private String ordermconnect;   
	   /**
	    * 手机
	    */
	   private String ordermphone;   
	   /**
	    * 地址
	    */
	   private String ordermaddress;   
	   /**
	    * 修改时间
	    */
	   private String updtime;   
	   /**
	    * 修改人
	    */
	   private String updor;   
	   /**
	    * 业务员ID(订单源)
	    */
	   private String ordermemp;   
	   /**
	    * 店铺名称
	    */
	   private String ordermcusshop;   
	   /**
	    * 客户等级
	    */
	   private String ordermcuslevel;   
	   /**
	    * 客户类型
	    */
	   private String ordermcustype;   
	   /**
	    * 打印次数
	    */
	   private String ordermprinttimes;   
	    //属性方法	    
	     /**
		 *设置主键"订单ID"属性
		 *@param ordermid 实体的Ordermid属性
		 */
		public void setOrdermid(String ordermid)
		{
			this.ordermid = ordermid;
		}
		
		/**
		 *获取主键"订单ID"属性
		 */
		public String getOrdermid()
		{
			return this.ordermid;
		}

		/**
		 *设置"客户ID"属性
		 *@param ordermcustomer 实体的Ordermcustomer属性
		 */
		public void setOrdermcustomer(String ordermcustomer)
		{
			this.ordermcustomer = ordermcustomer;
		}
		
		/**
		 *获取"客户ID"属性
		 */
		public String getOrdermcustomer()
		{
			return this.ordermcustomer;
		}	   

		/**
		 *设置"经销商ID"属性
		 *@param ordermcompany 实体的Ordermcompany属性
		 */
		public void setOrdermcompany(String ordermcompany)
		{
			this.ordermcompany = ordermcompany;
		}
		
		/**
		 *获取"经销商ID"属性
		 */
		public String getOrdermcompany()
		{
			return this.ordermcompany;
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
		 *设置"下单金额"属性
		 *@param ordermmoney 实体的Ordermmoney属性
		 */
		public void setOrdermmoney(Float ordermmoney)
		{
			this.ordermmoney = ordermmoney;
		}
		
		/**
		 *获取"下单金额"属性
		 */
		public Float getOrdermmoney()
		{
			return this.ordermmoney;
		}	   

		/**
		 *设置"实际金额"属性
		 *@param ordermrightmoney 实体的Ordermrightmoney属性
		 */
		public void setOrdermrightmoney(Float ordermrightmoney)
		{
			this.ordermrightmoney = ordermrightmoney;
		}
		
		/**
		 *获取"实际金额"属性
		 */
		public Float getOrdermrightmoney()
		{
			return this.ordermrightmoney;
		}	   

		/**
		 *设置"支付方式"属性
		 *@param ordermway 实体的Ordermway属性
		 */
		public void setOrdermway(String ordermway)
		{
			this.ordermway = ordermway;
		}
		
		/**
		 *获取"支付方式"属性
		 */
		public String getOrdermway()
		{
			return this.ordermway;
		}	   

		/**
		 *设置"状态"属性
		 *@param ordermstatue 实体的Ordermstatue属性
		 */
		public void setOrdermstatue(String ordermstatue)
		{
			this.ordermstatue = ordermstatue;
		}
		
		/**
		 *获取"状态"属性
		 */
		public String getOrdermstatue()
		{
			return this.ordermstatue;
		}	   

		/**
		 *设置"备注(客户留言)"属性
		 *@param ordermdetail 实体的Ordermdetail属性
		 */
		public void setOrdermdetail(String ordermdetail)
		{
			this.ordermdetail = ordermdetail;
		}
		
		/**
		 *获取"备注(客户留言)"属性
		 */
		public String getOrdermdetail()
		{
			return this.ordermdetail;
		}	   

		/**
		 *设置"下单时间"属性
		 *@param ordermtime 实体的Ordermtime属性
		 */
		public void setOrdermtime(String ordermtime)
		{
			this.ordermtime = ordermtime;
		}
		
		/**
		 *获取"下单时间"属性
		 */
		public String getOrdermtime()
		{
			return this.ordermtime;
		}	   

		/**
		 *设置"联系人"属性
		 *@param ordermconnect 实体的Ordermconnect属性
		 */
		public void setOrdermconnect(String ordermconnect)
		{
			this.ordermconnect = ordermconnect;
		}
		
		/**
		 *获取"联系人"属性
		 */
		public String getOrdermconnect()
		{
			return this.ordermconnect;
		}	   

		/**
		 *设置"手机"属性
		 *@param ordermphone 实体的Ordermphone属性
		 */
		public void setOrdermphone(String ordermphone)
		{
			this.ordermphone = ordermphone;
		}
		
		/**
		 *获取"手机"属性
		 */
		public String getOrdermphone()
		{
			return this.ordermphone;
		}	   

		/**
		 *设置"地址"属性
		 *@param ordermaddress 实体的Ordermaddress属性
		 */
		public void setOrdermaddress(String ordermaddress)
		{
			this.ordermaddress = ordermaddress;
		}
		
		/**
		 *获取"地址"属性
		 */
		public String getOrdermaddress()
		{
			return this.ordermaddress;
		}	   

		/**
		 *设置"修改时间"属性
		 *@param updtime 实体的Updtime属性
		 */
		public void setUpdtime(String updtime)
		{
			this.updtime = updtime;
		}
		
		/**
		 *获取"修改时间"属性
		 */
		public String getUpdtime()
		{
			return this.updtime;
		}	   

		/**
		 *设置"修改人"属性
		 *@param updor 实体的Updor属性
		 */
		public void setUpdor(String updor)
		{
			this.updor = updor;
		}
		
		/**
		 *获取"修改人"属性
		 */
		public String getUpdor()
		{
			return this.updor;
		}	   

		/**
		 *设置"业务员ID(订单源)"属性
		 *@param ordermemp 实体的Ordermemp属性
		 */
		public void setOrdermemp(String ordermemp)
		{
			this.ordermemp = ordermemp;
		}
		
		/**
		 *获取"业务员ID(订单源)"属性
		 */
		public String getOrdermemp()
		{
			return this.ordermemp;
		}	   

		/**
		 *设置"店铺名称"属性
		 *@param ordermcusshop 实体的Ordermcusshop属性
		 */
		public void setOrdermcusshop(String ordermcusshop)
		{
			this.ordermcusshop = ordermcusshop;
		}
		
		/**
		 *获取"店铺名称"属性
		 */
		public String getOrdermcusshop()
		{
			return this.ordermcusshop;
		}	   

		/**
		 *设置"客户等级"属性
		 *@param ordermcuslevel 实体的Ordermcuslevel属性
		 */
		public void setOrdermcuslevel(String ordermcuslevel)
		{
			this.ordermcuslevel = ordermcuslevel;
		}
		
		/**
		 *获取"客户等级"属性
		 */
		public String getOrdermcuslevel()
		{
			return this.ordermcuslevel;
		}	   

		/**
		 *设置"客户类型"属性
		 *@param ordermcustype 实体的Ordermcustype属性
		 */
		public void setOrdermcustype(String ordermcustype)
		{
			this.ordermcustype = ordermcustype;
		}
		
		/**
		 *获取"客户类型"属性
		 */
		public String getOrdermcustype()
		{
			return this.ordermcustype;
		}	   
		/**
		 *设置"打印次数"属性
		 *@param ordermprinttimes 实体的Ordermprinttimes属性
		 */
		public void setOrdermprinttimes(String ordermprinttimes)
		{
			this.ordermprinttimes = ordermprinttimes;
		}
		
		/**
		 *获取"打印次数"属性
		 */
		public String getOrdermprinttimes()
		{
			return this.ordermprinttimes;
		}	   
	/**
	    * 客户ID,主键
	    */
	   private String customerid; 
	   /**
	    * 编码
	    */
	   private String customercode;   
	   /**
	    * 姓名
	    */
	   private String customername;   
	   /**
	    * 手机
	    */
	   private String customerphone;   
	   /**
	    * 密码
	    */
	   private String customerpsw;   
	   /**
	    * 店铺
	    */
	   private String customershop;   
	   /**
	    * 城市
	    */
	   private String customercity;   
	   /**
	    * 县
	    */
	   private String customerxian;   
	   /**
	    * 街道地址
	    */
	   private String customeraddress;   
	   /**
	    * 类型
	    */
	   private String customertype;   
	   /**
	    * 等级
	    */
	   private Integer customerlevel;   
	   /**
	    * openid
	    */
	   private String openid;   
	   /**
	    * 描述
	    */
	   private String customerdetail;   
	   /**
	    * 状态
	    */
	   private String customerstatue;   
	   /**
	    * 创建时间
	    */
	   private String createtime;   
	    //属性方法	    
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

		/**
		 *设置"编码"属性
		 *@param customercode 实体的Customercode属性
		 */
		public void setCustomercode(String customercode)
		{
			this.customercode = customercode;
		}
		
		/**
		 *获取"编码"属性
		 */
		public String getCustomercode()
		{
			return this.customercode;
		}	   

		/**
		 *设置"姓名"属性
		 *@param customername 实体的Customername属性
		 */
		public void setCustomername(String customername)
		{
			this.customername = customername;
		}
		
		/**
		 *获取"姓名"属性
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
		 *设置"密码"属性
		 *@param customerpsw 实体的Customerpsw属性
		 */
		public void setCustomerpsw(String customerpsw)
		{
			this.customerpsw = customerpsw;
		}
		
		/**
		 *获取"密码"属性
		 */
		public String getCustomerpsw()
		{
			return this.customerpsw;
		}	   

		/**
		 *设置"店铺"属性
		 *@param customershop 实体的Customershop属性
		 */
		public void setCustomershop(String customershop)
		{
			this.customershop = customershop;
		}
		
		/**
		 *获取"店铺"属性
		 */
		public String getCustomershop()
		{
			return this.customershop;
		}	   

		/**
		 *设置"城市"属性
		 *@param customercity 实体的Customercity属性
		 */
		public void setCustomercity(String customercity)
		{
			this.customercity = customercity;
		}
		
		/**
		 *获取"城市"属性
		 */
		public String getCustomercity()
		{
			return this.customercity;
		}	   

		/**
		 *设置"县"属性
		 *@param customerxian 实体的Customerxian属性
		 */
		public void setCustomerxian(String customerxian)
		{
			this.customerxian = customerxian;
		}
		
		/**
		 *获取"县"属性
		 */
		public String getCustomerxian()
		{
			return this.customerxian;
		}	   

		/**
		 *设置"街道地址"属性
		 *@param customeraddress 实体的Customeraddress属性
		 */
		public void setCustomeraddress(String customeraddress)
		{
			this.customeraddress = customeraddress;
		}
		
		/**
		 *获取"街道地址"属性
		 */
		public String getCustomeraddress()
		{
			return this.customeraddress;
		}	   

		/**
		 *设置"类型"属性
		 *@param customertype 实体的Customertype属性
		 */
		public void setCustomertype(String customertype)
		{
			this.customertype = customertype;
		}
		
		/**
		 *获取"类型"属性
		 */
		public String getCustomertype()
		{
			return this.customertype;
		}	   

		/**
		 *设置"等级"属性
		 *@param customerlevel 实体的Customerlevel属性
		 */
		public void setCustomerlevel(Integer customerlevel)
		{
			this.customerlevel = customerlevel;
		}
		
		/**
		 *获取"等级"属性
		 */
		public Integer getCustomerlevel()
		{
			return this.customerlevel;
		}	   

		/**
		 *设置"openid"属性
		 *@param openid 实体的Openid属性
		 */
		public void setOpenid(String openid)
		{
			this.openid = openid;
		}
		
		/**
		 *获取"openid"属性
		 */
		public String getOpenid()
		{
			return this.openid;
		}	   

		/**
		 *设置"描述"属性
		 *@param customerdetail 实体的Customerdetail属性
		 */
		public void setCustomerdetail(String customerdetail)
		{
			this.customerdetail = customerdetail;
		}
		
		/**
		 *获取"描述"属性
		 */
		public String getCustomerdetail()
		{
			return this.customerdetail;
		}	   

		/**
		 *设置"状态"属性
		 *@param customerstatue 实体的Customerstatue属性
		 */
		public void setCustomerstatue(String customerstatue)
		{
			this.customerstatue = customerstatue;
		}
		
		/**
		 *获取"状态"属性
		 */
		public String getCustomerstatue()
		{
			return this.customerstatue;
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
		    * 经销商ID,主键
		    */
		   private String companyid; 
		   /**
		    * 编码
		    */
		   private String companycode;   
		   /**
		    * 姓名
		    */
		   private String username;   
		   /**
		    * 手机
		    */
		   private String companyphone;   
		   /**
		    * 店铺
		    */
		   private String companyshop;   
		   /**
		    * 城市和县ID
		    */
		   private String companycity;   
		   /**
		    * 街道地址
		    */
		   private String companyaddress;   
		   /**
		    * 描述
		    */
		   private String companydetail;   
		   /**
		    * 状态
		    */
		   private String companystatue;   
		    //属性方法	    
		     /**
			 *设置主键"经销商ID"属性
			 *@param companyid 实体的Companyid属性
			 */
			public void setCompanyid(String companyid)
			{
				this.companyid = companyid;
			}
			
			/**
			 *获取主键"经销商ID"属性
			 */
			public String getCompanyid()
			{
				return this.companyid;
			}

			/**
			 *设置"编码"属性
			 *@param companycode 实体的Companycode属性
			 */
			public void setCompanycode(String companycode)
			{
				this.companycode = companycode;
			}
			
			/**
			 *获取"编码"属性
			 */
			public String getCompanycode()
			{
				return this.companycode;
			}	   

			/**
			 *设置"姓名"属性
			 *@param username 实体的Username属性
			 */
			public void setUsername(String username)
			{
				this.username = username;
			}
			
			/**
			 *获取"姓名"属性
			 */
			public String getUsername()
			{
				return this.username;
			}	   

			/**
			 *设置"手机"属性
			 *@param companyphone 实体的Companyphone属性
			 */
			public void setCompanyphone(String companyphone)
			{
				this.companyphone = companyphone;
			}
			
			/**
			 *获取"手机"属性
			 */
			public String getCompanyphone()
			{
				return this.companyphone;
			}	   

			/**
			 *设置"店铺"属性
			 *@param companyshop 实体的Companyshop属性
			 */
			public void setCompanyshop(String companyshop)
			{
				this.companyshop = companyshop;
			}
			
			/**
			 *获取"店铺"属性
			 */
			public String getCompanyshop()
			{
				return this.companyshop;
			}	   

			/**
			 *设置"城市和县ID"属性
			 *@param companycity 实体的Companycity属性
			 */
			public void setCompanycity(String companycity)
			{
				this.companycity = companycity;
			}
			
			/**
			 *获取"城市和县ID"属性
			 */
			public String getCompanycity()
			{
				return this.companycity;
			}	   

			/**
			 *设置"街道地址"属性
			 *@param companyaddress 实体的Companyaddress属性
			 */
			public void setCompanyaddress(String companyaddress)
			{
				this.companyaddress = companyaddress;
			}
			
			/**
			 *获取"街道地址"属性
			 */
			public String getCompanyaddress()
			{
				return this.companyaddress;
			}	   

			/**
			 *设置"描述"属性
			 *@param companydetail 实体的Companydetail属性
			 */
			public void setCompanydetail(String companydetail)
			{
				this.companydetail = companydetail;
			}
			
			/**
			 *获取"描述"属性
			 */
			public String getCompanydetail()
			{
				return this.companydetail;
			}	   

			/**
			 *设置"状态"属性
			 *@param companystatue 实体的Companystatue属性
			 */
			public void setCompanystatue(String companystatue)
			{
				this.companystatue = companystatue;
			}
			
			/**
			 *获取"状态"属性
			 */
			public String getCompanystatue()
			{
				return this.companystatue;
			}	   

}
