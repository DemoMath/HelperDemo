package com.demo.wd.helper.fragment.main;


import android.support.v4.view.ViewPager;
import android.view.View;

import com.demo.wd.helper.R;
import com.demo.wd.helper.adapter.NewsViewPagerAdapter;
import com.demo.wd.helper.base.NoThreadBaseFragment;
import com.demo.wd.helper.factory.ExplorePagerFactory;
import com.demo.wd.helper.factory.NewsPagerFactory;
import com.demo.wd.helper.pager.explore.FindPager;
import com.demo.wd.helper.pager.news.HomePager;
import com.demo.wd.helper.ui.widget.PagerSlidingTabStrip;
import com.demo.wd.helper.utils.CommonUtils;

/**
 * 这是首页的综合Fragment
 * @author Administrator
 *
 */
public class NewsFragment extends NoThreadBaseFragment {
	@Override
	public View initView() {
		View view = View.inflate(CommonUtils.getContext(), R.layout.fragment_news, null);
		return view;
	}
	
	@Override
	public void initData() {
		PagerSlidingTabStrip newsPagertab = (PagerSlidingTabStrip) mView.findViewById(R.id.news_pagertab);
		
		CommonUtils.setTabs(newsPagertab);

		ViewPager newsViewpager = (ViewPager) mView.findViewById(R.id.news_viewpager);
		String[] tabsName =  CommonUtils.getContext().getResources().getStringArray(R.array.news_viewpage_arrays);
		newsViewpager.setAdapter(new NewsViewPagerAdapter(tabsName));
		newsPagertab.setViewPager(newsViewpager);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		FindPager findPager = (FindPager) ExplorePagerFactory.createPager(1);
		findPager.stopChange();
	}
}
