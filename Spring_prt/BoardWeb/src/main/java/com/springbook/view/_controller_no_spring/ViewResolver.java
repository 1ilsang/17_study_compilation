package com.springbook.view._controller_no_spring;

public class ViewResolver {
	public String prefix;
	public String suffix;

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	
	public String getview(String viewName){
		return prefix + viewName + suffix;
	}
}
