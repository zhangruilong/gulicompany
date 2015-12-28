package com.system.tools.pojo;

import java.sql.Date;

/**
 * 这个是实体类
 * 
 * @author ZhangRuiLong
 */
public class ExcelHeadinfo {
	// 主键,自增，主要不需要对其进行赋值!
	private String id;
	// 属性定义
	// "表头序号"
	private int tableheadcolno;
	// "表头代码"
	private String tableheadcode;
	// "表头名称"
	private String tableheadname;
	// "表头别名"
	private String tableheadnameas;
	// "表头对应录入表字段名"
	private String tableheadfieldname;
	// "表头级次"
	private int headleve;
	// "备注"
	private String memotxt;

	public ExcelHeadinfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getTableheadcolno() {
		return tableheadcolno;
	}

	public void setTableheadcolno(int tableheadcolno) {
		this.tableheadcolno = tableheadcolno;
	}

	public String getTableheadcode() {
		return tableheadcode;
	}

	public void setTableheadcode(String tableheadcode) {
		this.tableheadcode = tableheadcode;
	}

	public String getTableheadname() {
		return tableheadname;
	}

	public void setTableheadname(String tableheadname) {
		this.tableheadname = tableheadname;
	}

	public String getTableheadnameas() {
		return tableheadnameas;
	}

	public void setTableheadnameas(String tableheadnameas) {
		this.tableheadnameas = tableheadnameas;
	}

	public String getTableheadfieldname() {
		return tableheadfieldname;
	}

	public void setTableheadfieldname(String tableheadfieldname) {
		this.tableheadfieldname = tableheadfieldname;
	}

	public int getHeadleve() {
		return headleve;
	}

	public void setHeadleve(int headleve) {
		this.headleve = headleve;
	}

	public String getMemotxt() {
		return memotxt;
	}

	public void setMemotxt(String memotxt) {
		this.memotxt = memotxt;
	}

	public ExcelHeadinfo(String id, int tableheadcolno, String tableheadcode,
			String tableheadname, String tableheadnameas,
			String tableheadfieldname, int headleve, String memotxt) {
		super();
		this.id = id;
		this.tableheadcolno = tableheadcolno;
		this.tableheadcode = tableheadcode;
		this.tableheadname = tableheadname;
		this.tableheadnameas = tableheadnameas;
		this.tableheadfieldname = tableheadfieldname;
		this.headleve = headleve;
		this.memotxt = memotxt;
	}

}
