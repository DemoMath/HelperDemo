package com.demo.wd.helper;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

public class MyApplication extends Application {
	private static Context context;
	private static Handler mainHanlder;
	private static int myTid ;

	@Override
	public void onCreate() {
		super.onCreate();

		context = getApplicationContext();
		mainHanlder = new Handler();
		myTid = android.os.Process.myTid();//OS给线程的id
//		Thread.currentThread().getId();//Thread类给线程id
	}
	
	
	public static Context getContext(){
		return context;
	}
	
	public static Handler getHandler(){
		return mainHanlder;
	}
	
	public static int getMainThreadId(){
		return myTid;
	}
}
