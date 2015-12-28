package com.om.poco;

/**
 * om_grouptreeview 实体类的常量
 *@author ZhangRuiLong
 */
public class Om_grouptreeviewPoco
{
   /**
    * 实体中文名
    */
   public static String NAME = "om_grouptreeview";
   /**
    * 实体表名
    */
   public static String TABLE = "Om_grouptreeview";
   /**
    * 实体主键
    */
   public static String[] KEYCOLUMN = {"id"};
   /**
    * 实体中文字段
    */
   public static String[] CHINESENAME = {
	 	"code",
	 	"name",
	 	"detail",
	 	"leaf",
	 	"parentid",
	};
	/**
	 * 实体英文字段
	 */
   public static final String[] FIELDNAME = {
	 	"code",
	 	"name",
	 	"detail",
	 	"leaf",
	 	"parentid",
   };
   /**
    * 实体排序
    */
   public static final String ORDER = " id desc ";
   /**
	 * 要模糊查询字段
	 */
   public static final String[] QUERYFIELDNAME = {
	 	"code",
	 	"name",
	 	"detail",
	 	"leaf",
	 	"parentid",
   };
}

