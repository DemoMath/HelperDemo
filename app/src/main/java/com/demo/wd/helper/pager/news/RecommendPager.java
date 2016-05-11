package com.demo.wd.helper.pager.news;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.wd.helper.R;
import com.demo.wd.helper.base.BasicPager;
import com.demo.wd.helper.randomLayout.StellarMap;
import com.demo.wd.helper.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 综合中的推荐
 * @author Administrator
 *
 */
public class RecommendPager extends BasicPager{

	private List<String> mTitleData;
	private List<Integer> mIconData;
	public RecommendPager(Context context) {
		super(context);
	}

	@Override
	public View createSuccessView() {
		StellarMap sm = new StellarMap(CommonUtils.getContext());
		mTitleData = new ArrayList<>();
		mTitleData.add("张老师");
		mTitleData.add("王老师");
		mTitleData.add("李老师");
		mTitleData.add("陆老师");
		mTitleData.add("吴老师");

		mTitleData.add("周老师");
		mTitleData.add("吴老师");
		mTitleData.add("郑老师");
		mTitleData.add("王老师");
		mTitleData.add("孙老师");

		mTitleData.add("徐老师");
		mTitleData.add("刘老师");
		mTitleData.add("谢老师");
		mTitleData.add("邱老师");
		mTitleData.add("李老师");

		mTitleData.add("邓老师");
		mTitleData.add("卞老师");
		mTitleData.add("愤怒的小鸡");
		mTitleData.add("我死了");
		mTitleData.add("累死的");

		mIconData = new ArrayList<>();
		mIconData.add(R.mipmap.alien);
		mIconData.add(R.mipmap.angry);
		mIconData.add(R.mipmap.anguished);
		mIconData.add(R.mipmap.astonished);
		mIconData.add(R.mipmap.blush);

		mIconData.add(R.mipmap.cold_sweat);
		mIconData.add(R.mipmap.confounded);
		mIconData.add(R.mipmap.confused);
		mIconData.add(R.mipmap.disappointed);
		mIconData.add(R.mipmap.cry);

		mIconData.add(R.mipmap.disappointed_relieved);
		mIconData.add(R.mipmap.dizzy_face);
		mIconData.add(R.mipmap.expressionless);
		mIconData.add(R.mipmap.fearful);
		mIconData.add(R.mipmap.grimacing);

		mIconData.add(R.mipmap.grin);
		mIconData.add(R.mipmap.grinning);
		mIconData.add(R.mipmap.heart_eyes);
		mIconData.add(R.mipmap.hushed);
		mIconData.add(R.mipmap.alien);

		sm.setAdapter(new StellarMap.Adapter() {
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
				View view = View.inflate(CommonUtils.getContext(), R.layout.layout_recommend_item,null);
				ImageView icon = (ImageView) view.findViewById(R.id.recommend_icon);
				final TextView title = (TextView) view.findViewById(R.id.recommend_title);
				if(group == 0){
					// data 中 [0-9] 给了组1
					title.setText(mTitleData.get(position));
				}else{
					// 组2 显示 data中[10-(size-1)]
					title.setText(mTitleData.get(position + 10));
				}
				//随机字体大小
				int size = 9 + new Random().nextInt(25);// [5,35)
				//随机字体颜色
				int red = 10 + new Random().nextInt(210);
				int green = 10 + new Random().nextInt(210);
				int blue = 10 + new Random().nextInt(210);
				int rgb = Color.rgb(red, green, blue);
				title.setTextSize(size);
				title.setTextColor(rgb);
				icon.setImageResource(mIconData.get(position));

				view.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Toast.makeText(CommonUtils.getContext(),"点击了"+title.getText().toString().trim(),Toast.LENGTH_SHORT).show();
					}
				});
				return view;
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
		sm.setGroup(0, true);

		sm.setRegularity(10, 10);

		return sm;
	}
	@Override
	public Object loadData() {
		return "1";
	}
}
