package com.demo.wd.helper.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.wd.helper.R;
import com.demo.wd.helper.base.MyBaseAdapter;
import com.demo.wd.helper.bean.ActiveImageInfo;
import com.demo.wd.helper.utils.CommonUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/5/6.
 */
public class ActiveListAdapter extends MyBaseAdapter<ActiveImageInfo> {

    public ActiveListAdapter(List<ActiveImageInfo> data) {
        super(data);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ActiveHolder holder ;
        if (convertView == null) {
            holder = new ActiveHolder();
            convertView = View.inflate(CommonUtils.getContext(), R.layout.layout_active_item,null);
            holder.image = (ImageView) convertView.findViewById(R.id.image);
            holder.des = (TextView) convertView.findViewById(R.id.des);
            convertView.setTag(holder);
        } else {
            holder = (ActiveHolder) convertView.getTag();
        }
        ActiveImageInfo item = getItem(position);
        holder.image.setBackgroundResource(item.getImgUrl());
        holder.des.setText(item.getDes());
        return convertView;
    }

    public class ActiveHolder {
        ImageView image;
        TextView des;
    }
}
