package com.demo.wd.helper.pager.explore;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.wd.helper.R;
import com.demo.wd.helper.base.BasicPager;
import com.demo.wd.helper.base.MyBaseAdapter;
import com.demo.wd.helper.randomLayout.StellarMap;
import com.demo.wd.helper.utils.CommonUtils;
import com.demo.wd.helper.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 发现中的找人
 * @author Administrator
 *
 */
public class FindPager extends BasicPager {


	private List<String> mTitleData;
	private List<Integer> mIconData;
	private StellarMap mSm;
	private FrameLayout mResult;
	private EditText mSelect;
	private View mView;
	private ListView mLv_result;
	private TextView mNoone;

	public FindPager(Context context) {
		super(context);
	}
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			mSm.zoomIn();
			mHandler.sendEmptyMessageDelayed(0,3000);
		}
	};
	@Override
	public View createSuccessView() {
		mView = View.inflate(CommonUtils.getContext(), R.layout.layout_find,null);
		mResult = (FrameLayout) mView.findViewById(R.id.result);
		mSelect = (EditText) mView.findViewById(R.id.select);
		mLv_result = (ListView) mView.findViewById(R.id.lv_result);
		mNoone = (TextView) mView.findViewById(R.id.noone);


		mSelect.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				String selectContent = mSelect.getText().toString().trim();
				if (StringUtils.isEmpty(selectContent)) {
					mNoone.setVisibility(View.GONE);
					mLv_result.setVisibility(View.GONE);
					mSm.setVisibility(View.VISIBLE);
					startChange();
					return;
				}
				stopChange();
				final List<String> resultList = new ArrayList<String>();
				for (String content : mTitleData) {
					if (content.contains(selectContent)) {
						resultList.add(content);
					}
				}
				if (resultList.size() == 0) {
					mNoone.setVisibility(View.VISIBLE);
					mLv_result.setVisibility(View.GONE);
					mSm.setVisibility(View.GONE);
				} else {
					mLv_result.setVisibility(View.VISIBLE);
					mNoone.setVisibility(View.GONE);
					mSm.setVisibility(View.GONE);
					CommonUtils.runInMainThread(new Runnable() {
						@Override
						public void run() {
							mLv_result.setAdapter(new ResultAdapter(resultList));
						}
					});
				}
			}

			@Override
			public void afterTextChanged(Editable s) {
			}
		});


		mSm = new StellarMap(CommonUtils.getContext()){
			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
				return false;
			}

			@Override
			public boolean onTouchEvent(MotionEvent event) {
				return false;
			}

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return false;
			}

		};
		mTitleData = new ArrayList<>();
		mTitleData.add("徐东方");
		mTitleData.add("刘斌");
		mTitleData.add("吴迪");
		mTitleData.add("张老师");
		mTitleData.add("令狐冲");

		mTitleData.add("东方不败");
		mTitleData.add("周星驰");
		mTitleData.add("独孤求败");
		mTitleData.add("霍华健");
		mTitleData.add("胡歌");

		mTitleData.add("郑鑫");
		mTitleData.add("王涛");
		mTitleData.add("经元帅");
		mTitleData.add("李世元");
		mTitleData.add("柏鑫焱");

		mTitleData.add("邓超");
		mTitleData.add("黄晓明");
		mTitleData.add("愤怒的小鸡");
		mTitleData.add("我快累死了");
		mTitleData.add("好困");

		mSm.setAdapter(new StellarMap.Adapter() {
			@Override
			public int getGroupCount() {
				return 2;
			}

			@Override
			public int getCount(int group) {
				if(group == 0){
					//组1返回10个View
					return 10;
				}else{
					//组2返回data中剩下的View数
					return mTitleData.size()-10;
				}
			}
			@Override
			public View getView(int group, int position, View convertView) {
				final TextView textView = new TextView(CommonUtils.getContext());
				if(group == 0){
					// data 中 [0-9] 给了组1
					textView.setText(mTitleData.get(position));
				}else{
					// 组2 显示 data中[10-(size-1)]
					textView.setText(mTitleData.get(position + 10));
				}
				//随机字体大小
				int size = 9 + new Random().nextInt(22);// [5,35)
				textView.setTextSize(size);
				textView.setTextColor(Color.WHITE);

				textView.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Toast.makeText(CommonUtils.getContext(),"点击了"+textView.getText().toString().trim(),Toast.LENGTH_SHORT).show();
					}
				});
				return textView;
			}

			@Override
			public int getNextGroupOnPan(int group, float degree) {
				return 1 - group;
			}

			@Override
			public int getNextGroupOnZoom(int group, boolean isZoomIn) {
				return 1 - group;
			}
		});
		mSm.setGroup(0, true);

		mSm.setRegularity(10, 10);
		mResult.addView(mSm);

		return mView;
	}
	@Override
	public Object loadData() {
		CommonUtils.runInMainThread(new Runnable() {
			@Override
			public void run() {
				startChange();
			}
		});
		return ShowSuccess;
	}

	public void startChange() {
		mHandler.sendEmptyMessageDelayed(0,3000);
	}

	public  void stopChange() {
		mHandler.removeCallbacksAndMessages(null);
	}


	public class ResultAdapter extends MyBaseAdapter<String> {
		public ResultAdapter(List<String> data) {
			super(data);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ResultHolder resultHolder;
			if (convertView == null) {
				resultHolder = new ResultHolder();
				convertView = View.inflate(CommonUtils.getContext(),R.layout.layout_find_result_item,null);
				resultHolder.username = (TextView) convertView.findViewById(R.id.username);
				convertView.setTag(resultHolder);
			} else {
				resultHolder = (ResultHolder) convertView.getTag();
			}
			String item = getItem(position);
			resultHolder.username.setText(item);
			return convertView;
		}

		public class ResultHolder{
			TextView username;
		}
	}
}
