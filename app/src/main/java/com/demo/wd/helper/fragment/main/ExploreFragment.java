package com.demo.wd.helper.fragment.main;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.demo.wd.helper.R;
import com.demo.wd.helper.activity.ExploreActivity;
import com.demo.wd.helper.base.NoThreadBaseFragment;
import com.demo.wd.helper.factory.ExplorePagerFactory;
import com.demo.wd.helper.pager.explore.FindPager;
import com.demo.wd.helper.utils.CommonUtils;
import com.demo.wd.helper.utils.SpUtils;
import com.demo.wd.helper.utils.StringUtils;

/**
 * 这是主页中的发现
 * @author Administrator
 *
 */
public class ExploreFragment extends NoThreadBaseFragment implements View.OnClickListener{

	private View mView;
	public static final int FRIENDSGROUP = 0;
	public static final int FIND = 1;
	public static final int ANSWER = 2;
	private boolean isLogin = false;
	private String mUsername;
	/*
	public static final int SHAKE = 3;
	public static final int SCANNING = 4;*/
	@Override
	public View initView() {
		mView = View.inflate(CommonUtils.getContext(), R.layout.fragment_explore,null);

		mUsername = SpUtils.getString(CommonUtils.getContext(), "username");
		if (!StringUtils.isEmpty(mUsername)) {
			isLogin = true;
		}
		return mView;
	}

	@Override
	public void initData() {
		View friendsGroup =  mView.findViewById(R.id.tv_friendsgroup);
		View find =  mView.findViewById(R.id.tv_find);
		View action =  mView.findViewById(R.id.tv_answer);
		/*View shake =  mView.findViewById(R.id.tv_shake);
		View scanning =  mView.findViewById(R.id.tv_scanning);*/

		friendsGroup.setOnClickListener(this);
		find.setOnClickListener(this);
		action.setOnClickListener(this);
		/*shake.setOnClickListener(this);
		scanning.setOnClickListener(this);*/
	}

	@Override
	public void onClick(View v) {
		Intent intentExplore = new Intent(CommonUtils.getContext(), ExploreActivity.class);
		switch (v.getId()) {
			case R.id.tv_friendsgroup://朋友圈
				if (isLogin) {
					intentExplore.putExtra("exploreKey",FRIENDSGROUP);
					startActivity(intentExplore);
				} else {
					Toast.makeText(CommonUtils.getContext(),"请先登录",Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.tv_find://找人
				if (isLogin) {
					intentExplore.putExtra("exploreKey",FIND);
					startActivity(intentExplore);
				} else {
					Toast.makeText(CommonUtils.getContext(),"请先登录",Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.tv_answer://答疑
				if (isLogin) {
					Toast.makeText(CommonUtils.getContext(),"功能不予开放！",Toast.LENGTH_SHORT).show();
//
//					intentExplore.putExtra("exploreKey",ANSWER);
//					startActivity(intentExplore);
				} else {
					Toast.makeText(CommonUtils.getContext(),"请先登录",Toast.LENGTH_SHORT).show();
				}
				break;
			/*case R.id.tv_shake://摇一摇
				intentExplore.putExtra("exploreKey",SHAKE);
				break;
			case R.id.tv_scanning://扫一扫
				intentExplore.putExtra("exploreKey",SCANNING);
				break;*/
		}
	}


	@Override
	public void onDestroy() {
		super.onDestroy();
		FindPager findPager = (FindPager) ExplorePagerFactory.createPager(1);
		findPager.stopChange();
	}
}
