package com.system.tools.pojo;


public class Fileinfo {
	 private String fullname;
	 private String name;
	 private String type;
	 private String path;
	 private long size;
	 public Fileinfo() {
	 }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Fileinfo(String fullname, String name, String type, String path,
			long size) {
		super();
		this.fullname = fullname;
		this.name = name;
		this.type = type;
		this.path = path;
		this.size = size;
	}


}
	