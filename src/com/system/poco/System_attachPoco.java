package com.system.poco;

/**
 * 附件 实体类的常量
 *@author ZhangRuiLong
 */
public class System_attachPoco
{
   /**
    * 实体中文名
    */
   public static String NAME = "附件";
   /**
    * 实体表名
    */
   public static String TABLE = "System_attach";
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
	 	"分类",
	 	"类型",
	 	"大小",
	 	"外键",
	 	"创建人员",
	 	"创建时间",
	};
	/**
	 * 实体英文字段
	 */
   public static final String[] FIELDNAME = {
	 	"code",
	 	"name",
	 	"detail",
	 	"classify",
	 	"type",
	 	"attachsize",
	 	"fid",
	 	"creator",
	 	"createtime",
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
	 	"classify",
	 	"type",
	 	"attachsize",
	 	"fid",
	 	"creator",
	 	"createtime",
   };
}

