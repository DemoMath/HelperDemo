package com.demo.wd.helper.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.wd.helper.R;
import com.demo.wd.helper.base.MyBaseAdapter;
import com.demo.wd.helper.bean.News;
import com.demo.wd.helper.utils.CommonUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/5/6.
 */
public class HomeListAdapter extends MyBaseAdapter<News> {

    public HomeListAdapter(List data) {
        super(data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HomeHolder holder ;
        if (convertView == null) {
            holder = new HomeHolder();
            convertView = View.inflate(CommonUtils.getContext(), R.layout.layout_home_item,null);
            holder.icon = (ImageView) convertView.findViewById(R.id.icon);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.content = (TextView) convertView.findViewById(R.id.content);
            holder.type = (TextView) convertView.findViewById(R.id.type);
            holder.time = (TextView) convertView.findViewById(R.id.time);
            holder.discuss = (TextView) convertView.findViewById(R.id.discuss);
            convertView.setTag(holder);
        } else {
            holder = (HomeHolder) convertView.getTag();
        }
        News item = getItem(position);
        holder.icon.setBackgroundResource(R.mipmap.logo);
        holder.title.setText(item.getTitle());
        holder.content.setText(item.getContent());
        holder.type.setText(item.getType());
        holder.time.setText(item.getTime());
        holder.discuss.setText(item.getDiscuss());
        return convertView;
    }
    public class HomeHolder {
        ImageView icon;
        TextView title;
        TextView content;
        TextView type;
        TextView time;
        TextView discuss;
    }
}
