package com.om.poco;

/**
 * 工作组 实体类的常量
 *@author ZhangRuiLong
 */
public class Om_groupPoco
{
   /**
    * 实体中文名
    */
   public static String NAME = "工作组";
   /**
    * 实体表名
    */
   public static String TABLE = "Om_group";
   /**
    * 实体主键
    */
   public static String[] KEYCOLUMN = {"groupid"};
   /**
    * 实体中文字段
    */
   public static String[] CHINESENAME = {
	 	"工作组代码",
	 	"工作组名称",
	 	"工作组层次",
	 	"工作组描述",
	 	"工作组类型",
	 	"工作组路径序列",
	 	"工作组有效开始日期",
	 	"工作组有效截止日期",
	 	"工作组状态",
	 	"负责人",
	 	"隶属机构",
	 	"父工作组编号",
	 	"创建时间",
	 	"最近更新时间",
	 	"最近更新人员",
	 	"是否叶子节点",
	 	"子节点数",
	};
	/**
	 * 实体英文字段
	 */
   public static final String[] FIELDNAME = {
	 	"groupcode",
	 	"groupname",
	 	"grouplevel",
	 	"groupdesc",
	 	"grouptype",
	 	"groupseq",
	 	"startdate",
	 	"enddate",
	 	"groupstatus",
	 	"manager",
	 	"orgid",
	 	"parentgroupid",
	 	"createtime",
	 	"lastupdate",
	 	"updator",
	 	"isleaf",
	 	"subcount",
   };
   /**
    * 实体排序
    */
   public static final String ORDER = " groupid desc ";
   /**
	 * 要模糊查询字段
	 */
   public static final String[] QUERYFIELDNAME = {
	 	"groupcode",
	 	"groupname",
	 	"grouplevel",
	 	"groupdesc",
	 	"grouptype",
	 	"groupseq",
	 	"startdate",
	 	"enddate",
	 	"groupstatus",
	 	"manager",
	 	"orgid",
	 	"parentgroupid",
	 	"createtime",
	 	"lastupdate",
	 	"updator",
	 	"isleaf",
	 	"subcount",
   };
}

