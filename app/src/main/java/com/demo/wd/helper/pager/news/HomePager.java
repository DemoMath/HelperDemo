package com.demo.wd.helper.pager.news;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.demo.wd.helper.R;
import com.demo.wd.helper.adapter.HomeListAdapter;
import com.demo.wd.helper.adapter.HomeViewPagerAdapter;
import com.demo.wd.helper.base.BasicPager;
import com.demo.wd.helper.bean.News;
import com.demo.wd.helper.ui.widget.PullToRefreshListView;
import com.demo.wd.helper.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 综合中的首页
 *
 * @author Administrator
 */
public class HomePager extends BasicPager implements PullToRefreshListView.OnRefershListener {

    private View mView;
    private PullToRefreshListView mLv_home;
    private View mViewPagerView;
    private LinearLayout mLl_viewpagers;
    private TextView mTv_title;
    private LinearLayout mLl_dots;
    private List<News> mData;
    private HomeListAdapter mAdapter;
    private List<View> mDots ;
    private List<Integer> mMImageUrls;
    private List<String> mMTitles;
    private int mCurrentPosition = mMImageUrls.size() * 1000;
    private ViewPager mPager;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mCurrentPosition = mCurrentPosition + 1;
            mPager.setCurrentItem(mCurrentPosition);
            mHandler.sendEmptyMessageDelayed(0,2000);
        }
    };


    public HomePager(Context context) {
        super(context);
    }

    @Override
    public View createSuccessView() {
        mView = View.inflate(CommonUtils.getContext(), R.layout.layout_home, null);

        mLv_home = (PullToRefreshListView) mView.findViewById(R.id.lv_home);
        mViewPagerView = View.inflate(CommonUtils.getContext(), R.layout.menu_newscenteritem_viewpager, null);

        mLl_viewpagers = (LinearLayout) mViewPagerView.findViewById(R.id.ll_viewpagers);
        mTv_title = (TextView) mViewPagerView.findViewById(R.id.tv_title);
        mLl_dots = (LinearLayout) mViewPagerView.findViewById(R.id.ll_dots);

        mLv_home.setViewPager(mViewPagerView);

        mLv_home.setOnRefershListener(this);

        mLv_home.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });


        mMImageUrls = new ArrayList<>();
        mMImageUrls.add(R.mipmap.home01);
        mMImageUrls.add(R.mipmap.home02);
        mMImageUrls.add(R.mipmap.home03);
        mMImageUrls.add(R.mipmap.home04);
        mMImageUrls.add(R.mipmap.home05);
        mMImageUrls.add(R.mipmap.home06);
        mMImageUrls.add(R.mipmap.home07);
        mMImageUrls.add(R.mipmap.home08);
        mMImageUrls.add(R.mipmap.home09);

        mMTitles = new ArrayList<>();
        mMTitles.add("俯瞰雪景");
        mMTitles.add("三元湖小舟");
        mMTitles.add("朦胧湖景");
        mMTitles.add("湖光波澜");
        mMTitles.add("青春朝阳");
        mMTitles.add("落叶");
        mMTitles.add("雨后清凉");
        mMTitles.add("五教爬山虎");
        mMTitles.add("小树林哈哈哈");



        initDot();
        /*// 2.创建viewpager
        RollViewPager rollViewPager = new RollViewPager(CommonUtils.getContext(), mDots);
        // 3.将图片文本和点传递给viewpager进行操作
        rollViewPager.initImageUrl(mMImageUrls);
        rollViewPager.initTitles(mMTitles, mTv_title);

        rollViewPager.initData();
        rollViewPager.roll();

        // ViewPager和ListView进行关联操作
        // 1.将ViewPager存放到viewpager布局文件的容器中，展示
        mLl_viewpagers.removeAllViews();
        mLl_viewpagers.addView(rollViewPager);*/

        mPager = new ViewPager(CommonUtils.getContext()){
            @Override
            public boolean onTouchEvent(MotionEvent ev) {
                switch (ev.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        stopRoll();
                        break;

                    case MotionEvent.ACTION_UP:
                        startRoll();
                        break;
                }
                return super.onTouchEvent(ev);
            }
        };

        mPager.setAdapter(new HomeViewPagerAdapter(mMImageUrls));
        mPager.setCurrentItem(mCurrentPosition);
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mCurrentPosition = position;
                position = position % mMImageUrls.size();
                mTv_title.setText(mMTitles.get(position));
                mDots.get(position).setBackgroundResource(R.mipmap.indicator_selected);
                // 设置其他点的样式
                for (int i = 0; i < mDots.size(); i++) {
                    if (i != position) {
                        mDots.get(i).setBackgroundResource(
                                R.mipmap.indicator_normal);
                    }
                }
            }
            @Override
            public void onPageSelected(int position) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        mLl_viewpagers.removeAllViews();
        mLl_viewpagers.addView(mPager);
        return mView;
    }

    public void startRoll() {
        mHandler.sendEmptyMessageDelayed(0,2000);
    }

    public  void stopRoll() {
        mHandler.removeCallbacksAndMessages(null);
    }

    private void initDot() {

        mLl_dots.removeAllViews();
        mDots = new ArrayList<View>();
        mDots.clear();
        // 根据图片的张数创建点的个数
        for (int i = 0; i < mMImageUrls.size(); i++) {
            View view = new View(CommonUtils.getContext());
            // 设置，如果是第一个点显示红色，其他点显示白色
            if (i == 0) {
                view.setBackgroundResource(R.mipmap.indicator_selected);
            } else {
                view.setBackgroundResource(R.mipmap.indicator_normal);
            }
            // 设置点的宽高
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(CommonUtils.dip2px(7),
                    CommonUtils.dip2px(7));
            params.setMargins(0, 0, 6, 0);
            // 设置点的属性，并且将点存放到点的容器中显示
			/*
			 * view.setLayoutParams(params); mLLDots.addView(view);
			 */
            mLl_dots.addView(view, params);
            // 将点存放到集合中显示
            mDots.add(view);
        }
    }

    @Override
    public Object loadData() {
        mData = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mData.add(i, new News("标题" + i, "内容" + i, "2016-05-06", "新闻", "评论：" + i));
        }
        mAdapter = new HomeListAdapter(mData);
        CommonUtils.runInMainThread(new Runnable() {
            @Override
            public void run() {
                mLv_home.setAdapter(mAdapter);

                startRoll();
            }
        });
        return mData;
    }

    @Override
    public void refresh() {
        List<News> data = new ArrayList<News>();
        for (int i = 0; i < 1; i++) {
            data.add(i, new News("刷新出的标题" + i, "刷新出的内容" + i, "2016-05-06", "新闻", "评论：" + i));
        }
        mData.addAll(0, data);
        mAdapter.setData(mData);
        mAdapter.notifyDataSetChanged();
        mLv_home.finish();
    }

    @Override
    public void loadmore() {
        List<News> data = new ArrayList<News>();
        for (int i = 0; i < 20; i++) {
            data.add(i, new News("分页加载的标题" + i, "分页加载的内容" + i, "2016-05-06", "新闻", "评论：" + i));
        }
        mData.addAll(data);
        mAdapter.setData(mData);
        mAdapter.notifyDataSetChanged();
        mLv_home.finish();
    }
}
