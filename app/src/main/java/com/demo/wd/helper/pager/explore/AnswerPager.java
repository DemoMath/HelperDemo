package com.demo.wd.helper.pager.explore;

import android.content.Context;
import android.view.View;

import com.demo.wd.helper.R;
import com.demo.wd.helper.base.BasicPager;
import com.demo.wd.helper.utils.CommonUtils;

/**
 * 发现中的答疑
 * @author Administrator
 *
 */
public class AnswerPager extends BasicPager {

	public AnswerPager(Context context) {
		super(context);
	}

	@Override
	public View createSuccessView() {
		View view = View.inflate(CommonUtils.getContext(), R.layout.layout_answer,null);
		return view;
	}

	@Override
	public Object loadData() {
		return "1";
	}

}
