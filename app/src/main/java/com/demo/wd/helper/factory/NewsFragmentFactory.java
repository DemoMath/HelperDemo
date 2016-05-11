package com.demo.wd.helper.factory;

import android.support.v4.app.Fragment;

import com.demo.wd.helper.fragment.news.ActiveFragment;
import com.demo.wd.helper.fragment.news.HomeFragment;
import com.demo.wd.helper.fragment.news.RecommendFragment;
import com.demo.wd.helper.fragment.news.StudyFragment;

/**
 * 综合中的fragmetn工厂
 */
public class NewsFragmentFactory {
	public static Fragment[] fragments = new Fragment[4];
    public static Fragment createFragment(int position) {
        Fragment fragment = fragments[position];
        if (fragment == null) {
            switch (position) { 
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new StudyFragment();
                    break;
                case 2:
                    fragment = new ActiveFragment();
                    break;
                case 3:
                    fragment = new RecommendFragment();
                    break;
            }
        }
        fragments[position] = fragment;
        return fragment;
    }
}
