package com.demo.wd.helper.pager.talkover;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.demo.wd.helper.R;
import com.demo.wd.helper.adapter.MineTalkListAdapter;
import com.demo.wd.helper.base.BasicPager;
import com.demo.wd.helper.bean.TalkInfo;
import com.demo.wd.helper.ui.widget.PullToRefreshListView;
import com.demo.wd.helper.utils.CommonUtils;

import java.util.ArrayList;

/**
 * 动弹中的我的动弹
 * @author Administrator
 *
 */
public class MinePager extends BasicPager implements PullToRefreshListView.OnRefershListener  {

	private View mView;
	private PullToRefreshListView lvMinetalk;
	private ArrayList<TalkInfo> mListData;
	private MineTalkListAdapter mAdapter;

	public MinePager(Context context) {
		super(context);
	}

	@Override
	public View createSuccessView() {
		mView = View.inflate(CommonUtils.getContext(), R.layout.layout_minetalk, null);
		lvMinetalk = (PullToRefreshListView) mView.findViewById(R.id.lv_minetalk);
		lvMinetalk.setViewPager(new View(CommonUtils.getContext()));
		lvMinetalk.setOnRefershListener(this);
		lvMinetalk.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(CommonUtils.getContext(),"点击了"+ mListData.get(position - 1).getTitle(),Toast.LENGTH_SHORT).show();
			}
		});
		return mView;
	}

	@Override
	public Object loadData() {
		mListData = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			mListData.add(i, new TalkInfo("标题" + i, "内容" + i, "2016-05-06", "学习", "评论：" + i));
		}
		mAdapter = new MineTalkListAdapter(mListData);
		CommonUtils.runInMainThread(new Runnable() {
			@Override
			public void run() {
				lvMinetalk.setAdapter(mAdapter);
			}

		});
		return mListData;
	}

	@Override
	public void refresh() {
		Toast.makeText(CommonUtils.getContext(),"已是最新",Toast.LENGTH_SHORT).show();
		lvMinetalk.finish();
	}
	@Override
	public void loadmore() {
		Toast.makeText(CommonUtils.getContext(),"没有更多了",Toast.LENGTH_SHORT).show();
		lvMinetalk.finish();
	}
}
