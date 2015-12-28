package com.om.poco;

/**
 * 机构信息表 实体类的常量
 *@author ZhangRuiLong
 */
public class Om_organizationPoco
{
   /**
    * 实体中文名
    */
   public static String NAME = "机构信息表";
   /**
    * 实体表名
    */
   public static String TABLE = "Om_organization";
   /**
    * 实体主键
    */
   public static String[] KEYCOLUMN = {"orgid"};
   /**
    * 实体中文字段
    */
   public static String[] CHINESENAME = {
	 	"机构代码",
	 	"机构名称",
	 	"机构层次",
	 	"机构等级",
	 	"父机构编号",
	 	"机构序列",
	 	"机构类型",
	 	"机构地址",
	 	"邮编",
	 	"机构主管岗位",
	 	"机构主管人员",
	 	"机构管理员",
	 	"联系人",
	 	"联系电话",
	 	"电子邮件",
	 	"网站地址",
	 	"生效日期",
	 	"失效日期",
	 	"机构状态",
	 	"所属地域",
	 	"创建时间",
	 	"最近更新时间",
	 	"最近更新人员",
	 	"排列顺序编号",
	 	"是否叶子节点",
	 	"子节点数",
	 	"备注",
	};
	/**
	 * 实体英文字段
	 */
   public static final String[] FIELDNAME = {
	 	"orgcode",
	 	"orgname",
	 	"orglevel",
	 	"orgdegree",
	 	"parentorgid",
	 	"orgseq",
	 	"orgtype",
	 	"orgaddr",
	 	"zipcode",
	 	"manaposition",
	 	"managerid",
	 	"orgmanager",
	 	"linkman",
	 	"linktel",
	 	"email",
	 	"weburl",
	 	"startdate",
	 	"enddate",
	 	"status",
	 	"area",
	 	"createtime",
	 	"lastupdate",
	 	"updator",
	 	"sortno",
	 	"isleaf",
	 	"subcount",
	 	"remark",
   };
   /**
    * 实体排序
    */
   public static final String ORDER = " orgid desc ";
   /**
	 * 要模糊查询字段
	 */
   public static final String[] QUERYFIELDNAME = {
	 	"orgcode",
	 	"orgname",
	 	"orglevel",
	 	"orgdegree",
	 	"parentorgid",
	 	"orgseq",
	 	"orgtype",
	 	"orgaddr",
	 	"zipcode",
	 	"manaposition",
	 	"managerid",
	 	"orgmanager",
	 	"linkman",
	 	"linktel",
	 	"email",
	 	"weburl",
	 	"startdate",
	 	"enddate",
	 	"status",
	 	"area",
	 	"createtime",
	 	"lastupdate",
	 	"updator",
	 	"sortno",
	 	"isleaf",
	 	"subcount",
	 	"remark",
   };
}

