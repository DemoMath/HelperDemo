package com.demo.wd.helper.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.demo.wd.helper.utils.CommonUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/5/7.
 */
public class HomeViewPagerAdapter extends PagerAdapter {
    private List<Integer> mData;
    public HomeViewPagerAdapter(List<Integer> data) {
        super();
        mData = data;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(CommonUtils.getContext());
        position = position % mData.size();
        imageView.setBackgroundResource(mData.get(position));
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
