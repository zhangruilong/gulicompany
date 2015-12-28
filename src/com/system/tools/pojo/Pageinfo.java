package com.system.tools.pojo;

import java.util.List;

public class Pageinfo {
	private boolean success;
	private String msg;
	private int total;
	private List root;

	public Pageinfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List getRoot() {
		return root;
	}

	public void setRoot(List root) {
		this.root = root;
	}

	public Pageinfo(int total, List root) {
		super();
		this.success = true;
		this.msg = "操作成功";
		this.total = total;
		this.root = root;
	}
	public Pageinfo(List root) {
		super();
		this.success = true;
		this.msg = "操作成功";
		this.total = root.size();
		this.root = root;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
