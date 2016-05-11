package com.demo.wd.helper.pager.explore;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.demo.wd.helper.R;
import com.demo.wd.helper.adapter.MessageListAdapter;
import com.demo.wd.helper.base.BasicPager;
import com.demo.wd.helper.bean.Message;
import com.demo.wd.helper.bean.MessagePhoto;
import com.demo.wd.helper.bean.Reply;
import com.demo.wd.helper.ui.widget.PullToRefreshListView;
import com.demo.wd.helper.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 发现中的朋友圈
 * @author Administrator
 *
 */
public class FriendGroupPager extends BasicPager implements PullToRefreshListView.OnRefershListener {

private View mView;
	private PullToRefreshListView mLv_friendgroup;
	private PullToRefreshListView lv_friendgroup;
	private ArrayList<Message> mData;

	public FriendGroupPager(Context context) {
		super(context);
	}

	@Override
	public View createSuccessView() {
		mView = View.inflate(CommonUtils.getContext(), R.layout.layout_friendgroup,null);

		lv_friendgroup = (PullToRefreshListView) mView.findViewById(R.id.lv_friendgroup);
		View view = View.inflate(CommonUtils.getContext(),R.layout.layout_friendgroup_listview_head,null);
		lv_friendgroup.setViewPager(view);
		lv_friendgroup.setOnRefershListener(this);
		return mView;
	}


	@Override
	public Object loadData() {
		mData = new ArrayList<>();
		Message message1 = new Message();
		message1.setName("张三");
		message1.setContentText("昨天晚上我吃饭的时候也有一只虫子！！！！");

		List<MessagePhoto> photos = new ArrayList<>();
		MessagePhoto messagePhoto1 = new MessagePhoto();
		messagePhoto1.setImageurl(R.mipmap.home01);
		photos.add(messagePhoto1);
		MessagePhoto messagePhoto2 = new MessagePhoto();
		messagePhoto2.setImageurl(R.mipmap.actionbar_bg);
		photos.add(messagePhoto2);
		MessagePhoto messagePhoto3 = new MessagePhoto();
		messagePhoto3.setImageurl(R.mipmap.home02);
		photos.add(messagePhoto3);
		message1.setImages(photos);

		List<Reply> replys = new ArrayList<>();
		Reply reply1 = new Reply();
		reply1.setSendName("wudi");
		reply1.setReplyName("张三");
		reply1.setContent("报应啊！！！！");
		replys.add(reply1);
		Reply reply2 = new Reply();
		reply2.setSendName("wudi");
		reply2.setReplyName("张三");
		reply2.setContent("报应啊！！！！");
		replys.add(reply2);
		message1.setReplyList(replys);

		message1.setSendDate("1小时前");


		Message message2 = new Message();
		message2.setName("李四");
		message2.setContentText("窝草，我也吃到虫子了");

		List<MessagePhoto> photos2 = new ArrayList<>();
		MessagePhoto messagePhoto11 = new MessagePhoto();
		messagePhoto11.setImageurl(R.mipmap.home06);
		photos2.add(messagePhoto1);
		message2.setImages(photos2);

		List<Reply> replys2 = new ArrayList<>();
		Reply reply11 = new Reply();
		reply11.setSendName("wudi");
		reply11.setReplyName("李四");
		reply11.setContent("苍天有眼！！");
		replys2.add(reply11);
		Reply reply12 = new Reply();
		reply12.setSendName("张三");
		reply12.setReplyName("李四");
		reply12.setContent("哈哈哈哈啊哈哈哈");
		replys2.add(reply12);
		message2.setReplyList(replys2);

		message2.setSendDate("1小时前");



		Message message3 = new Message();
		List<MessagePhoto> photos3 = new ArrayList<>();
		MessagePhoto messagePhoto21 = new MessagePhoto();
		messagePhoto21.setImageurl(R.mipmap.home08);
		photos3.add(messagePhoto21);
		List<Reply> replys3 = new ArrayList<>();
		Reply reply21 = new Reply();
		reply21.setSendName("wudi");
		reply21.setReplyName("李四");
		reply21.setContent("苍天有眼！！");
		replys3.add(reply21);
		Reply reply22 = new Reply();
		reply22.setSendName("张三");
		reply22.setReplyName("李四");
		reply22.setContent("哈哈哈哈啊哈哈哈");
		replys3.add(reply22);

		message3.setName("王五");
		message3.setContentText("窝草，我也吃到虫子了");
		message3.setImages(photos3);
		message3.setReplyList(replys3);
		message3.setSendDate("1小时前");

		Message message4 = new Message();
		List<MessagePhoto> photos4 = new ArrayList<>();
		MessagePhoto messagePhoto31 = new MessagePhoto();
		messagePhoto31.setImageurl(R.mipmap.home09);
		photos4.add(messagePhoto31);
		List<Reply> replys4 = new ArrayList<>();
		Reply reply31 = new Reply();
		reply31.setSendName("wudi");
		reply31.setReplyName("李四");
		reply31.setContent("苍天有眼！！");
		replys4.add(reply31);
		Reply reply32 = new Reply();
		reply32.setSendName("张三");
		reply32.setReplyName("李四");
		reply32.setContent("哈哈哈哈啊哈哈哈");
		replys4.add(reply32);

		message4.setName("赵六");
		message4.setContentText("窝草，我也吃到虫子了");
		message4.setImages(photos4);
		message4.setReplyList(replys4);
		message4.setSendDate("1小时前");

		mData.add(message1);
		mData.add(message2);
		mData.add(message3);
		mData.add(message4);
		CommonUtils.runInMainThread(new Runnable() {
			@Override
			public void run() {
				lv_friendgroup.setAdapter(new MessageListAdapter(mData));
			}
		});

		return mData;
	}

	@Override
	public void refresh() {
		Toast.makeText(CommonUtils.getContext(),"已是最新",Toast.LENGTH_SHORT).show();
		lv_friendgroup.finish();
	}

	@Override
	public void loadmore() {
		Toast.makeText(CommonUtils.getContext(),"没有更多了",Toast.LENGTH_SHORT).show();
		lv_friendgroup.finish();
	}
}
