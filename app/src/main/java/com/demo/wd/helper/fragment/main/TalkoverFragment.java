package com.demo.wd.helper.fragment.main;


import android.support.v4.view.ViewPager;
import android.view.View;

import com.demo.wd.helper.R;
import com.demo.wd.helper.adapter.TweetViewPagerAdapter;
import com.demo.wd.helper.base.NoThreadBaseFragment;
import com.demo.wd.helper.ui.widget.PagerSlidingTabStrip;
import com.demo.wd.helper.utils.CommonUtils;

/**
 * 这是首页的动弹Fragment
 * @author Administrator
 *
 */
public class TalkoverFragment extends NoThreadBaseFragment {

	@Override
	public View initView() {
		View view = View.inflate(CommonUtils.getContext(), R.layout.fragment_tweet, null);
		return view;
	}

	@Override
	public void initData() {
		PagerSlidingTabStrip tweetPagertab = (PagerSlidingTabStrip) mView.findViewById(R.id.tweet_pagertab);
		CommonUtils.setTabs(tweetPagertab);
		ViewPager tweetViewpager = (ViewPager) mView.findViewById(R.id.tweet_viewpager);
		String[] tabNames = (String[]) CommonUtils.getContext().getResources().getStringArray(R.array.tweets_viewpage_arrays);
		tweetViewpager.setAdapter(new TweetViewPagerAdapter(tabNames));
		tweetPagertab.setViewPager(tweetViewpager);
	}
}
