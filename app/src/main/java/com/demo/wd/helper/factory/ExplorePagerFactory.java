package com.demo.wd.helper.factory;


import com.demo.wd.helper.base.BasicPager;
import com.demo.wd.helper.fragment.main.ExploreFragment;
import com.demo.wd.helper.pager.explore.AnswerPager;
import com.demo.wd.helper.pager.explore.FindPager;
import com.demo.wd.helper.pager.explore.FriendGroupPager;
import com.demo.wd.helper.utils.CommonUtils;

public class ExplorePagerFactory {
	
	public static BasicPager[] pages = new BasicPager[3];
	
    public static BasicPager createPager(int position) {
    	BasicPager page = pages[position];
        if (page == null) {
            switch (position) { 
                case ExploreFragment.FRIENDSGROUP:
                	page = new FriendGroupPager(CommonUtils.getContext());
                    break;
                case ExploreFragment.FIND:
                	page = new FindPager(CommonUtils.getContext());
                    break;
                case ExploreFragment.ANSWER:
                	page = new AnswerPager(CommonUtils.getContext());
                    break;
            }
            pages[position] = page;
        }
        
        return page;
    }
}
