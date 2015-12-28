package com.system.poco;

/**
 * 快捷菜单 实体类的常量
 *@author ZhangRuiLong
 */
public class System_power_quickPoco
{
   /**
    * 实体中文名
    */
   public static String NAME = "快捷菜单";
   /**
    * 实体表名
    */
   public static String TABLE = "System_power_quick";
   /**
    * 实体主键
    */
   public static String[] KEYCOLUMN = {"id"};
   /**
    * 实体中文字段
    */
   public static String[] CHINESENAME = {
	 	"USERID",
	 	"POWERID",
	};
	/**
	 * 实体英文字段
	 */
   public static final String[] FIELDNAME = {
	 	"userid",
	 	"powerid",
   };
   /**
    * 实体排序
    */
   public static final String ORDER = " id desc ";
   /**
	 * 要模糊查询字段
	 */
   public static final String[] QUERYFIELDNAME = {
	 	"userid",
	 	"powerid",
   };
}

