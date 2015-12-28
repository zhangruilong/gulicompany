package com.om.poco;

/**
 * om_posiorgview 实体类的常量
 *@author ZhangRuiLong
 */
public class Om_posiorgviewPoco
{
   /**
    * 实体中文名
    */
   public static String NAME = "om_posiorgview";
   /**
    * 实体表名
    */
   public static String TABLE = "Om_posiorgview";
   /**
    * 实体主键
    */
   public static String[] KEYCOLUMN = {"positionid"};
   /**
    * 实体中文字段
    */
   public static String[] CHINESENAME = {
	 	"posicode",
	 	"posiname",
	 	"posilevel",
	 	"manaposi",
	 	"dutyid",
	 	"orgid",
	 	"positionseq",
	 	"positype",
	 	"createtime",
	 	"lastupdate",
	 	"updator",
	 	"startdate",
	 	"enddate",
	 	"status",
	 	"isleaf",
	 	"subcount",
	 	"orgname",
	};
	/**
	 * 实体英文字段
	 */
   public static final String[] FIELDNAME = {
	 	"posicode",
	 	"posiname",
	 	"posilevel",
	 	"manaposi",
	 	"dutyid",
	 	"orgid",
	 	"positionseq",
	 	"positype",
	 	"createtime",
	 	"lastupdate",
	 	"updator",
	 	"startdate",
	 	"enddate",
	 	"status",
	 	"isleaf",
	 	"subcount",
	 	"orgname",
   };
   /**
    * 实体排序
    */
   public static final String ORDER = " positionid desc ";
   /**
	 * 要模糊查询字段
	 */
   public static final String[] QUERYFIELDNAME = {
	 	"posicode",
	 	"posiname",
	 	"posilevel",
	 	"manaposi",
	 	"dutyid",
	 	"orgid",
	 	"positionseq",
	 	"positype",
	 	"createtime",
	 	"lastupdate",
	 	"updator",
	 	"startdate",
	 	"enddate",
	 	"status",
	 	"isleaf",
	 	"subcount",
	 	"orgname",
   };
}

