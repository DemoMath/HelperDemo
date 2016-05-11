package com.demo.wd.helper.factory;

import android.support.v4.app.Fragment;

import com.demo.wd.helper.fragment.main.ExploreFragment;
import com.demo.wd.helper.fragment.main.MeFragment;
import com.demo.wd.helper.fragment.main.NewsFragment;
import com.demo.wd.helper.fragment.main.TalkoverFragment;

public class MainFragmentFactory {
	public static Fragment[] fragments = new Fragment[4];
    public static Fragment createFragment(int position) {
        Fragment fragment = fragments[position];
        if (fragment == null) {
            switch (position) { 
                case 0:
                    fragment = new NewsFragment();
                    break;
                case 1:
                    fragment = new TalkoverFragment();
                    break;
                case 2:
                    fragment = new ExploreFragment();
                    break;
                case 3:
                    fragment = new MeFragment();
                    break;
            }
        }
        fragments[position] = fragment;
        return fragment;
    }
}
