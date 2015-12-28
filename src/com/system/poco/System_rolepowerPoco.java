package com.system.poco;

/**
 * 角色权限 实体类的常量
 *@author ZhangRuiLong
 */
public class System_rolepowerPoco
{
   /**
    * 实体中文名
    */
   public static String NAME = "角色权限";
   /**
    * 实体表名
    */
   public static String TABLE = "System_rolepower";
   /**
    * 实体主键
    */
   public static String[] KEYCOLUMN = {"id"};
   /**
    * 实体中文字段
    */
   public static String[] CHINESENAME = {
	 	"ROLEID",
	 	"POWERID",
	};
	/**
	 * 实体英文字段
	 */
   public static final String[] FIELDNAME = {
	 	"roleid",
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
	 	"roleid",
	 	"powerid",
   };
}

