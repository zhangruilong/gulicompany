package com.system.poco;

/**
 * 快捷菜单 实体类的常量
 *@author ZhangRuiLong
 */
public class System_powerPoco
{
   /**
    * 实体中文名
    */
   public static String NAME = "快捷菜单";
   /**
    * 实体表名
    */
   public static String TABLE = "System_power";
   /**
    * 实体主键
    */
   public static String[] KEYCOLUMN = {"id"};
   /**
    * 实体中文字段
    */
   public static String[] CHINESENAME = {
	 	"编码",
	 	"名称",
	 	"描述",
	 	"父节点",
	 	"类型",
	 	"入口",
	 	"菜单顺序",
	 	"图片",
	 	"打开方式",
	};
	/**
	 * 实体英文字段
	 */
   public static final String[] FIELDNAME = {
	 	"code",
	 	"name",
	 	"detail",
	 	"parentid",
	 	"menulevel",
	 	"entrance",
	 	"menuorder",
	 	"iconcls",
	 	"hreftarget",
   };
   /**
    * 实体排序
    */
   public static final String ORDER = " id desc ";
   /**
	 * 要模糊查询字段
	 */
   public static final String[] QUERYFIELDNAME = {
	 	"code",
	 	"name",
	 	"detail",
	 	"parentid",
	 	"menulevel",
	 	"entrance",
	 	"menuorder",
	 	"iconcls",
	 	"hreftarget",
   };
}

