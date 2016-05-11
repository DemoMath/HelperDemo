package com.demo.wd.helper.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.wd.helper.R;
import com.demo.wd.helper.base.MyBaseAdapter;
import com.demo.wd.helper.bean.TeacherInfo;
import com.demo.wd.helper.utils.CommonUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/5/6.
 */
public class TeacherListAdapter extends MyBaseAdapter<TeacherInfo> {

    public TeacherListAdapter(List data) {
        super(data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HomeHolder holder ;
        if (convertView == null) {
            holder = new HomeHolder();
            convertView = View.inflate(CommonUtils.getContext(), R.layout.layout_teacher_item,null);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.desc = (TextView) convertView.findViewById(R.id.desc);
            convertView.setTag(holder);
        } else {
            holder = (HomeHolder) convertView.getTag();
        }
        TeacherInfo item = getItem(position);
        holder.name.setText(item.getName());
        holder.desc.setText(item.getDesc());
        return convertView;
    }
    public class HomeHolder {
        TextView name;
        TextView desc;
    }
}
