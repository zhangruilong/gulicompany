package com.server.pojo.entity;

import java.util.List;

public class PrintInfo extends Orderm {
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
	   /**
	    * 修改时间
	    */
	   private String updtime;   
	   /**
	    * 关联收藏(一对多)
	    */
	   private List<Collect> collectList;
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
