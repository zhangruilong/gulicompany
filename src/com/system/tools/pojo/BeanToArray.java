package com.system.tools.pojo;

import java.util.List;

public class BeanToArray {

	 private List beanNames;
	 private List values;

	 public BeanToArray() {
	 }

	public List getBeanNames() {
		return beanNames;
	}

	public void setBeanNames(List beanNames) {
		this.beanNames = beanNames;
	}

	public List getValues() {
		return values;
	}

	public void setValues(List values) {
		this.values = values;
	}

	public BeanToArray(List beanNames, List values) {
		super();
		this.beanNames = beanNames;
		this.values = values;
	}

}
	