package com.system.tools.pojo;

import java.util.ArrayList;


public class Treeinfo {
	 private String id;
	 private String code;
	 private String text;
	 private String qtip;
	 private String icon;
	 private String href;
	 private String hrefTarget;
	 private String leaf;
	 private String parent;
	 private Boolean checked;
	 private ArrayList<Treeinfo> children;
	 public Treeinfo() {
	 }
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getQtip() {
		return qtip;
	}
	public void setQtip(String qtip) {
		this.qtip = qtip;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getHrefTarget() {
		return hrefTarget;
	}
	public void setHrefTarget(String hrefTarget) {
		this.hrefTarget = hrefTarget;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLeaf() {
		return leaf;
	}
	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public Treeinfo(String id, String code, String text, String qtip,
			String icon, String href, String hrefTarget, String leaf,
			String parent) {
		super();
		this.id = id;
		this.code = code;
		this.text = text;
		this.qtip = qtip;
		this.icon = icon;
		this.href = href;
		this.hrefTarget = hrefTarget;
		this.leaf = leaf;
		this.parent = parent;
	}
	public ArrayList<Treeinfo> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<Treeinfo> children) {
		this.children = children;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}


}
	