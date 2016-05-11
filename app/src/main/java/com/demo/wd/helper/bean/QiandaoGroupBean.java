package com.demo.wd.helper.bean;

import java.util.List;

/**
 * 一级Item实体类
 * 
 * @author zihao
 * 
 */
public class QiandaoGroupBean {
	private String groupName;
	/** 二级Item数据列表 **/
	private List<QiandaoChildBean> childList;

	public QiandaoGroupBean(String groupName,List<QiandaoChildBean> childList) {
		this.groupName = groupName;
		this.childList = childList;
	}


	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<QiandaoChildBean> getChildList() {
		return childList;
	}

	public void setChildList(List<QiandaoChildBean> childList) {
		this.childList = childList;
	}

}