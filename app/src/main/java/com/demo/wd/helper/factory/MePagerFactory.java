package com.demo.wd.helper.factory;


import com.demo.wd.helper.base.BasicPager;
import com.demo.wd.helper.fragment.main.MeFragment;
import com.demo.wd.helper.pager.me.MessagePager;
import com.demo.wd.helper.pager.me.MinePager;
import com.demo.wd.helper.pager.me.QiandaoPager;
import com.demo.wd.helper.pager.me.TeacherPager;
import com.demo.wd.helper.utils.CommonUtils;

public class MePagerFactory {
	
	public static BasicPager[] pages = new BasicPager[4];
	
    public static BasicPager createPager(int position) {
    	BasicPager page = pages[position];
        if (page == null) {
            switch (position) { 
                case MeFragment.MINE:
                	page = new MinePager(CommonUtils.getContext());
                    break;
                case MeFragment.MESSAGE:
                	page = new MessagePager(CommonUtils.getContext());
                    break;
                case MeFragment.QIAODAN:
                    page = new QiandaoPager(CommonUtils.getContext());
                    break;
                case MeFragment.TEACHER:
                    page = new TeacherPager(CommonUtils.getContext());
                    break;
            }
            pages[position] = page;
        }
        
        return page;
    }
}
