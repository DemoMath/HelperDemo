package com.demo.wd.helper.pager.me;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.demo.wd.helper.R;
import com.demo.wd.helper.adapter.TeacherListAdapter;
import com.demo.wd.helper.base.BasicPager;
import com.demo.wd.helper.bean.TeacherInfo;
import com.demo.wd.helper.ui.widget.PullToRefreshListView;
import com.demo.wd.helper.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 我中的我的导师
 * @author Administrator
 *
 */
public class TeacherPager extends BasicPager implements PullToRefreshListView.OnRefershListener  {

	private PullToRefreshListView mLv_teacher;
	private List<TeacherInfo> mData;

	public TeacherPager(Context context) {
		super(context);
	}

	@Override
	public View createSuccessView() {
		View view = View.inflate(CommonUtils.getContext(), R.layout.layout_teacher, null);
		mLv_teacher = (PullToRefreshListView) view.findViewById(R.id.lv_teacher);
		mLv_teacher.setViewPager(new View(CommonUtils.getContext()));
		mLv_teacher.setOnRefershListener(this);
		return view;
	}

	@Override
	public Object loadData() {
		mData = new ArrayList<>();
		TeacherInfo teacherInfo1 = new TeacherInfo();
		teacherInfo1.setName("于立新老师");
		teacherInfo1.setDesc("研究方向：偏微分方程");
		TeacherInfo teacherInfo2 = new TeacherInfo();
		teacherInfo2.setName("王燕老师");
		teacherInfo2.setDesc("研究方向：图论与组合最优化");
		TeacherInfo teacherInfo3 = new TeacherInfo();
		teacherInfo3.setName("何志红老师");
		teacherInfo3.setDesc("研究方向：代数组合，图论");
		TeacherInfo teacherInfo4 = new TeacherInfo();
		teacherInfo4.setName("马云杰老师");
		teacherInfo4.setDesc("研究方向：偏微分方程反问题");
		TeacherInfo teacherInfo5 = new TeacherInfo();
		teacherInfo5.setName("杨玉军老师");
		teacherInfo5.setDesc("研究方向：图论及其应用");
		TeacherInfo teacherInfo6 = new TeacherInfo();
		teacherInfo6.setName("刘红霞老师");
		teacherInfo6.setDesc("研究方向：图论及其应用");
		mData.add(teacherInfo1);
		mData.add(teacherInfo2);
		mData.add(teacherInfo3);
		mData.add(teacherInfo4);
		mData.add(teacherInfo5);
		mData.add(teacherInfo6);
		CommonUtils.runInMainThread(new Runnable() {
			@Override
			public void run() {
				mLv_teacher.setAdapter(new TeacherListAdapter(mData));
			}
		});
		return "1";
	}

	@Override
	public void refresh() {
		Toast.makeText(CommonUtils.getContext(),"已是最新",Toast.LENGTH_SHORT).show();
		mLv_teacher.finish();
	}
	@Override
	public void loadmore() {
		Toast.makeText(CommonUtils.getContext(),"没有更多了",Toast.LENGTH_SHORT).show();
		mLv_teacher.finish();
	}
}
