package com.om.poco;

/**
 * 人员机构关系表 实体类的常量
 *@author ZhangRuiLong
 */
public class Om_emporgPoco
{
   /**
    * 实体中文名
    */
   public static String NAME = "人员机构关系表";
   /**
    * 实体表名
    */
   public static String TABLE = "Om_emporg";
   /**
    * 实体主键
    */
   public static String[] KEYCOLUMN = {"orgempid"};
   /**
    * 实体中文字段
    */
   public static String[] CHINESENAME = {
	 	"机构编号",
	 	"人员编号",
	 	"是否主机构",
	};
	/**
	 * 实体英文字段
	 */
   public static final String[] FIELDNAME = {
	 	"orgid",
	 	"empid",
	 	"ismain",
   };
   /**
    * 实体排序
    */
   public static final String ORDER = " orgempid desc ";
   /**
	 * 要模糊查询字段
	 */
   public static final String[] QUERYFIELDNAME = {
	 	"orgid",
	 	"empid",
	 	"ismain",
   };
}

