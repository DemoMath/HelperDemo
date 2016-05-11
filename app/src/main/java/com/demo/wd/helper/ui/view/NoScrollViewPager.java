package com.demo.wd.helper.ui.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NoScrollViewPager extends ViewPager {

	public NoScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public NoScrollViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	//不拦截事件，将事件传递给子控件执行
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		return false;
	}
	//执行viewpager的滑动操作
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		//通过重写系统的viewpager的onTouchEvent方法，不执行任何的滑动viewpager的代码，屏蔽viewpager的滑动事件
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
