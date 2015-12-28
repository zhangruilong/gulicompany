package com.system.poco;

/**
 * 角色人员 实体类的常量
 *@author ZhangRuiLong
 */
public class System_roleuserPoco
{
   /**
    * 实体中文名
    */
   public static String NAME = "角色人员";
   /**
    * 实体表名
    */
   public static String TABLE = "System_roleuser";
   /**
    * 实体主键
    */
   public static String[] KEYCOLUMN = {"id"};
   /**
    * 实体中文字段
    */
   public static String[] CHINESENAME = {
	 	"ROLEID",
	 	"USERID",
	};
	/**
	 * 实体英文字段
	 */
   public static final String[] FIELDNAME = {
	 	"roleid",
	 	"userid",
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
   };
}

