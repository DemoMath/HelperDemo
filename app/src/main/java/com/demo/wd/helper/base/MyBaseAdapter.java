package com.demo.wd.helper.base;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/5/6.
 */
public abstract class MyBaseAdapter<T> extends android.widget.BaseAdapter {
    public List<T> mData;
    public MyBaseAdapter(List<T> data) {
        super();
        mData = data;
    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);

    public void setData(List<T> data) {
        mData = data;
    }

}
