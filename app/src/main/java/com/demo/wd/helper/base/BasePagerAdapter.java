package com.demo.wd.helper.base;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public abstract class BasePagerAdapter extends PagerAdapter {

	protected String[] tabsName;
	
	public BasePagerAdapter(String[] tabsName) {
		super();
		this.tabsName = tabsName;
	}
	/*
	 * 获取到title
	 */
	@Override
	public CharSequence getPageTitle(int position) {
		return tabsName[position];
	}
	
	@Override
	public int getCount() {
		return tabsName.length;
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}
	
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}
	
	@Override
	public abstract Object instantiateItem(ViewGroup container, int position) ;
	
}
