package com.demo.wd.helper.pager.me;

import android.content.Context;
import android.view.View;

import com.demo.wd.helper.R;
import com.demo.wd.helper.base.BasicPager;
import com.demo.wd.helper.utils.CommonUtils;

/**
 * 我中的我的信息
 * @author Administrator
 *
 */
public class MinePager extends BasicPager{

	public MinePager(Context context) {
		super(context);
	}

	@Override
	public View createSuccessView() {
		View view = View.inflate(CommonUtils.getContext(), R.layout.layout_mine, null);
		return view;
	}

	@Override
	public Object loadData() {
		return "1";
	}
}
