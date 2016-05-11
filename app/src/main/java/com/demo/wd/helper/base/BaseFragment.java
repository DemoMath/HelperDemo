package com.demo.wd.helper.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.wd.helper.utils.CommonUtils;

/**
 * Created by Administrator on 2016/4/25.
 */
public abstract class BaseFragment extends Fragment {

    protected BasicPager mBasicPager;//注意此处的修饰符不能是private

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //这样做的目的是，解决fragment的缓存问题，默认fragment的view会被销毁，重新进入会重新加载数据,消耗用户的流量
        //因此，要销毁掉此Fragment的爹
        if (mBasicPager==null) {
            mBasicPager = new BasicPager(getActivity()){
                //加载成功后的view
                @Override
                public View createSuccessView() {
                    return getSuccessView();
                }
                //加载每个界面的数据
                @Override
                public Object loadData() {
                    return requestData();
                }
            };
        } else {
            //重点：onCreateView : 复用contentPage-NoSaveStateFrameLayout
            //报错信息：you must call removeView()  on the Child's  parent first
            CommonUtils.removeFromParent(mBasicPager);
        }
        return mBasicPager;
    }
    
    /**
     * 每个子类实现此方法，加载不同的数据
     * @return
     */
    protected  abstract Object requestData();
    /**
     * 每个子类实现此方法，加载不同的view
     * @return
     */
    protected abstract View getSuccessView();

    public  void loadDataAndRefresh() {
        mBasicPager.loadDataAndRefreshPage();
    };

}
