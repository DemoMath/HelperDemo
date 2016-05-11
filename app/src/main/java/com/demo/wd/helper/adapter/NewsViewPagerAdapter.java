package com.demo.wd.helper.adapter;

import android.view.ViewGroup;

import com.demo.wd.helper.base.BasePagerAdapter;
import com.demo.wd.helper.base.BasicPager;
import com.demo.wd.helper.factory.NewsPagerFactory;
import com.demo.wd.helper.utils.CommonUtils;

public class NewsViewPagerAdapter extends BasePagerAdapter {

	public NewsViewPagerAdapter(String[] tabsName) {
		super(tabsName);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		BasicPager createPager = NewsPagerFactory.createPager(position);
		CommonUtils.removeFromParent(createPager);
		container.addView(createPager);
		return createPager;
	}
}
