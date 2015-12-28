package com.om.poco;

/**
 * 职务定义表 实体类的常量
 *@author ZhangRuiLong
 */
public class Om_dutyPoco
{
   /**
    * 实体中文名
    */
   public static String NAME = "职务定义表";
   /**
    * 实体表名
    */
   public static String TABLE = "Om_duty";
   /**
    * 实体主键
    */
   public static String[] KEYCOLUMN = {"dutyid"};
   /**
    * 实体中文字段
    */
   public static String[] CHINESENAME = {
	 	"职务代码",
	 	"职务名称",
	 	"上级职务编号",
	 	"职务层次",
	 	"职务序列号",
	 	"职务套别",
	 	"是否叶子节点",
	 	"子节点数",
	 	"备注",
	};
	/**
	 * 实体英文字段
	 */
   public static final String[] FIELDNAME = {
	 	"dutycode",
	 	"dutyname",
	 	"parentduty",
	 	"dutylevel",
	 	"dutyseq",
	 	"dutytype",
	 	"isleaf",
	 	"subcount",
	 	"remark",
   };
   /**
    * 实体排序
    */
   public static final String ORDER = " dutyid desc ";
   /**
	 * 要模糊查询字段
	 */
   public static final String[] QUERYFIELDNAME = {
	 	"dutycode",
	 	"dutyname",
	 	"parentduty",
	 	"dutylevel",
	 	"dutyseq",
	 	"dutytype",
	 	"isleaf",
	 	"subcount",
	 	"remark",
   };
}

