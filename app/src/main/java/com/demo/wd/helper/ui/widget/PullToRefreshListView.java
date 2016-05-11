package com.demo.wd.helper.ui.widget;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.demo.wd.helper.R;

import java.text.SimpleDateFormat;
import java.util.Date;


public class PullToRefreshListView extends ListView implements OnScrollListener{

	private View headerView;
	private LinearLayout rootView;
	private LinearLayout refreshHeaderView;
	private ProgressBar progressbar;
	private ImageView arr;
	private TextView title;
	private TextView time;
	private int headerViewmeasuredHeight;
	private RotateAnimation up;
	private RotateAnimation down;
	private int startY = -1;
	// 下拉刷新
	private static final int PULL_DOWN = 1;
	// 释放刷新
	private static final int RELEASE_REFRESH = 2;
	// 正在刷新
	private static final int REFRESHING = 3;
	// 当前的状态
	private int CURRENTOPTION = PULL_DOWN;
	/**
	 * 是否进行上拉加载操作
	 */
	private boolean isLoadMore=false;
	
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			//取消刷新操作
			finish();
		};
	};
	private View customView;
	private View footerView;
	private int footerViewmeasuredHeight;
	
	public PullToRefreshListView(Context context) {
		// super(context);
		this(context, null);
	}
	public PullToRefreshListView(Context context, AttributeSet attrs) {
		// super(context, attrs);
		this(context, attrs, 0);
	}

	public PullToRefreshListView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);

		//去分割线
		this.setDivider(null);

		this.setDividerHeight(1);
		//去掉选中背景
		this.setSelector(android.R.color.transparent);

		setHeader();
		initAnimation();
		setFooter();
		setOnScrollListener(this);
	}
	/**
	 * 添加刷新头
	 */
	private void setHeader() {
		headerView = View.inflate(getContext(), R.layout.refresh_header, null);

		rootView = (LinearLayout) headerView
				.findViewById(R.id.refresh_header_root);
		// 真正的刷新头
		refreshHeaderView = (LinearLayout) headerView
				.findViewById(R.id.refresh_header_view);
		progressbar = (ProgressBar) headerView.findViewById(R.id.progressbar);
		arr = (ImageView) headerView.findViewById(R.id.imageView);

		title = (TextView) headerView.findViewById(R.id.state_text);
		time = (TextView) headerView.findViewById(R.id.time);

		// 因为刷新头布局中包含viewpager和刷新头，但是隐藏的只是刷新头，没有隐藏viewpager,所以隐藏的是refreshHeaderView
		refreshHeaderView.measure(0, 0);
		headerViewmeasuredHeight = refreshHeaderView.getMeasuredHeight();
		refreshHeaderView.setPadding(0, -headerViewmeasuredHeight, 0, 0);

		// 将viewpager和刷新头所在的布局作为刷新头添加到ListView中
		addHeaderView(headerView);
	}
	/**
	 * 添加加载更多条目
	 */
	private void setFooter() {
		footerView = View.inflate(getContext(), R.layout.refresh_footer, null);
		
		//隐藏加载更多条目
		footerView.measure(0, 0);
		footerViewmeasuredHeight = footerView.getMeasuredHeight();
		footerView.setPadding(0, 0, 0, -footerViewmeasuredHeight);
		
		addFooterView(footerView);
	}

	/**
	 * 将viewpager的布局文件，添加到刷新头的布局文件中
	 */
	public void setViewPager(View view) {
		customView = view;
		rootView.addView(view);
	}

	/**
	 * 初始化动画 旋转动画
	 */
	private void initAnimation() {
		up = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		up.setDuration(300);
		up.setFillAfter(true);// 保持动画结束的状态

		down = new RotateAnimation(-180, -360, Animation.RELATIVE_TO_SELF,
				0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		down.setDuration(300);
		down.setFillAfter(true);
	}

	// 下拉刷新的满足条件
	// 1.下拉操作
	// 2.当前界面显示的第一个条目，是ListView的第一个条目
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startY = (int) ev.getY();// 当viewpager和ListView同时存在的时候，viewpager会把ListView的down事件拦截，所以startY根本获取到值
			break;
		case MotionEvent.ACTION_MOVE:
			//如果是正在刷新，不能下拉
			if (CURRENTOPTION==REFRESHING) {
				return true;
			}
			//如果Viewpager显示一半，下拉不能执行下拉刷新操作
			//如果Viewpager的在屏幕中y坐标小于ListView在屏幕中的y坐标的，不能执行下拉操作，如果是大于，执行下拉操作
			int[] listviewLocation = new int[2];
			getLocationOnScreen(listviewLocation);//获取控件在屏幕中的坐标，存放到一个int数组中保存
			int listViewLocationY = listviewLocation[1];
			
			int[] viewpagerLocation = new int[2];
			customView.getLocationOnScreen(viewpagerLocation);
			int viewPagerLoactionY = viewpagerLocation[1];
			
			if (listViewLocationY > viewPagerLoactionY) {
				startY=-1;
				break;
			}
			
			if (startY == -1) {
				startY = (int) ev.getY();// 可以获取和按下坐标相邻的点的坐标，作为按下的坐标来进行处理
			}
			// endY-startY > 0:下拉操作
			int endY = (int) ev.getY();
			// 下拉操作,并且当前界面显示的第一个条目，是ListView的第一个条目
			if (endY - startY > 0 && getFirstVisiblePosition() == 0) {
				// 下拉刷新头操作
				int padding = endY - startY - headerViewmeasuredHeight;
				// 让刷新头平移padding距离
				refreshHeaderView.setPadding(0, padding, 0, 0);
				// 下拉刷新 -> 释放刷新
				if (padding > 0 && CURRENTOPTION == PULL_DOWN) {
					CURRENTOPTION = RELEASE_REFRESH;
					switchOption();
				}
				// 释放刷新 -> 下拉刷新
				if (padding < 0 && CURRENTOPTION == RELEASE_REFRESH) {
					CURRENTOPTION = PULL_DOWN;
					switchOption();
				}
				return true;// 实现当滑动到listview的第一个条目的时候，还可以继续往下接着滑，而不是像系统ListView一样，滑动到第一个条目，再往下滑的时候就不能滑动
			}
			break;
		case MotionEvent.ACTION_UP:
			startY = -1;
			// 释放刷新 -> 正在刷新
			if (CURRENTOPTION == RELEASE_REFRESH) {
				CURRENTOPTION = REFRESHING;
				refreshHeaderView.setPadding(0, 0, 0, 0);
				switchOption();
				//handler.sendEmptyMessageDelayed(0, 3000);
				//3.使用回调监听方法
				if (onRefershListener!=null) {
					onRefershListener.refresh();
				}
			}
			// 下拉刷新 -> 弹回刷新头
			if (CURRENTOPTION == PULL_DOWN) {
				refreshHeaderView
						.setPadding(0, -headerViewmeasuredHeight, 0, 0);
			}
			break;
		}
		return super.onTouchEvent(ev);
	}

	/**
	 * 根据刷新的状态更改Ui状态
	 */
	private void switchOption() {
		switch (CURRENTOPTION) {
		case PULL_DOWN:
			title.setText("下拉刷新");
			arr.startAnimation(down);
			break;
		case RELEASE_REFRESH:
			title.setText("释放刷新");
			arr.startAnimation(up);
			break;
		case REFRESHING:
			title.setText("正在刷新");
			arr.clearAnimation();//清除动画
			arr.setVisibility(View.INVISIBLE);
			progressbar.setVisibility(View.VISIBLE);
			time.setText(getTime());
			break;
		}
	}
	/**
	 * 获取格式化事件
	 * @return
	 */
	private String getTime() {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(date);
	}
	/**
	 * 取消刷新操作
	 */
	public void finish() {
		//正在刷新 -> 下拉刷新
		if (CURRENTOPTION==REFRESHING) {
			CURRENTOPTION = PULL_DOWN;
			title.setText("下拉刷新");
			progressbar.setVisibility(View.INVISIBLE);
			arr.setVisibility(View.VISIBLE);
			refreshHeaderView.setPadding(0, -headerViewmeasuredHeight, 0, 0);
		}

		//取消上拉加载更多操作
		if (isLoadMore) {
			isLoadMore=false;
			footerView.setPadding(0, 0, 0, -footerViewmeasuredHeight);
		}
	}
	//当listView的滑动状态改变的时候调用
	//scrollState : listView的滑动状态
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		//当ListView滑动到最后一个条目，并且停止滑动的时候，显示上拉加载条目
		//SCROLL_STATE_IDLE : 空闲状态
		//SCROLL_STATE_TOUCH_SCROLL : 触摸滑动状态
		//SCROLL_STATE_FLING : 快速滑动的状态
		if (scrollState==OnScrollListener.SCROLL_STATE_IDLE && getLastVisiblePosition() == getCount()-1 && isLoadMore==false) {
			isLoadMore=true;
			footerView.setPadding(0, 0, 0, 0);
			setSelection(getCount()-1);//定位到listView的那个条目的位置，position:条目的位置
			//handler.sendEmptyMessageDelayed(0, 3000);
			//3.使用回调监听方法
			if (onRefershListener!=null) {
				onRefershListener.loadmore();
			}
		}
	}
	//当listview滑动的时候调用
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
	}
	
	private OnRefershListener onRefershListener;

	public void setOnRefershListener(OnRefershListener onRefershListener) {
		this.onRefershListener = onRefershListener;

	}

	//1.创建回调监听
	public interface OnRefershListener{
		/**
		 * 下拉刷新
		 */
		public void refresh();
		/**
		 * 加载更多
		 */
		public void loadmore();
	}
}
