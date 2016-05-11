package com.demo.wd.helper.factory;


import com.demo.wd.helper.base.BasicPager;
import com.demo.wd.helper.pager.talkover.MinePager;
import com.demo.wd.helper.pager.talkover.NewPager;
import com.demo.wd.helper.utils.CommonUtils;

public class TalkovertPagerFactory {
	
	public static BasicPager[] pages = new BasicPager[3];
	
    public static BasicPager createPager(int position) {
    	BasicPager page = pages[position];
        if (page == null) {
            switch (position) { 
                case 0:
                	page = new NewPager(CommonUtils.getContext());
                    break;
                case 1:
                	page = new MinePager(CommonUtils.getContext());
                    break;
            }
            pages[position] = page;
        }
        
        return page;
    }
}
