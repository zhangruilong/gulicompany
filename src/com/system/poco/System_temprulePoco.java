package com.system.poco;

/**
 * 模板样式 实体类的常量
 *@author ZhangRuiLong
 */
public class System_temprulePoco
{
   /**
    * 实体中文名
    */
   public static String NAME = "模板样式";
   /**
    * 实体表名
    */
   public static String TABLE = "System_temprule";
   /**
    * 实体主键
    */
   public static String[] KEYCOLUMN = {"id"};
   /**
    * 实体中文字段
    */
   public static String[] CHINESENAME = {
	 	"模板代码",
	 	"模板名称",
	 	"页签序号",
	 	"页签名称",
	 	"录入表代码",
	 	"表头序号",
	 	"表头代码",
	 	"表头名称",
	 	"表头别名",
	 	"表头对应录入表字段名",
	 	"表头级次",
	 	"表头开始行",
	 	"表头结束行",
	 	"表头开始列",
	 	"表头结束列",
	 	"备注",
	};
	/**
	 * 实体英文字段
	 */
   public static final String[] FIELDNAME = {
	 	"tempcode",
	 	"tempname",
	 	"sheetno",
	 	"sheetname",
	 	"tablecode",
	 	"headno",
	 	"headcode",
	 	"headname",
	 	"headnameas",
	 	"fieldname",
	 	"headlevel",
	 	"startrow",
	 	"endrow",
	 	"startcol",
	 	"endcol",
	 	"detail",
   };
   /**
    * 实体排序
    */
   public static final String ORDER = " id desc ";
   /**
	 * 要模糊查询字段
	 */
   public static final String[] QUERYFIELDNAME = {
	 	"tempcode",
	 	"tempname",
	 	"sheetno",
	 	"sheetname",
	 	"tablecode",
	 	"headno",
	 	"headcode",
	 	"headname",
	 	"headnameas",
	 	"fieldname",
	 	"headlevel",
	 	"startrow",
	 	"endrow",
	 	"startcol",
	 	"endcol",
	 	"detail",
   };
}

