package com.system.poco;

/**
 * 数据字典 实体类的常量
 *@author ZhangRuiLong
 */
public class System_tablecolPoco
{
   /**
    * 实体中文名
    */
   public static String NAME = "数据字典";
   /**
    * 实体表名
    */
   public static String TABLE = "System_tablecol";
   /**
    * 实体主键
    */
   public static String[] KEYCOLUMN = {"id"};
   /**
    * 实体中文字段
    */
   public static String[] CHINESENAME = {
	 	"录入表代码",
	 	"录入表名称",
	 	"字段序号",
	 	"字段列名",
	 	"字段中文名",
	 	"数据类型",
	 	"字段长度",
	 	"字段默认值",
	};
	/**
	 * 实体英文字段
	 */
   public static final String[] FIELDNAME = {
	 	"tablecode",
	 	"tablename",
	 	"colno",
	 	"colcode",
	 	"colname",
	 	"coltype",
	 	"collength",
	 	"coldefault",
   };
   /**
    * 实体排序
    */
   public static final String ORDER = " id desc ";
   /**
	 * 要模糊查询字段
	 */
   public static final String[] QUERYFIELDNAME = {
	 	"tablecode",
	 	"tablename",
	 	"colno",
	 	"colcode",
	 	"colname",
	 	"coltype",
	 	"collength",
	 	"coldefault",
   };
}

