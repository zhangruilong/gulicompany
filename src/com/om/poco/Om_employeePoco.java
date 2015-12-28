package com.om.poco;

/**
 * 人员 实体类的常量
 *@author ZhangRuiLong
 */
public class Om_employeePoco
{
   /**
    * 实体中文名
    */
   public static String NAME = "人员";
   /**
    * 实体表名
    */
   public static String TABLE = "Om_employee";
   /**
    * 实体主键
    */
   public static String[] KEYCOLUMN = {"empid"};
   /**
    * 实体中文字段
    */
   public static String[] CHINESENAME = {
	 	"人员代码",
	 	"操作员编号",
	 	"操作员登录号",
	 	"人员姓名",
	 	"人员全名",
	 	"性别",
	 	"出生日期",
	 	"基本岗位",
	 	"状态",
	 	"证件类型",
	 	"证件号码",
	 	"入职日期",
	 	"离职日期",
	 	"办公电话",
	 	"办公地址",
	 	"办公邮编",
	 	"办公邮件",
	 	"传真号码",
	 	"手机号码",
	 	"MSN号码",
	 	"家庭电话",
	 	"家庭地址",
	 	"家庭邮编",
	 	"私人电子邮箱",
	 	"政治面貌",
	 	"职级",
	 	"直接主管",
	 	"可授权角色",
	 	"工作描述",
	 	"注册日期",
	 	"创建时间",
	 	"最新更新时间",
	 	"可管理机构",
	 	"主机构编号",
	 	"备注",
	};
	/**
	 * 实体英文字段
	 */
   public static final String[] FIELDNAME = {
	 	"empcode",
	 	"operatorid",
	 	"loginname",
	 	"empname",
	 	"realname",
	 	"gender",
	 	"birthdate",
	 	"position",
	 	"empstatus",
	 	"cardtype",
	 	"cardno",
	 	"indate",
	 	"outdate",
	 	"otel",
	 	"oaddress",
	 	"ozipcode",
	 	"oemail",
	 	"faxno",
	 	"mobileno",
	 	"msn",
	 	"htel",
	 	"haddress",
	 	"hzipcode",
	 	"pemail",
	 	"party",
	 	"degree",
	 	"major",
	 	"specialty",
	 	"workexp",
	 	"regdate",
	 	"createtime",
	 	"lastmodytime",
	 	"orgidlist",
	 	"orgid",
	 	"remark",
   };
   /**
    * 实体排序
    */
   public static final String ORDER = " empid desc ";
   /**
	 * 要模糊查询字段
	 */
   public static final String[] QUERYFIELDNAME = {
	 	"empcode",
	 	"operatorid",
	 	"loginname",
	 	"empname",
	 	"realname",
	 	"gender",
	 	"birthdate",
	 	"position",
	 	"empstatus",
	 	"cardtype",
	 	"cardno",
	 	"indate",
	 	"outdate",
	 	"otel",
	 	"oaddress",
	 	"ozipcode",
	 	"oemail",
	 	"faxno",
	 	"mobileno",
	 	"msn",
	 	"htel",
	 	"haddress",
	 	"hzipcode",
	 	"pemail",
	 	"party",
	 	"degree",
	 	"major",
	 	"specialty",
	 	"workexp",
	 	"regdate",
	 	"createtime",
	 	"lastmodytime",
	 	"orgidlist",
	 	"orgid",
	 	"remark",
   };
}

