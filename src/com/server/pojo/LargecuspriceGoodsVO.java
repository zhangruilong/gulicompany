package com.server.pojo;
/**
 * 特殊价格商品
 * @author tao
 *
 */
public class LargecuspriceGoodsVO {
	/**
	    * 商品ID,主键
	    */
	   private String goodsid; 
	   /**
	    * 经销商ID
	    */
	   private String goodscompany;   
	   /**
	    * 编码
	    */
	   private String goodscode;   
	   /**
	    * 名称
	    */
	   private String goodsname;   
	   /**
	    * 描述
	    */
	   private String goodsdetail;   
	   /**
	    * 规格
	    */
	   private String goodsunits;   
	   /**
	    * 小类ID
	    */
	   private String goodsclass;   
	   /**
	    * 图片
	    */
	   private String goodsimage;   
	   /**
	    * 状态
	    */
	   private String goodsstatue;   
	   /**
	    * 品牌
	    */
	   private String goodsbrand;   
	   /**
	    * 种类
	    */
	   private String goodstype;   
	   /**
	    * 顺序
	    */
	   private Integer goodsorder;   
	   /**
	    * 重量
	    */
	   private String goodsweight;   
	    //属性方法	    
	     /**
		 *设置主键"商品ID"属性
		 *@param goodsid 实体的Goodsid属性
		 */
		public void setGoodsid(String goodsid)
		{
			this.goodsid = goodsid;
		}
		
		/**
		 *获取主键"商品ID"属性
		 */
		public String getGoodsid()
		{
			return this.goodsid;
		}

		/**
		 *设置"经销商ID"属性
		 *@param goodscompany 实体的Goodscompany属性
		 */
		public void setGoodscompany(String goodscompany)
		{
			this.goodscompany = goodscompany;
		}
		
		/**
		 *获取"经销商ID"属性
		 */
		public String getGoodscompany()
		{
			return this.goodscompany;
		}	   

		/**
		 *设置"编码"属性
		 *@param goodscode 实体的Goodscode属性
		 */
		public void setGoodscode(String goodscode)
		{
			this.goodscode = goodscode;
		}
		
		/**
		 *获取"编码"属性
		 */
		public String getGoodscode()
		{
			return this.goodscode;
		}	   

		/**
		 *设置"名称"属性
		 *@param goodsname 实体的Goodsname属性
		 */
		public void setGoodsname(String goodsname)
		{
			this.goodsname = goodsname;
		}
		
		/**
		 *获取"名称"属性
		 */
		public String getGoodsname()
		{
			return this.goodsname;
		}	   

		/**
		 *设置"描述"属性
		 *@param goodsdetail 实体的Goodsdetail属性
		 */
		public void setGoodsdetail(String goodsdetail)
		{
			this.goodsdetail = goodsdetail;
		}
		
		/**
		 *获取"描述"属性
		 */
		public String getGoodsdetail()
		{
			return this.goodsdetail;
		}	   

		/**
		 *设置"规格"属性
		 *@param goodsunits 实体的Goodsunits属性
		 */
		public void setGoodsunits(String goodsunits)
		{
			this.goodsunits = goodsunits;
		}
		
		/**
		 *获取"规格"属性
		 */
		public String getGoodsunits()
		{
			return this.goodsunits;
		}	   

		/**
		 *设置"小类ID"属性
		 *@param goodsclass 实体的Goodsclass属性
		 */
		public void setGoodsclass(String goodsclass)
		{
			this.goodsclass = goodsclass;
		}
		
		/**
		 *获取"小类ID"属性
		 */
		public String getGoodsclass()
		{
			return this.goodsclass;
		}	   

		/**
		 *设置"图片"属性
		 *@param goodsimage 实体的Goodsimage属性
		 */
		public void setGoodsimage(String goodsimage)
		{
			this.goodsimage = goodsimage;
		}
		
		/**
		 *获取"图片"属性
		 */
		public String getGoodsimage()
		{
			return this.goodsimage;
		}	   

		/**
		 *设置"状态"属性
		 *@param goodsstatue 实体的Goodsstatue属性
		 */
		public void setGoodsstatue(String goodsstatue)
		{
			this.goodsstatue = goodsstatue;
		}
		
		/**
		 *获取"状态"属性
		 */
		public String getGoodsstatue()
		{
			return this.goodsstatue;
		}	   

		/**
		 *设置"品牌"属性
		 *@param goodsbrand 实体的Goodsbrand属性
		 */
		public void setGoodsbrand(String goodsbrand)
		{
			this.goodsbrand = goodsbrand;
		}
		
		/**
		 *获取"品牌"属性
		 */
		public String getGoodsbrand()
		{
			return this.goodsbrand;
		}	   

		/**
		 *设置"种类"属性
		 *@param goodstype 实体的Goodstype属性
		 */
		public void setGoodstype(String goodstype)
		{
			this.goodstype = goodstype;
		}
		
		/**
		 *获取"种类"属性
		 */
		public String getGoodstype()
		{
			return this.goodstype;
		}	   

		/**
		 *设置"顺序"属性
		 *@param goodsorder 实体的Goodsorder属性
		 */
		public void setGoodsorder(Integer goodsorder)
		{
			this.goodsorder = goodsorder;
		}
		
		/**
		 *获取"顺序"属性
		 */
		public Integer getGoodsorder()
		{
			return this.goodsorder;
		}	   

		/**
		 *设置"重量"属性
		 *@param goodsweight 实体的Goodsweight属性
		 */
		public void setGoodsweight(String goodsweight)
		{
			this.goodsweight = goodsweight;
		}
		
		/**
		 *获取"重量"属性
		 */
		public String getGoodsweight()
		{
			return this.goodsweight;
		}	   
		/**
		    * 价格体系ID,主键
		    */
		   private String pricesid; 
		   /**
		    * 商品ID
		    */
		   private String pricesgoods;   
		   /**
		    * 分类
		    */
		   private String pricesclass;   
		   /**
		    * 等级
		    */
		   private Integer priceslevel;   
		   /**
		    * 单品价
		    */
		   private Float pricesprice;   
		   /**
		    * 单品单位
		    */
		   private String pricesunit;   
		    //属性方法	    
		     /**
			 *设置主键"价格体系ID"属性
			 *@param pricesid 实体的Pricesid属性
			 */
			public void setPricesid(String pricesid)
			{
				this.pricesid = pricesid;
			}
			
			/**
			 *获取主键"价格体系ID"属性
			 */
			public String getPricesid()
			{
				return this.pricesid;
			}

			/**
			 *设置"商品ID"属性
			 *@param pricesgoods 实体的Pricesgoods属性
			 */
			public void setPricesgoods(String pricesgoods)
			{
				this.pricesgoods = pricesgoods;
			}
			
			/**
			 *获取"商品ID"属性
			 */
			public String getPricesgoods()
			{
				return this.pricesgoods;
			}	   

			/**
			 *设置"分类"属性
			 *@param pricesclass 实体的Pricesclass属性
			 */
			public void setPricesclass(String pricesclass)
			{
				this.pricesclass = pricesclass;
			}
			
			/**
			 *获取"分类"属性
			 */
			public String getPricesclass()
			{
				return this.pricesclass;
			}	   

			/**
			 *设置"等级"属性
			 *@param priceslevel 实体的Priceslevel属性
			 */
			public void setPriceslevel(Integer priceslevel)
			{
				this.priceslevel = priceslevel;
			}
			
			/**
			 *获取"等级"属性
			 */
			public Integer getPriceslevel()
			{
				return this.priceslevel;
			}	   

			/**
			 *设置"单品价"属性
			 *@param pricesprice 实体的Pricesprice属性
			 */
			public void setPricesprice(Float pricesprice)
			{
				this.pricesprice = pricesprice;
			}
			
			/**
			 *获取"单品价"属性
			 */
			public Float getPricesprice()
			{
				return this.pricesprice;
			}	   

			/**
			 *设置"单品单位"属性
			 *@param pricesunit 实体的Pricesunit属性
			 */
			public void setPricesunit(String pricesunit)
			{
				this.pricesunit = pricesunit;
			}
			
			/**
			 *获取"单品单位"属性
			 */
			public String getPricesunit()
			{
				return this.pricesunit;
			}	   
			/**
			    * ID,主键
			    */
			   private String largecuspriceid; 
			   /**
			    * 供应商
			    */
			   private String largecuspricecompany;   
			   /**
			    * 客户
			    */
			   private String largecuspricecustomer;   
			   /**
			    * 商品
			    */
			   private String largecuspricegoods;   
			   /**
			    * 单品价
			    */
			   private String largecuspriceprice;   
			   /**
			    * 描述
			    */
			   private String largecuspricedetail;   
			   /**
			    * 创建时间
			    */
			   private String largecuspricecreatetime;   
			   /**
			    * 创建人
			    */
			   private String largecuspricecreator;   
			   /**
			    * 套装价
			    */
			   private String largecuspriceprice2;   
			   /**
			    * 单品价单位
			    */
			   private String largecuspriceunit;   
			   /**
			    * 套装价单位
			    */
			   private String largecuspriceunit2;   
			   /**
			    * 修改人
			    */
			   private String largecusupdtime;   
			   /**
			    * 修改时间
			    */
			   private String largecusupdor;   
			    //属性方法	    
			     /**
				 *设置主键"ID"属性
				 *@param largecuspriceid 实体的Largecuspriceid属性
				 */
				public void setLargecuspriceid(String largecuspriceid)
				{
					this.largecuspriceid = largecuspriceid;
				}
				
				/**
				 *获取主键"ID"属性
				 */
				public String getLargecuspriceid()
				{
					return this.largecuspriceid;
				}

				/**
				 *设置"供应商"属性
				 *@param largecuspricecompany 实体的Largecuspricecompany属性
				 */
				public void setLargecuspricecompany(String largecuspricecompany)
				{
					this.largecuspricecompany = largecuspricecompany;
				}
				
				/**
				 *获取"供应商"属性
				 */
				public String getLargecuspricecompany()
				{
					return this.largecuspricecompany;
				}	   

				/**
				 *设置"客户"属性
				 *@param largecuspricecustomer 实体的Largecuspricecustomer属性
				 */
				public void setLargecuspricecustomer(String largecuspricecustomer)
				{
					this.largecuspricecustomer = largecuspricecustomer;
				}
				
				/**
				 *获取"客户"属性
				 */
				public String getLargecuspricecustomer()
				{
					return this.largecuspricecustomer;
				}	   

				/**
				 *设置"商品"属性
				 *@param largecuspricegoods 实体的Largecuspricegoods属性
				 */
				public void setLargecuspricegoods(String largecuspricegoods)
				{
					this.largecuspricegoods = largecuspricegoods;
				}
				
				/**
				 *获取"商品"属性
				 */
				public String getLargecuspricegoods()
				{
					return this.largecuspricegoods;
				}	   

				/**
				 *设置"单品价"属性
				 *@param largecuspriceprice 实体的Largecuspriceprice属性
				 */
				public void setLargecuspriceprice(String largecuspriceprice)
				{
					this.largecuspriceprice = largecuspriceprice;
				}
				
				/**
				 *获取"单品价"属性
				 */
				public String getLargecuspriceprice()
				{
					return this.largecuspriceprice;
				}	   

				/**
				 *设置"描述"属性
				 *@param largecuspricedetail 实体的Largecuspricedetail属性
				 */
				public void setLargecuspricedetail(String largecuspricedetail)
				{
					this.largecuspricedetail = largecuspricedetail;
				}
				
				/**
				 *获取"描述"属性
				 */
				public String getLargecuspricedetail()
				{
					return this.largecuspricedetail;
				}	   

				/**
				 *设置"创建时间"属性
				 *@param largecuspricecreatetime 实体的Largecuspricecreatetime属性
				 */
				public void setLargecuspricecreatetime(String largecuspricecreatetime)
				{
					this.largecuspricecreatetime = largecuspricecreatetime;
				}
				
				/**
				 *获取"创建时间"属性
				 */
				public String getLargecuspricecreatetime()
				{
					return this.largecuspricecreatetime;
				}	   

				/**
				 *设置"创建人"属性
				 *@param largecuspricecreator 实体的Largecuspricecreator属性
				 */
				public void setLargecuspricecreator(String largecuspricecreator)
				{
					this.largecuspricecreator = largecuspricecreator;
				}
				
				/**
				 *获取"创建人"属性
				 */
				public String getLargecuspricecreator()
				{
					return this.largecuspricecreator;
				}	   

				/**
				 *设置"套装价"属性
				 *@param largecuspriceprice2 实体的Largecuspriceprice2属性
				 */
				public void setLargecuspriceprice2(String largecuspriceprice2)
				{
					this.largecuspriceprice2 = largecuspriceprice2;
				}
				
				/**
				 *获取"套装价"属性
				 */
				public String getLargecuspriceprice2()
				{
					return this.largecuspriceprice2;
				}	   

				/**
				 *设置"单品价单位"属性
				 *@param largecuspriceunit 实体的Largecuspriceunit属性
				 */
				public void setLargecuspriceunit(String largecuspriceunit)
				{
					this.largecuspriceunit = largecuspriceunit;
				}
				
				/**
				 *获取"单品价单位"属性
				 */
				public String getLargecuspriceunit()
				{
					return this.largecuspriceunit;
				}	   

				/**
				 *设置"套装价单位"属性
				 *@param largecuspriceunit2 实体的Largecuspriceunit2属性
				 */
				public void setLargecuspriceunit2(String largecuspriceunit2)
				{
					this.largecuspriceunit2 = largecuspriceunit2;
				}
				
				/**
				 *获取"套装价单位"属性
				 */
				public String getLargecuspriceunit2()
				{
					return this.largecuspriceunit2;
				}	   

				/**
				 *设置"修改人"属性
				 *@param largecusupdtime 实体的Largecusupdtime属性
				 */
				public void setLargecusupdtime(String largecusupdtime)
				{
					this.largecusupdtime = largecusupdtime;
				}
				
				/**
				 *获取"修改人"属性
				 */
				public String getLargecusupdtime()
				{
					return this.largecusupdtime;
				}	   

				/**
				 *设置"修改时间"属性
				 *@param largecusupdor 实体的Largecusupdor属性
				 */
				public void setLargecusupdor(String largecusupdor)
				{
					this.largecusupdor = largecusupdor;
				}
				
				/**
				 *获取"修改时间"属性
				 */
				public String getLargecusupdor()
				{
					return this.largecusupdor;
				}	   

				   /**
				    * 名称
				    */
				   private String goodsclassname;   

					/**
					 *设置"名称"属性
					 *@param goodsclassname 实体的Goodsclassname属性
					 */
					public void setGoodsclassname(String goodsclassname)
					{
						this.goodsclassname = goodsclassname;
					}
					
					/**
					 *获取"名称"属性
					 */
					public String getGoodsclassname()
					{
						return this.goodsclassname;
					}	   

}
