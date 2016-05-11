package com.demo.wd.helper.pager.me;

import android.content.Context;
import android.view.View;
import android.widget.ExpandableListView;

import com.demo.wd.helper.R;
import com.demo.wd.helper.adapter.StatusExpandAdapter;
import com.demo.wd.helper.base.BasicPager;
import com.demo.wd.helper.bean.QiandaoChildBean;
import com.demo.wd.helper.bean.QiandaoGroupBean;
import com.demo.wd.helper.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 我中的签到信息
 * @author Administrator
 *
 */
public class QiandaoPager extends BasicPager {

	private View mView;
	private ExpandableListView expandlistView;
	private StatusExpandAdapter statusAdapter;
	private List<QiandaoGroupBean> mData;

	public QiandaoPager(Context context) {
		super(context);
	}

	@Override
	public View createSuccessView() {
		mView = View.inflate(CommonUtils.getContext(), R.layout.layout_qiandao,null);
		expandlistView = (ExpandableListView) mView.findViewById(R.id.expandlist);
		return mView;
	}

	@Override
	public Object loadData() {
		mData = new ArrayList<>();

		List<QiandaoChildBean> childList1 = new ArrayList<>();

		childList1.add(new QiandaoChildBean("23:21","综合楼"));
		childList1.add(new QiandaoChildBean("23:21","综合楼"));
		childList1.add(new QiandaoChildBean("23:21","综合楼"));
		childList1.add(new QiandaoChildBean("23:21","综合楼"));
		childList1.add(new QiandaoChildBean("23:21","综合楼"));

		mData.add(new QiandaoGroupBean("2016-5-7 星期一",childList1));
		mData.add(new QiandaoGroupBean("2016-5-7 星期二",childList1));
		mData.add(new QiandaoGroupBean("2016-5-7 星期三",childList1));
		mData.add(new QiandaoGroupBean("2016-5-7 星期四",childList1));
		mData.add(new QiandaoGroupBean("2016-5-7 星期五",childList1));
		CommonUtils.runInMainThread(new Runnable() {
			@Override
			public void run() {
				statusAdapter = new StatusExpandAdapter(CommonUtils.getContext(), mData);
				expandlistView.setAdapter(statusAdapter);
				expandlistView.setGroupIndicator(null); // 去掉默认带的箭头
				expandlistView.setSelection(0);// 设置默认选中项
				int groupCount = expandlistView.getCount();
				for (int i=0; i<groupCount; i++)
				{
					expandlistView.expandGroup(i);
				};
			}
		});

		return 1;
	}

}
