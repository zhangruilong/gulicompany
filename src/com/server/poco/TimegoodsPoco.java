package com.server.poco;

/**
 * 促销品 实体类的常量
 *@author ZhangRuiLong
 */
public class TimegoodsPoco
{
   /**
    * 实体中文名
    */
   public static String NAME = "促销品";
   /**
    * 实体表名
    */
   public static String TABLE = "Timegoods";
   /**
    * 实体主键
    */
   public static String[] KEYCOLUMN = {"timegoodsid"};
   /**
    * 实体中文字段
    */
   public static String[] CHINESENAME = {
	 	"经销商ID",
	 	"编码",
	 	"名称",
	 	"描述",
	 	"规格",
	 	"单位",
	 	"原价",
	 	"现价",
	 	"限量",
	 	"小类名称",
	 	"图片",
	 	"状态",
	 	"创建时间",
	 	"创建人",
	};
	/**
	 * 实体英文字段
	 */
   public static final String[] FIELDNAME = {
	 	"timegoodscompany",
	 	"timegoodscode",
	 	"timegoodsname",
	 	"timegoodsdetail",
	 	"timegoodsunits",
	 	"timegoodsunit",
	 	"timegoodsprice",
	 	"timegoodsorgprice",
	 	"timegoodsnum",
	 	"timegoodsclass",
	 	"timegoodsimage",
	 	"timegoodsstatue",
	 	"createtime",
	 	"creator",
   };
   /**
    * 实体排序
    */
   public static final String ORDER = " timegoodsid ";
   /**
	 * 要模糊查询字段
	 */
   public static final String[] QUERYFIELDNAME = {
	 	"timegoodscompany",
	 	"timegoodscode",
	 	"timegoodsname",
	 	"timegoodsdetail",
	 	"timegoodsunits",
	 	"timegoodsunit",
	 	"timegoodsprice",
	 	"timegoodsorgprice",
	 	"timegoodsnum",
	 	"timegoodsclass",
	 	"timegoodsimage",
	 	"timegoodsstatue",
	 	"createtime",
	 	"creator",
   };
}

