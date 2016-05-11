package com.demo.wd.helper.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 朋友信息bean
 * 
 * @author jiangyue
 * 
 */
public class Message implements Serializable{
	public int id;
	public String photo;// 头像icon
	public String name;// 名字
	public String contentText;// 发表的文字内容
	public String sendDate;// 发表时间
	public String favourName;// 点赞的名字
	public List<Reply> replyList;// 回复的listView
	public List<MessagePhoto> images;//发表的图片url
	public int replyCount;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getContentText() {
		return contentText;
	}
	public void setContentText(String contentText) {
		this.contentText = contentText;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	public String getFavourName() {
		return favourName;
	}
	public void setFavourName(String favourName) {
		this.favourName = favourName;
	}
	public List<Reply> getReplyList() {
		return replyList;
	}
	public void setReplyList(List<Reply> replyList) {
		this.replyList = replyList;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public void setImages(List<MessagePhoto> images) {
		this.images = images;
	}

	public List<MessagePhoto> getImages() {
		return images;
	}
}
