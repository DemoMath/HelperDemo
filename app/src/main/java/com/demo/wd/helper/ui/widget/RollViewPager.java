package com.demo.wd.helper.ui.widget;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.wd.helper.R;

import java.util.List;

public class RollViewPager extends ViewPager {

	private List<Integer> mImagerUrl;
	private List<String> mTitles;
	private TextView mTitle;
	private List<View> mDots;
	private MyPagerAdapter myPagerAdapter;

	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			// 设置滚动位置
			RollViewPager.this.setCurrentItem(currentPosition);
			// 重复滚动
			roll();
		};
	};
	private RunnableTask runnableTask;

	private int currentPosition = 0;
	private int startX;
	private int startY;

	public RollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public RollViewPager(Context context, List<View> dots) {
		super(context);
		this.mDots = dots;
		runnableTask = new RunnableTask();
		// 设置viewpager的界面切换监听，实现文本和点随着图片滚动而切换的操作
		setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				position = position % mImagerUrl.size();
				mTitle.setText(mTitles.get(position));
				mDots.get(position).setBackgroundResource(R.mipmap.indicator_selected);
				// 设置其他点的样式
				for (int i = 0; i < mDots.size(); i++) {
					if (i != position) {
						mDots.get(i).setBackgroundResource(
								R.mipmap.indicator_normal);
					}
				}
				// 重新设置更新滚动位置
				currentPosition = position;
			}

			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
			}

			@Override
			public void onPageScrollStateChanged(int state) {
			}
		});
	}

	/**
	 * 接受传递过来的图片的路径
	 * 
	 * @param imageurl
	 */
	public void initImageUrl(List<Integer> imageurl) {

		this.mImagerUrl = imageurl;
	}

	/**
	 * 接受传递过来的文本，以及MenuNewsCenterItemPager中的要显示文本的TextView对象
	 * 
	 * @param titles
	 * @param title
	 */
	public void initTitles(List<String> titles, TextView title) {
		if (titles != null && titles.size() > 0 && title != null) {
			this.mTitles = titles;
			this.mTitle = title;
			// 设置默认显示的文本
			mTitle.setText(mTitles.get(0));
		}
	}

	public void initData() {
		// 1.显示 数据
		if (myPagerAdapter == null) {
			myPagerAdapter = new MyPagerAdapter();
			setAdapter(myPagerAdapter);
		} else {
			myPagerAdapter.notifyDataSetChanged();
		}
	}

	/**
	 * 显示数据，自动滚动操作
	 */
	public void roll() {
		// 2.自动滚动操作
		handler.postDelayed(runnableTask, 2000);// 参数2：延迟时间
	}

	private class RunnableTask implements Runnable {

		@Override
		public void run() {
			// 1.设置滚动条目的位置
			// 获取自动滚动条目的位置
			// 0,1,2,3,0,1,2,3,0,1,2,3
			currentPosition = (currentPosition + 1) % mImagerUrl.size();
			// 2.给handler发送消息，进行自动滚动操作
			handler.obtainMessage().sendToTarget();// 获取一个Message,将这个message发送给handler进行操作
		}

	}
	private class MyPagerAdapter extends PagerAdapter {
		@Override
		public int getCount() {
			return Integer.MAX_VALUE;
		}
		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}
		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			position = position % mImagerUrl.size();
			View view = View.inflate(getContext(), R.layout.rollviewpager_item,
					null);
			ImageView imageview = (ImageView) view.findViewById(R.id.imageview);
			imageview.setBackgroundResource(mImagerUrl.get(position));
			container.addView(view);
			view.setOnTouchListener(new OnTouchListener() {

				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:
						//停止滑动
						handler.removeCallbacksAndMessages(null);
						break;
					case MotionEvent.ACTION_UP:
						//重新进行滑动操作
						roll();
						break;
					case MotionEvent.ACTION_CANCEL:
						//重新进行滑动操作
						roll();
						break;
					}
					return true;
				}
			});
			return view;
		}
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}
	// 事件分发操作
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// getParent() : 获取父控件
			getParent().requestDisallowInterceptTouchEvent(true);// 请求父控件不要拦截事件，true：不拦截事件，传递事件，false:拦截事件
			startX = (int) ev.getX();
			startY = (int) ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			int endX = (int) ev.getX();
			int endY = (int) ev.getY();
			// 判断是滑动viewpager还是滑动ListView
			if (Math.abs(startX - endX) > Math.abs(startY - endY)) {
				// 滑动ViewPager
				// 从右往左
				if (startX - endX > 0
						&& getCurrentItem() == getAdapter().getCount() - 1) {
					// 父控件切换界面
					getParent().requestDisallowInterceptTouchEvent(false);
				} else if (startX - endX > 0
						&& getCurrentItem() < getAdapter().getCount() - 1) {
					// 当前控件切换图片
					getParent().requestDisallowInterceptTouchEvent(true);
				}
				// 从左往右
				else if (startX - endX < 0 && getCurrentItem() == 0) {
					// 父控件打开侧拉菜单
					getParent().requestDisallowInterceptTouchEvent(false);
				} else if (startX - endX < 0 && getCurrentItem() > 0) {
					// 当前控件切换到上一张图片
					getParent().requestDisallowInterceptTouchEvent(true);
				}
			} else {
				// 滑动ListView
				getParent().requestDisallowInterceptTouchEvent(false);
			}
			break;
		case MotionEvent.ACTION_UP:

			break;
		}
		return super.dispatchTouchEvent(ev);
	}
	// 当界面退出的执行的方法
	@Override
	protected void onDetachedFromWindow() {
		// 当前界面退出的，移出handler的发送消息的延迟操作
		handler.removeCallbacksAndMessages(null);
		super.onDetachedFromWindow();
	}

}
