package com.om.poco;

/**
 * 业务机构 实体类的常量
 *@author ZhangRuiLong
 */
public class Om_busiorgPoco
{
   /**
    * 实体中文名
    */
   public static String NAME = "业务机构";
   /**
    * 实体表名
    */
   public static String TABLE = "Om_busiorg";
   /**
    * 实体主键
    */
   public static String[] KEYCOLUMN = {"busidomain"};
   /**
    * 实体中文字段
    */
   public static String[] CHINESENAME = {
	 	"业务机构编号",
	 	"业务机构名称",
	 	"上级业务机构",
	 	"机构编号",
	 	"业务机构层次",
	 	"节点类型",
	 	"机构代号",
	 	"序列号",
	 	"主管岗位",
	 	"排列顺序编号",
	 	"是否叶子节点",
	 	"子节点数",
	};
	/**
	 * 实体英文字段
	 */
   public static final String[] FIELDNAME = {
	 	"busiorgid",
	 	"orgname",
	 	"parentid",
	 	"orgid",
	 	"orglevel",
	 	"nodetype",
	 	"orgcode",
	 	"seqno",
	 	"manapos",
	 	"sortno",
	 	"isleaf",
	 	"subcount",
   };
   /**
    * 实体排序
    */
   public static final String ORDER = " busidomain desc ";
   /**
	 * 要模糊查询字段
	 */
   public static final String[] QUERYFIELDNAME = {
	 	"busiorgid",
	 	"orgname",
	 	"parentid",
	 	"orgid",
	 	"orglevel",
	 	"nodetype",
	 	"orgcode",
	 	"seqno",
	 	"manapos",
	 	"sortno",
	 	"isleaf",
	 	"subcount",
   };
}

