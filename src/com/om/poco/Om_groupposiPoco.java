package com.om.poco;

/**
 * 工作组岗位列表 实体类的常量
 *@author ZhangRuiLong
 */
public class Om_groupposiPoco
{
   /**
    * 实体中文名
    */
   public static String NAME = "工作组岗位列表";
   /**
    * 实体表名
    */
   public static String TABLE = "Om_groupposi";
   /**
    * 实体主键
    */
   public static String[] KEYCOLUMN = {"groposid"};
   /**
    * 实体中文字段
    */
   public static String[] CHINESENAME = {
	 	"工作组编号",
	 	"岗位编号",
	};
	/**
	 * 实体英文字段
	 */
   public static final String[] FIELDNAME = {
	 	"groupid",
	 	"positionid",
   };
   /**
    * 实体排序
    */
   public static final String ORDER = " groposid desc ";
   /**
	 * 要模糊查询字段
	 */
   public static final String[] QUERYFIELDNAME = {
	 	"groupid",
	 	"positionid",
   };
}

