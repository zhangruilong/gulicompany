package com.om.poco;

/**
 * 岗位/职位 实体类的常量
 *@author ZhangRuiLong
 */
public class Om_positionPoco
{
   /**
    * 实体中文名
    */
   public static String NAME = "岗位/职位";
   /**
    * 实体表名
    */
   public static String TABLE = "Om_position";
   /**
    * 实体主键
    */
   public static String[] KEYCOLUMN = {"positionid"};
   /**
    * 实体中文字段
    */
   public static String[] CHINESENAME = {
	 	"岗位代码",
	 	"岗位名称",
	 	"岗位层次",
	 	"上级岗位",
	 	"职务编号",
	 	"机构编号",
	 	"岗位序列",
	 	"岗位类别",
	 	"创建时间",
	 	"最近更新时间",
	 	"最近更新人员",
	 	"岗位有效开始日期",
	 	"岗位有效截止日期",
	 	"岗位状态",
	 	"是否叶子岗位",
	 	"子节点数",
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
   };
}

