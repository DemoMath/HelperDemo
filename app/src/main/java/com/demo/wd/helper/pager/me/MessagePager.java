package com.demo.wd.helper.pager.me;

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
 * 我中的我的消息
 * @author Administrator
 *
 */
public class MessagePager extends BasicPager implements PullToRefreshListView.OnRefershListener {

	private View mView;
	private PullToRefreshListView lv_message;
	private List<Message> mData;

	public MessagePager(Context context) {
		super(context);
	}

	@Override
	public View createSuccessView() {
		mView = View.inflate(CommonUtils.getContext(), R.layout.layout_message, null);
		lv_message = (PullToRefreshListView) mView.findViewById(R.id.lv_message);
		View view = View.inflate(CommonUtils.getContext(),R.layout.layout_message_listview_head,null);
		lv_message.setViewPager(view);
		lv_message.setOnRefershListener(this);
		return mView;
	}

	@Override
	public Object loadData() {
		mData = new ArrayList<>();
		Message message1 = new Message();
		message1.setName("wudi");
		message1.setContentText("昨天晚上我吃饭的时候有一只虫子");

		List<MessagePhoto> photos = new ArrayList<>();
		MessagePhoto messagePhoto1 = new MessagePhoto();
		messagePhoto1.setImageurl(R.mipmap.active01);
		photos.add(messagePhoto1);
		MessagePhoto messagePhoto2 = new MessagePhoto();
		messagePhoto2.setImageurl(R.mipmap.active02);
		photos.add(messagePhoto2);
		MessagePhoto messagePhoto3 = new MessagePhoto();
		messagePhoto3.setImageurl(R.mipmap.active03);
		photos.add(messagePhoto3);
		MessagePhoto messagePhoto4 = new MessagePhoto();
		messagePhoto4.setImageurl(R.mipmap.active04);
		photos.add(messagePhoto4);
		message1.setImages(photos);

		List<Reply> replys = new ArrayList<>();
		Reply reply1 = new Reply();
		reply1.setSendName("张三");
		reply1.setReplyName("wudi");
		reply1.setContent("能长肉了");
		replys.add(reply1);
		Reply reply2 = new Reply();
		reply2.setSendName("李四");
		reply2.setReplyName("wudi");
		reply2.setContent("能长肉了");
		replys.add(reply2);
		Reply reply3 = new Reply();
		reply3.setSendName("王五");
		reply3.setReplyName("wudi");
		reply3.setContent("能长肉了");
		replys.add(reply3);
		Reply reply4 = new Reply();
		reply4.setSendName("赵六");
		reply4.setReplyName("wudi");
		reply4.setContent("能长肉了");
		replys.add(reply4);
		Reply reply5 = new Reply();
		reply5.setSendName("郑七");
		reply5.setReplyName("wudi");
		reply5.setContent("能长肉了");
		replys.add(reply5);
		message1.setReplyList(replys);

		message1.setSendDate("1小时前");


		Message message2 = new Message();
		message2.setName("wudi");
		message2.setContentText("昨天晚上我吃饭的时候有一只虫子");

		List<MessagePhoto> photos2 = new ArrayList<>();
		MessagePhoto messagePhoto11 = new MessagePhoto();
		messagePhoto11.setImageurl(R.mipmap.active01);
		photos2.add(messagePhoto1);
		MessagePhoto messagePhoto12 = new MessagePhoto();
		messagePhoto12.setImageurl(R.mipmap.active02);
		photos2.add(messagePhoto2);
		MessagePhoto messagePhoto13 = new MessagePhoto();
		messagePhoto13.setImageurl(R.mipmap.active03);
		photos2.add(messagePhoto13);
		MessagePhoto messagePhoto14 = new MessagePhoto();
		messagePhoto14.setImageurl(R.mipmap.active04);
		photos2.add(messagePhoto14);

		message2.setImages(photos);

		List<Reply> replys2 = new ArrayList<>();
		Reply reply11 = new Reply();
		reply11.setSendName("张三");
		reply11.setReplyName("wudi");
		reply11.setContent("能长肉了");
		replys2.add(reply11);
		Reply reply12 = new Reply();
		reply12.setSendName("李四");
		reply12.setReplyName("wudi");
		reply12.setContent("能长肉了");
		replys2.add(reply12);
		Reply reply13 = new Reply();
		reply13.setSendName("王五");
		reply13.setReplyName("wudi");
		reply13.setContent("能长肉了");
		replys2.add(reply13);
		Reply reply14 = new Reply();
		reply14.setSendName("赵六");
		reply14.setReplyName("wudi");
		reply14.setContent("能长肉了");
		replys2.add(reply14);
		Reply reply15 = new Reply();
		reply15.setSendName("郑七");
		reply15.setReplyName("wudi");
		reply15.setContent("能长肉了");
		replys2.add(reply15);
		message2.setReplyList(replys2);

		message2.setSendDate("1小时前");
		mData.add(message1);
		mData.add(message2);
		CommonUtils.runInMainThread(new Runnable() {
			@Override
			public void run() {
				lv_message.setAdapter(new MessageListAdapter(mData));
			}
		});

		return mData;
	}

	@Override
	public void refresh() {
		Toast.makeText(CommonUtils.getContext(),"已是最新",Toast.LENGTH_SHORT).show();
		lv_message.finish();
	}

	@Override
	public void loadmore() {
		Toast.makeText(CommonUtils.getContext(),"没有更多了",Toast.LENGTH_SHORT).show();
		lv_message.finish();
	}

}
