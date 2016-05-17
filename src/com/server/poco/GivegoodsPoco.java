package com.server.poco;

/**
 * 买赠 实体类的常量
 *@author ZhangRuiLong
 */
public class GivegoodsPoco
{
   /**
    * 实体中文名
    */
   public static String NAME = "买赠";
   /**
    * 实体表名
    */
   public static String TABLE = "Givegoods";
   /**
    * 实体主键
    */
   public static String[] KEYCOLUMN = {"givegoodsid"};
   /**
    * 实体中文字段
    */
   public static String[] CHINESENAME = {
   		"促销品ID",
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
	 	"顺序",
	 	"客户范围",
	};
	/**
	 * 实体英文字段
	 */
   public static final String[] FIELDNAME = {
   		"givegoodsid",
	 	"givegoodscompany",
	 	"givegoodscode",
	 	"givegoodsname",
	 	"givegoodsdetail",
	 	"givegoodsunits",
	 	"givegoodsunit",
	 	"givegoodsprice",
	 	"givegoodsorgprice",
	 	"givegoodsnum",
	 	"givegoodsclass",
	 	"givegoodsimage",
	 	"givegoodsstatue",
	 	"createtime",
	 	"creator",
	 	"givegoodsseq",
	 	"givegoodsscope",
   };
   /**
    * 实体排序
    */
   public static final String ORDER = " givegoodsid desc ";
   /**
	 * 要模糊查询字段
	 */
   public static final String[] QUERYFIELDNAME = {
	 	"givegoodscompany",
	 	"givegoodscode",
	 	"givegoodsname",
	 	"givegoodsdetail",
	 	"givegoodsunits",
	 	"givegoodsunit",
	 	"givegoodsprice",
	 	"givegoodsorgprice",
	 	"givegoodsnum",
	 	"givegoodsclass",
	 	"givegoodsimage",
	 	"givegoodsstatue",
	 	"createtime",
	 	"creator",
	 	"givegoodsseq",
	 	"givegoodsscope",
   };
}

