package com.demo.wd.helper.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;

import com.demo.wd.helper.MyApplication;
import com.demo.wd.helper.ui.widget.PagerSlidingTabStrip;

import java.util.concurrent.atomic.AtomicInteger;

public class CommonUtils {
	// string id  转成  String 对象
	public static String getString(int id){
		Context mContext = MyApplication.getContext();
		Resources resources = mContext.getResources();
		return resources.getString(id);
	}
	
	//通过Drawable资源id获取Drawable对象
	public static Drawable getDrawable(int id){
		return getContext().getResources().getDrawable(id);
	}
	
	public static Context getContext(){
		return MyApplication.getContext();
	}
	
	/**
	 * xml布局 转成 View对象
	 * @param id
	 * @return
	 */
	public static View inflate(int id){
//		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		LayoutInflater.from(getContext()).inflate(id, null);
		
		return View.inflate(getContext(), id, null);
	}
	
	/**
	 * dip 转 px
	 * px = dip * density
	 * @param dip
	 * @return
	 */
	public static int dip2px(int dip){
		float density = getContext().getResources().getDisplayMetrics().density;
		// float -> int  1.1 1  1.6 1
		//				 1.6 1  2.1 2
		return (int) (dip * density + 0.5f);
	}
	
	public static int px2dip(int px){
		float density = getContext().getResources().getDisplayMetrics().density;
		// float -> int  1.1 1  1.6 1
		//				 1.6 1  2.1 2
		return (int) (px / density + 0.5f);
	}
	
	/**
	 * 运行一个任务在主线程
	 */
	public static void runInMainThread(Runnable task){
		//判断当前线程是否是主线程
		if(isMainThread()){
			//当前是主线程
			task.run();
		}else{
			Handler handler = MyApplication.getHandler();
			handler.post(task);
		}
	}
	
	/**
	 * 判断当前线程是否是主线程
	 * @return
	 */
	public static boolean isMainThread(){
//		if(android.os.Process.myTid() == MyApplication.getMainThreadId()){
//			return true;
//		}else{
//			return false;
//		}
		
		return android.os.Process.myTid() == MyApplication.getMainThreadId();
	}

	/**
	 * 把自身从父控件中移除
	 * @param
	 */
	public static void removeFromParent(View child) {
		if(child != null){
			ViewParent parent = child.getParent();
			if(parent != null && parent instanceof ViewGroup){
				ViewGroup viewGroup = (ViewGroup) parent;
				viewGroup.removeView(child);
			}
		}
	}
	
	private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);
    /**
     * Generate a value suitable for use in {@link #(int)}.
     * This value will not collide with ID values generated at build time by aapt for R.id.
     *
     * @return a generated ID value
     */
    public static int generateViewId() {
        for (;;) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }
    
    /**
     * 创建一个圆角矩形
     * @param color
     * @param radius
     * @return
     */
    public static GradientDrawable getGradientDrawable(int color,int radius){
		//圆角矩形
		GradientDrawable gradientDrawable = new GradientDrawable();
		//设置类型
		gradientDrawable.setGradientType(GradientDrawable.RECTANGLE);
		//设置颜色
		gradientDrawable.setColor(color);
		//设置圆角
		gradientDrawable.setCornerRadius(radius);
		//设置矩形边缘大小和颜色
		gradientDrawable.setStroke(1, color);
		
		return gradientDrawable;
    }
    
    public static StateListDrawable getStateListDrawable(Drawable pressedDrawable,Drawable normalDrawable){
    	//背景颜色选择器
		StateListDrawable stateListDrawable = new StateListDrawable();
		//添加状态和对应的Drawable
		stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressedDrawable);
		stateListDrawable.addState(new int[]{}, normalDrawable);
		
		return stateListDrawable;
    }
    public static void setTabs(PagerSlidingTabStrip tweetPagertab) {
		// 平分页签
		tweetPagertab.setShouldExpand(true);
		// 去掉分割线
		tweetPagertab.setDividerColor(Color.TRANSPARENT);
		// 设置滑动条的颜色
		tweetPagertab.setIndicatorColor(0xffFFFFFF);
		// 设置滑动条的高度
		tweetPagertab.setIndicatorHeight(6);
		// 设置文字大小
		tweetPagertab.setTextSize(CommonUtils.dip2px(14));
		// 设置文字字体
		tweetPagertab.setTypeface(Typeface.DEFAULT, Typeface.NORMAL);
//		tabs.setTextColorResource(R.color.viewpage_selector_slide_title);
		// 提供设置选中页签字体颜色的方法
		tweetPagertab.setSelectedTabColor(0xffFFFFFF);
	}

	public static void initActivityView(Activity activity,View view) {
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			//透明状态栏
			activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			//透明导航栏
			activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		}

		int ret = -1;
		int resourceId = activity.getResources().getIdentifier("status_bar_height","dimen","android");
		if (resourceId > 0){
			ret = activity.getResources().getDimensionPixelSize(resourceId);
		}

		ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
		layoutParams.height = ret;
		view.setLayoutParams(layoutParams);
	}
}
