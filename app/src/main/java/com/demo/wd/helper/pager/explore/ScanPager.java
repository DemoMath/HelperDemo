package com.demo.wd.helper.pager.explore;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.demo.wd.helper.base.BasicPager;
import com.demo.wd.helper.utils.CommonUtils;

/**
 * 发现中的扫一扫
 * @author Administrator
 *
 */
public class ScanPager extends BasicPager {

	public ScanPager(Context context) {
		super(context);
	}

	@Override
	public View createSuccessView() {
		TextView textView = new TextView(CommonUtils.getContext());
		textView.setText("扫一扫");
		return textView;
	}

	@Override
	public Object loadData() {
		return "1";
	}

}
