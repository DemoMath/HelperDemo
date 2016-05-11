package com.demo.wd.helper.base;

import android.view.View;

public abstract class BaseHolder<T>{
	public View view;
	private T mData;
	public BaseHolder() {
		//1，调用view = initView（），不知晓如何处理，抽象
		view = initView();
		//2,给view设置tag
		view.setTag(this);
	}
	
	public abstract View initView() ;
	public abstract void refreshView();
	
	//给listView对应的每一个条目设置数据的方法
	public void setData(T mData){
		this.mData = mData;
		//数据设置到控件上的方法，控件有什么未知，数据什么类型也未知，这个方法不能实现，抽象
		refreshView() ;
	}
	
	public T getData(){
		return mData;
	}
	
	public View getRootView(){
		return view;
	}
}
