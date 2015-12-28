package com.system.poco;

/**
 * system_powerview 实体类的常量
 *@author ZhangRuiLong
 */
public class System_powerviewPoco
{
   /**
    * 实体中文名
    */
   public static String NAME = "system_powerview";
   /**
    * 实体表名
    */
   public static String TABLE = "System_powerview";
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
	 	"parentid",
	 	"menulevel",
	 	"entrance",
	 	"menuorder",
	 	"iconcls",
	 	"hreftarget",
	 	"parentname",
	};
	/**
	 * 实体英文字段
	 */
   public static final String[] FIELDNAME = {
	 	"code",
	 	"name",
	 	"detail",
	 	"parentid",
	 	"menulevel",
	 	"entrance",
	 	"menuorder",
	 	"iconcls",
	 	"hreftarget",
	 	"parentname",
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
	 	"parentid",
	 	"menulevel",
	 	"entrance",
	 	"menuorder",
	 	"iconcls",
	 	"hreftarget",
	 	"parentname",
   };
}

