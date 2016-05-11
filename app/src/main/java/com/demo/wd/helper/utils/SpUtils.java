package com.demo.wd.helper.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SpUtils {
	
	private static SharedPreferences sp;
	
	public static SharedPreferences getSp(Context context){
		if(sp == null){
			sp = context.getSharedPreferences("config",
					Context.MODE_PRIVATE);
		}
		 
		return sp;
	}
	public static void putParam(Context context, 
			String paramName, boolean value) {
		 sp = getSp(context);
		// 使用sp记录开关的状态
		Editor editor = sp.edit();
		editor.putBoolean(paramName, value);
		editor.commit();
	}
	
	public static boolean getBoolean(Context context,String paramName,boolean defautValue){
		 sp = getSp(context);
		boolean value = sp.getBoolean(paramName, defautValue);
		return value;
	}
	
	public static String getString(Context context,String paramName){
		 sp = getSp(context);
		String value = sp.getString(paramName, "");
		return value;
	}
	
	public static int getInt(Context context,String paramName){
		 sp = getSp(context);
		int value = sp.getInt(paramName, 0);
		return value;
	}
	
	public static void putParam(Context context, 
			String paramName, String value) {
		 sp = getSp(context);
		// 使用sp记录开关的状态
		Editor editor = sp.edit();
		editor.putString(paramName, value);
		editor.commit();
	}
	
	public static void putParam(Context context, 
			String paramName, int value) {
		
		 sp = getSp(context);
		// 使用sp记录开关的状态
		Editor editor = sp.edit();
		editor.putInt(paramName, value);
		editor.commit();
	}


}
