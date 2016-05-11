package com.demo.wd.helper.fragment.news;

import android.view.View;

import com.demo.wd.helper.base.NoThreadBaseFragment;
import com.demo.wd.helper.pager.news.HomePager;
import com.demo.wd.helper.utils.CommonUtils;

/**
 * 这是综合中的首页
 * @author Administrator
 *
 */
public class HomeFragment extends NoThreadBaseFragment {

	@Override
	public View initView() {
		View view = new HomePager(CommonUtils.getContext());
		return view;
	}

	@Override
	public void initData() {}

}
