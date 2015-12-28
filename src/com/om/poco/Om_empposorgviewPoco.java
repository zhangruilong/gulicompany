package com.om.poco;

/**
 * om_empposorgview 实体类的常量
 *@author ZhangRuiLong
 */
public class Om_empposorgviewPoco
{
   /**
    * 实体中文名
    */
   public static String NAME = "om_empposorgview";
   /**
    * 实体表名
    */
   public static String TABLE = "Om_empposorgview";
   /**
    * 实体主键
    */
   public static String[] KEYCOLUMN = {"posempid"};
   /**
    * 实体中文字段
    */
   public static String[] CHINESENAME = {
	 	"positionid",
	 	"empid",
	 	"ismain",
	 	"empcode",
	 	"empname",
	 	"gender",
	 	"empstatus",
	 	"orgname",
	 	"posiname",
	 	"loginname",
	};
	/**
	 * 实体英文字段
	 */
   public static final String[] FIELDNAME = {
	 	"positionid",
	 	"empid",
	 	"ismain",
	 	"empcode",
	 	"empname",
	 	"gender",
	 	"empstatus",
	 	"orgname",
	 	"posiname",
	 	"loginname",
   };
   /**
    * 实体排序
    */
   public static final String ORDER = " posempid desc ";
   /**
	 * 要模糊查询字段
	 */
   public static final String[] QUERYFIELDNAME = {
	 	"positionid",
	 	"empid",
	 	"ismain",
	 	"empcode",
	 	"empname",
	 	"gender",
	 	"empstatus",
	 	"orgname",
	 	"posiname",
	 	"loginname",
   };
}

