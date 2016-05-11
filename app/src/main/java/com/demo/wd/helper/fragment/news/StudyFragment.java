package com.demo.wd.helper.fragment.news;

import android.view.View;

import com.demo.wd.helper.base.NoThreadBaseFragment;
import com.demo.wd.helper.pager.news.StudyPager;
import com.demo.wd.helper.utils.CommonUtils;

/**
 * 这是综合中的学习
 * @author Administrator
 *
 */
public class StudyFragment extends NoThreadBaseFragment {

	@Override
	public View initView() {
		View view = new StudyPager(CommonUtils.getContext());
		return view;
	}

	@Override
	public void initData() {}
	
}
