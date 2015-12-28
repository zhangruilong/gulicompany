package com.system.tools.pojo;

import java.sql.Date;
import java.util.ArrayList;

/**
 * 这个是实体类
 * 
 * @author ZhangRuiLong
 */
public class ExcelSheetinfo {
	// 属性定义
	// "模板代码"
	private String tempcode;
	// "模板名称"
	private String tempname;
	// "页签序号"
	private int xlssheetno;
	// "页签名称"
	private String xlssheetname;
	// "录入表代码"
	private String tablecode;
	// "表头开始行"
	private int headstartrow;
	// "表头结束行"
	private int headendrow;
	// "表头开始列"
	private int headstartcol;
	// "表头结束列"
	private int headendcol;
	// 表头信息
	private ArrayList<ExcelHeadinfo> excelHeadinfos;

	public ExcelSheetinfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTempcode() {
		return tempcode;
	}

	public void setTempcode(String tempcode) {
		this.tempcode = tempcode;
	}

	public String getTempname() {
		return tempname;
	}

	public void setTempname(String tempname) {
		this.tempname = tempname;
	}

	public int getXlssheetno() {
		return xlssheetno;
	}

	public void setXlssheetno(int xlssheetno) {
		this.xlssheetno = xlssheetno;
	}

	public String getXlssheetname() {
		return xlssheetname;
	}

	public void setXlssheetname(String xlssheetname) {
		this.xlssheetname = xlssheetname;
	}

	public String getTablecode() {
		return tablecode;
	}

	public void setTablecode(String tablecode) {
		this.tablecode = tablecode;
	}

	public int getHeadstartrow() {
		return headstartrow;
	}

	public void setHeadstartrow(int headstartrow) {
		this.headstartrow = headstartrow;
	}

	public int getHeadendrow() {
		return headendrow;
	}

	public void setHeadendrow(int headendrow) {
		this.headendrow = headendrow;
	}

	public int getHeadstartcol() {
		return headstartcol;
	}

	public void setHeadstartcol(int headstartcol) {
		this.headstartcol = headstartcol;
	}

	public int getHeadendcol() {
		return headendcol;
	}

	public void setHeadendcol(int headendcol) {
		this.headendcol = headendcol;
	}

	public ArrayList<ExcelHeadinfo> getExcelHeadinfos() {
		return excelHeadinfos;
	}

	public void setExcelHeadinfos(ArrayList<ExcelHeadinfo> excelHeadinfos) {
		this.excelHeadinfos = excelHeadinfos;
	}

	public ExcelSheetinfo(String tempcode, String tempname, int xlssheetno,
			String xlssheetname, String tablecode, int headstartrow,
			int headendrow, int headstartcol, int headendcol,
			ArrayList<ExcelHeadinfo> excelHeadinfos) {
		super();
		this.tempcode = tempcode;
		this.tempname = tempname;
		this.xlssheetno = xlssheetno;
		this.xlssheetname = xlssheetname;
		this.tablecode = tablecode;
		this.headstartrow = headstartrow;
		this.headendrow = headendrow;
		this.headstartcol = headstartcol;
		this.headendcol = headendcol;
		this.excelHeadinfos = excelHeadinfos;
	}

}
