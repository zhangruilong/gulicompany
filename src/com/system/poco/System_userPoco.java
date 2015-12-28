package com.system.poco;

/**
 * 用户 实体类的常量
 *@author ZhangRuiLong
 */
public class System_userPoco
{
   /**
    * 实体中文名
    */
   public static String NAME = "用户";
   /**
    * 实体表名
    */
   public static String TABLE = "System_user";
   /**
    * 实体主键
    */
   public static String[] KEYCOLUMN = {"id"};
   /**
    * 实体中文字段
    */
   public static String[] CHINESENAME = {
	 	"登录名",
	 	"密码",
	 	"用户名",
	 	"状态",
	};
	/**
	 * 实体英文字段
	 */
   public static final String[] FIELDNAME = {
	 	"loginname",
	 	"password",
	 	"username",
	 	"statue",
   };
   /**
    * 实体排序
    */
   public static final String ORDER = " id desc ";
   /**
	 * 要模糊查询字段
	 */
   public static final String[] QUERYFIELDNAME = {
	 	"loginname",
	 	"password",
	 	"username",
	 	"statue",
   };
}

