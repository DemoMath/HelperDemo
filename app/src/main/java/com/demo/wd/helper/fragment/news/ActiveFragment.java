package com.demo.wd.helper.fragment.news;

import android.view.View;

import com.demo.wd.helper.base.NoThreadBaseFragment;
import com.demo.wd.helper.pager.news.ActivePager;
import com.demo.wd.helper.utils.CommonUtils;

/**
 * 这是综合中的活动
 * @author Administrator
 *
 */
public class ActiveFragment extends NoThreadBaseFragment {

	@Override
	public View initView() {
		View view = new ActivePager(CommonUtils.getContext());
		return view;
	}

	@Override
	public void initData() {}
	
}
