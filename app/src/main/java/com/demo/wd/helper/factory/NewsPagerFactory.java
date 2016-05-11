package com.demo.wd.helper.factory;


import com.demo.wd.helper.base.BasicPager;
import com.demo.wd.helper.pager.news.ActivePager;
import com.demo.wd.helper.pager.news.HomePager;
import com.demo.wd.helper.pager.news.RecommendPager;
import com.demo.wd.helper.pager.news.StudyPager;
import com.demo.wd.helper.utils.CommonUtils;

public class NewsPagerFactory {
	
	public static BasicPager[] pages = new BasicPager[4];
	
    public static BasicPager createPager(int position) {
    	BasicPager page = pages[position];
        if (page == null) {
            switch (position) { 
                case 0:
                	page = new HomePager(CommonUtils.getContext());
                    break;
                case 1:
                	page = new StudyPager(CommonUtils.getContext());
                    break;
                case 2:
                	page = new ActivePager(CommonUtils.getContext());
                    break;
                case 3:
                	page = new RecommendPager(CommonUtils.getContext());
                    break;
            }
            pages[position] = page;
        }
        
        return page;
    }
}
