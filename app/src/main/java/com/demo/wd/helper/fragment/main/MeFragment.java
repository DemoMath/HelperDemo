package com.demo.wd.helper.fragment.main;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.wd.helper.R;
import com.demo.wd.helper.activity.LoginActivity;
import com.demo.wd.helper.activity.MeActivity;
import com.demo.wd.helper.base.NoThreadBaseFragment;
import com.demo.wd.helper.utils.CommonUtils;
import com.demo.wd.helper.utils.SpUtils;
import com.demo.wd.helper.utils.StringUtils;

/**
 * 这是主页中的我Fragment
 * @author Administrator
 *
 */
public class MeFragment extends NoThreadBaseFragment implements View.OnClickListener{

	private boolean isLogin = false;
	private String mUsername;

	public static final int MINE = 0;
	public static final int MESSAGE = 1;
	public static final int QIAODAN = 2;
	public static final int TEACHER = 3;

	@Override
	public View initView() {
		View view = View.inflate(CommonUtils.getContext(), R.layout.fragment_me,null);
		mUsername = SpUtils.getString(CommonUtils.getContext(), "username");
		if (!StringUtils.isEmpty(mUsername)) {
			isLogin = true;
		}
		return view;
	}


	@Override
	public void initData() {
		View mine = getView().findViewById(R.id.mine);
		View message = getView().findViewById(R.id.message);
		View qiandao = getView().findViewById(R.id.qiandao);
		View teacher = getView().findViewById(R.id.teacher);
		View iv_headimage = getView().findViewById(R.id.iv_headimage);
		TextView tv_loginname = (TextView) getView().findViewById(R.id.tv_loginname);
		View iv_erweima = getView().findViewById(R.id.iv_erweima);

		if (isLogin) {
			tv_loginname.setText(mUsername);
		}

		mine.setOnClickListener(this);
		message.setOnClickListener(this);
		qiandao.setOnClickListener(this);
		teacher.setOnClickListener(this);
		iv_headimage.setOnClickListener(this);
		tv_loginname.setOnClickListener(this);
		iv_erweima.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intentMe = new Intent(CommonUtils.getContext(), MeActivity.class);
		Intent intentLogin = new Intent(CommonUtils.getContext(), LoginActivity.class);
		switch (v.getId()) {
			case R.id.mine://个人信息
				if (isLogin) {
					intentMe.putExtra("meKey",MINE);
					startActivity(intentMe);
				} else {
					Toast.makeText(CommonUtils.getContext(),"请先登录",Toast.LENGTH_SHORT).show();
				}
				break;

			case R.id.message://我的消息
				if (isLogin) {
					intentMe.putExtra("meKey",MESSAGE);
					startActivity(intentMe);
				} else {
					Toast.makeText(CommonUtils.getContext(),"请先登录",Toast.LENGTH_SHORT).show();
				}
				break;

			case R.id.qiandao://签到记录
				if (isLogin) {
					intentMe.putExtra("meKey",QIAODAN);
					startActivity(intentMe);
				} else {
					Toast.makeText(CommonUtils.getContext(),"请先登录",Toast.LENGTH_SHORT).show();
				}
				break;

			case R.id.teacher://我的导师
				if (isLogin) {
					intentMe.putExtra("meKey",TEACHER);
					startActivity(intentMe);
				} else {
					Toast.makeText(CommonUtils.getContext(),"请先登录",Toast.LENGTH_SHORT).show();
				}
				break;

			case R.id.iv_headimage:
			case R.id.tv_loginname:
				if (isLogin) {
					intentMe.putExtra("meKey",MESSAGE);
					startActivity(intentMe);
				} else {
					startActivity(intentLogin);
					getActivity().finish();
				}
				break;

			case R.id.iv_erweima://二位码
				if (isLogin) {
					Toast.makeText(CommonUtils.getContext(),"创建个人二维码",Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(CommonUtils.getContext(),"请先登录",Toast.LENGTH_SHORT).show();
				}
				break;
		}
	}
}
