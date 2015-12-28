package com.system.poco;

/**
 * system_menuview 实体类的常量
 *@author ZhangRuiLong
 */
public class System_menuviewPoco
{
   /**
    * 实体中文名
    */
   public static String NAME = "system_menuview";
   /**
    * 实体表名
    */
   public static String TABLE = "System_menuview";
   /**
    * 实体主键
    */
   public static String[] KEYCOLUMN = {"userid"};
   /**
    * 实体中文字段
    */
   public static String[] CHINESENAME = {
	 	"username",
	 	"id",
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
	 	"username",
	 	"id",
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
   public static final String ORDER = " userid desc ";
   /**
	 * 要模糊查询字段
	 */
   public static final String[] QUERYFIELDNAME = {
	 	"username",
	 	"id",
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

