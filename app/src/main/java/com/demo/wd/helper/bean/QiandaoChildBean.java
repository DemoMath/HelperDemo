package com.demo.wd.helper.bean;

/**
 * 二级Item实体类
 * 
 * @author zihao
 * 
 */
public class QiandaoChildBean {
	/** 签到时间 **/
	private String qiandaoTime;
	/** 签到地点 **/
	private String qiandaoAddress;

	public QiandaoChildBean(String qiandaoTime,String qiandaoAddress) {
		this.qiandaoTime = qiandaoTime;
		this.qiandaoAddress = qiandaoAddress;
	}
	public void setQiandaoTime(String qiandaoTime) {
		this.qiandaoTime = qiandaoTime;
	}

	public void setQiandaoAddress(String qiandaoAddress) {
		this.qiandaoAddress = qiandaoAddress;
	}

	public String getQiandaoTime() {
		return qiandaoTime;
	}

	public String getQiandaoAddress() {
		return qiandaoAddress;
	}
}
