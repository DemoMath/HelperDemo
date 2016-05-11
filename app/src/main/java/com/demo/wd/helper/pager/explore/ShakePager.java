package com.demo.wd.helper.pager.explore;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.demo.wd.helper.base.BasicPager;
import com.demo.wd.helper.utils.CommonUtils;

/**
 * 发现中的摇一摇
 * @author Administrator
 *
 */
public class ShakePager extends BasicPager {

	public ShakePager(Context context) {
		super(context);
	}

	@Override
	public View createSuccessView() {
		TextView textView = new TextView(CommonUtils.getContext());
		textView.setText("答疑");
		return textView;
	}

	@Override
	public Object loadData() {
		return "1";
	}

}
