package com.demo.wd.helper.pager.news;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.demo.wd.helper.R;
import com.demo.wd.helper.adapter.ActiveListAdapter;
import com.demo.wd.helper.base.BasicPager;
import com.demo.wd.helper.bean.ActiveImageInfo;
import com.demo.wd.helper.ui.widget.PullToRefreshListView;
import com.demo.wd.helper.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 综合中的活动
 * @author Administrator
 *
 */
public class ActivePager extends BasicPager implements PullToRefreshListView.OnRefershListener {

	private View mView;
	private PullToRefreshListView mLvActive;
	private ActiveListAdapter mActiveListAdapter;
	private List<ActiveImageInfo> mData;

	public ActivePager(Context context) {
		super(context);
	}

	@Override
	public View createSuccessView() {
		mView = View.inflate(CommonUtils.getContext(), R.layout.layout_active,null);
		mLvActive = (PullToRefreshListView) mView.findViewById(R.id.lv_active);
		mLvActive.setViewPager(new View(CommonUtils.getContext()));
		mLvActive.setOnRefershListener(this);
		mLvActive.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(CommonUtils.getContext(),"点击了"+ mData.get(position - 1).getDes(),Toast.LENGTH_SHORT).show();
			}
		});
		return mView;
	}

	@Override
	public Object loadData() {
		mData = new ArrayList<>();
		mData.add(new ActiveImageInfo(R.mipmap.active01,"第一个活动"));
		mData.add(new ActiveImageInfo(R.mipmap.active02,"第二个活动"));
		mData.add(new ActiveImageInfo(R.mipmap.active03,"第三个活动"));
		mData.add(new ActiveImageInfo(R.mipmap.active04,"第四个活动"));
		mData.add(new ActiveImageInfo(R.mipmap.active05,"第一个活动"));
		mData.add(new ActiveImageInfo(R.mipmap.active06,"第一个活动"));
		mData.add(new ActiveImageInfo(R.mipmap.active07,"第一个活动"));
		mData.add(new ActiveImageInfo(R.mipmap.active08,"第一个活动"));
		mData.add(new ActiveImageInfo(R.mipmap.active09,"第一个活动"));
		mData.add(new ActiveImageInfo(R.mipmap.active10,"第一个活动"));
		mData.add(new ActiveImageInfo(R.mipmap.active11,"第一个活动"));
		mData.add(new ActiveImageInfo(R.mipmap.active12,"第一个活动"));
		mActiveListAdapter = new ActiveListAdapter(mData);
		CommonUtils.runInMainThread(new Runnable() {
			@Override
			public void run() {
				mLvActive.setAdapter(mActiveListAdapter);
			}
		});

		return "1";
	}
	@Override
	public void refresh() {
		Toast.makeText(CommonUtils.getContext(),"已是最新",Toast.LENGTH_SHORT).show();
		mLvActive.finish();
	}
	@Override
	public void loadmore() {
		Toast.makeText(CommonUtils.getContext(),"没有更多了",Toast.LENGTH_SHORT).show();
		mLvActive.finish();
	}
}
