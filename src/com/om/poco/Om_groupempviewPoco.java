package com.om.poco;

/**
 * om_groupempview 实体类的常量
 *@author ZhangRuiLong
 */
public class Om_groupempviewPoco
{
   /**
    * 实体中文名
    */
   public static String NAME = "om_groupempview";
   /**
    * 实体表名
    */
   public static String TABLE = "Om_groupempview";
   /**
    * 实体主键
    */
   public static String[] KEYCOLUMN = {"groempid"};
   /**
    * 实体中文字段
    */
   public static String[] CHINESENAME = {
	 	"groupid",
	 	"empid",
	 	"groupduty",
	 	"remark",
	 	"empcode",
	 	"empname",
	 	"gender",
	 	"empstatus",
	};
	/**
	 * 实体英文字段
	 */
   public static final String[] FIELDNAME = {
	 	"groupid",
	 	"empid",
	 	"groupduty",
	 	"remark",
	 	"empcode",
	 	"empname",
	 	"gender",
	 	"empstatus",
   };
   /**
    * 实体排序
    */
   public static final String ORDER = " groempid desc ";
   /**
	 * 要模糊查询字段
	 */
   public static final String[] QUERYFIELDNAME = {
	 	"groupid",
	 	"empid",
	 	"groupduty",
	 	"remark",
	 	"empcode",
	 	"empname",
	 	"gender",
	 	"empstatus",
   };
}

