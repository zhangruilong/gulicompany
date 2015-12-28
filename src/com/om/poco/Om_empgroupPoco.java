package com.om.poco;

/**
 * 人员工作组对应关系 实体类的常量
 *@author ZhangRuiLong
 */
public class Om_empgroupPoco
{
   /**
    * 实体中文名
    */
   public static String NAME = "人员工作组对应关系";
   /**
    * 实体表名
    */
   public static String TABLE = "Om_empgroup";
   /**
    * 实体主键
    */
   public static String[] KEYCOLUMN = {"groempid"};
   /**
    * 实体中文字段
    */
   public static String[] CHINESENAME = {
	 	"工作组编号",
	 	"人员编号",
	 	"工作组职务",
	 	"备注",
	};
	/**
	 * 实体英文字段
	 */
   public static final String[] FIELDNAME = {
	 	"groupid",
	 	"empid",
	 	"groupduty",
	 	"remark",
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
   };
}

