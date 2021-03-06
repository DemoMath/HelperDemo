package com.demo.wd.helper.pager.talkover;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.demo.wd.helper.R;
import com.demo.wd.helper.adapter.NewTalkListAdapter;
import com.demo.wd.helper.base.BasicPager;
import com.demo.wd.helper.bean.TalkInfo;
import com.demo.wd.helper.ui.widget.PullToRefreshListView;
import com.demo.wd.helper.utils.CommonUtils;

import java.util.ArrayList;

/**
 * 动弹中的最新动弹
 *
 * @author Administrator
 */
public class NewPager extends BasicPager implements PullToRefreshListView.OnRefershListener {

    private View mView;
    private PullToRefreshListView lvNewtalk;
    private ArrayList<TalkInfo> mListData;
    private NewTalkListAdapter mAdapter;

    public NewPager(Context context) {
        super(context);
    }

    @Override
    public View createSuccessView() {
        mView = View.inflate(CommonUtils.getContext(), R.layout.layout_newtalk, null);
        lvNewtalk = (PullToRefreshListView) mView.findViewById(R.id.lv_newtalk);
        lvNewtalk.setViewPager(new View(CommonUtils.getContext()));
        lvNewtalk.setOnRefershListener(this);
        lvNewtalk.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CommonUtils.getContext(), "点击了" + mListData.get(position - 1).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        return mView;
    }

    @Override
    public Object loadData() {
        mListData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mListData.add(i, new TalkInfo("标题" + i, "内容" + i, "2016-05-06", "学习", "评论：" + i));
        }
        mAdapter = new NewTalkListAdapter(mListData);
        CommonUtils.runInMainThread(new Runnable() {
            @Override
            public void run() {
                lvNewtalk.setAdapter(mAdapter);
            }

        });
        return mListData;
    }

    @Override
    public void refresh() {
        Toast.makeText(CommonUtils.getContext(), "已是最新", Toast.LENGTH_SHORT).show();
        lvNewtalk.finish();
    }

    @Override
    public void loadmore() {
        Toast.makeText(CommonUtils.getContext(), "没有更多了", Toast.LENGTH_SHORT).show();
        lvNewtalk.finish();
    }
}
