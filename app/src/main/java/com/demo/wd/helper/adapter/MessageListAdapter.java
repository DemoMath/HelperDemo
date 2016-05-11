package com.demo.wd.helper.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.wd.helper.R;
import com.demo.wd.helper.base.MyBaseAdapter;
import com.demo.wd.helper.bean.Message;
import com.demo.wd.helper.bean.MessagePhoto;
import com.demo.wd.helper.bean.Reply;
import com.demo.wd.helper.ui.widget.MessageDetailGridView;
import com.demo.wd.helper.ui.widget.MessageDetailListView;
import com.demo.wd.helper.utils.CommonUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/5/8.
 */
public class MessageListAdapter extends MyBaseAdapter<Message> {
    public MessageListAdapter(List<Message> data) {
        super(data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MessageHolder messageHolder;
        if (convertView == null) {
            messageHolder = new MessageHolder();
            convertView = View.inflate(CommonUtils.getContext(),R.layout.layout_message_items,null);
            messageHolder.name = (TextView) convertView.findViewById(R.id.name);
            messageHolder.content_text = (TextView) convertView.findViewById(R.id.content_text);
            messageHolder.image_content = (MessageDetailGridView) convertView.findViewById(R.id.image_content);
            messageHolder.date = (TextView) convertView.findViewById(R.id.date);
            messageHolder.reply_list = (MessageDetailListView) convertView.findViewById(R.id.reply_list);

            messageHolder.reply_more = (TextView) convertView.findViewById(R.id.reply_more);

            convertView.setTag(messageHolder);
        } else {
            messageHolder = (MessageHolder) convertView.getTag();
        }
        Message item = getItem(position);
        messageHolder.name.setText(item.getName());
        messageHolder.content_text.setText(item.getContentText());
        messageHolder.date.setText(item.getSendDate());
        messageHolder.image_content.setAdapter(new MessagePhotoItemAdapter(item.getImages()));
        messageHolder.reply_list.setAdapter(new MessageReplyItemAdapter(item.getReplyList()));

        if (item.getReplyList().size() < 6) {
            messageHolder.reply_more.setVisibility(View.GONE);
        }

        return convertView;
    }

    public class MessageHolder {
        TextView name;
        TextView content_text;
        MessageDetailGridView image_content;
        TextView date;
        MessageDetailListView reply_list;
        TextView reply_more;
    }


    public class MessagePhotoItemAdapter extends MyBaseAdapter<MessagePhoto> {

        public MessagePhotoItemAdapter(List<MessagePhoto> data) {
            super(data);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MessagePhotoHolder messagePhotoHolder;
            if (convertView == null) {
                messagePhotoHolder = new MessagePhotoHolder();
                convertView = View.inflate(CommonUtils.getContext(),R.layout.layout_message_image_item,null);
                //TODO
                messagePhotoHolder.image = (ImageView) convertView.findViewById(R.id.image);
                convertView.setTag(messagePhotoHolder);
            } else {
                messagePhotoHolder = (MessagePhotoHolder) convertView.getTag();
            }
            MessagePhoto item = getItem(position);
            //TODO
            messagePhotoHolder.image.setImageResource(item.getImageurl());

            return convertView;
        }
    }

    public class MessagePhotoHolder {
        ImageView image;
    }

    public class MessageReplyItemAdapter extends MyBaseAdapter<Reply> {

        public MessageReplyItemAdapter(List<Reply> data) {
            super(data);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ReplyHolder replyHolder;
            if (convertView == null) {
                replyHolder = new ReplyHolder();
                convertView = View.inflate(CommonUtils.getContext(),R.layout.layout_message_reply_item,null);
                //TODO
                replyHolder.send_name = (TextView) convertView.findViewById(R.id.send_name);
                replyHolder.reply_name = (TextView) convertView.findViewById(R.id.reply_name);
                replyHolder.content = (TextView) convertView.findViewById(R.id.content);
                convertView.setTag(replyHolder);
            } else {
                replyHolder = (ReplyHolder) convertView.getTag();
            }
            Reply item = getItem(position);
            //TODO
            replyHolder.send_name.setText(item.getSendName());
            replyHolder.reply_name.setText(item.getReplyName());
            replyHolder.content.setText(item.getContent());
            return convertView;
        }
    }

    public class ReplyHolder {
        TextView send_name;
        TextView reply_name;
        TextView content;
    }
}
