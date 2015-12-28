package com.system.poco;

/**
 * system_power_quickview 实体类的常量
 *@author ZhangRuiLong
 */
public class System_power_quickviewPoco
{
   /**
    * 实体中文名
    */
   public static String NAME = "system_power_quickview";
   /**
    * 实体表名
    */
   public static String TABLE = "System_power_quickview";
   /**
    * 实体主键
    */
   public static String[] KEYCOLUMN = {"id"};
   /**
    * 实体中文字段
    */
   public static String[] CHINESENAME = {
	 	"userid",
	 	"powerid",
	 	"code",
	 	"name",
	 	"detail",
	 	"parentname",
	 	"menulevel",
	 	"entrance",
	 	"hreftarget",
	};
	/**
	 * 实体英文字段
	 */
   public static final String[] FIELDNAME = {
	 	"userid",
	 	"powerid",
	 	"code",
	 	"name",
	 	"detail",
	 	"parentname",
	 	"menulevel",
	 	"entrance",
	 	"hreftarget",
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
	 	"code",
	 	"name",
	 	"detail",
	 	"parentname",
	 	"menulevel",
	 	"entrance",
	 	"hreftarget",
   };
}

