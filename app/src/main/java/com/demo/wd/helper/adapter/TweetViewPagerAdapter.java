package com.demo.wd.helper.adapter;

import android.view.ViewGroup;

import com.demo.wd.helper.base.BasePagerAdapter;
import com.demo.wd.helper.base.BasicPager;
import com.demo.wd.helper.factory.TalkovertPagerFactory;
import com.demo.wd.helper.utils.CommonUtils;

public class TweetViewPagerAdapter extends BasePagerAdapter {

	public TweetViewPagerAdapter(String[] tabsName) {
		super(tabsName);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		BasicPager createPager = TalkovertPagerFactory.createPager(position);
		CommonUtils.removeFromParent(createPager);
		container.addView(createPager);
		return createPager;
	}

}
