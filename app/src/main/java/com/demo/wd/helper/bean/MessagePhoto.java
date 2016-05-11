package com.demo.wd.helper.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 图片实体类
 * 
 * @author jiangyue
 * 
 */
public class MessagePhoto implements Parcelable {
	public static final String DATA="data";
	public static final String POSITION="position";
	public String max;// 大图地址
	public String min;// 小图地址
	public Integer imageurl;

	public Integer getImageurl() {
		return imageurl;
	}

	public void setImageurl(Integer imageurl) {
		this.imageurl = imageurl;
	}


	public MessagePhoto() {
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeString(max);
		out.writeString(min);
	}

	public static final Creator<MessagePhoto> CREATOR = new Creator<MessagePhoto>() {
		@Override
		public MessagePhoto[] newArray(int size) {
			return new MessagePhoto[size];
		}
		@Override
		public MessagePhoto createFromParcel(Parcel in) {
			return new MessagePhoto(in);
		}
	};

	public MessagePhoto(Parcel in) {
		max = in.readString();
		min = in.readString();
	}
}
