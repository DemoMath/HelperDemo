package com.demo.wd.helper.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.demo.wd.helper.R;
import com.demo.wd.helper.factory.NewsFragmentFactory;
import com.demo.wd.helper.utils.CommonUtils;

/**
 * Created by Administrator on 2016/4/12.
 */
public class NewsAdapter extends FragmentPagerAdapter {

    private String[] tabsName;

    public NewsAdapter(FragmentManager fm) {
        super(fm);
        tabsName =  CommonUtils.getContext().getResources().getStringArray(R.array.news_viewpage_arrays);
    }

    /*
     * 获取到title
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return tabsName[position];
    }

    @Override
    public Fragment getItem(int position) {
        return NewsFragmentFactory.createFragment(position);
    }

    @Override
    public int getCount() {
        return tabsName.length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

}
