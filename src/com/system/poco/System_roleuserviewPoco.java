package com.system.poco;

/**
 * system_roleuserview 实体类的常量
 *@author ZhangRuiLong
 */
public class System_roleuserviewPoco
{
   /**
    * 实体中文名
    */
   public static String NAME = "system_roleuserview";
   /**
    * 实体表名
    */
   public static String TABLE = "System_roleuserview";
   /**
    * 实体主键
    */
   public static String[] KEYCOLUMN = {"id"};
   /**
    * 实体中文字段
    */
   public static String[] CHINESENAME = {
	 	"roleid",
	 	"userid",
	 	"type",
	 	"rolecode",
	 	"rolename",
	 	"roledetail",
	 	"fcode",
	 	"fname",
	 	"fdetail",
	};
	/**
	 * 实体英文字段
	 */
   public static final String[] FIELDNAME = {
	 	"roleid",
	 	"userid",
	 	"type",
	 	"rolecode",
	 	"rolename",
	 	"roledetail",
	 	"fcode",
	 	"fname",
	 	"fdetail",
   };
   /**
    * 实体排序
    */
   public static final String ORDER = " id desc ";
   /**
	 * 要模糊查询字段
	 */
   public static final String[] QUERYFIELDNAME = {
	 	"roleid",
	 	"userid",
	 	"type",
	 	"rolecode",
	 	"rolename",
	 	"roledetail",
	 	"fcode",
	 	"fname",
	 	"fdetail",
   };
}

