package com.demo.wd.helper.base;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.demo.wd.helper.R;
import com.demo.wd.helper.utils.CommonUtils;

import java.util.List;

/**
 * Created by Administrator on 2016/4/25.
 */
public abstract class BasicPager extends FrameLayout {
    //定义4种状态常量
    enum PageState{
        STATE_LOADING(0),
        STATE_SUCCESS(1),
        STATE_ERROR(2),
        STATE_EMPTY(3);

        private int value;
        PageState(int value){
            this.value=value;
        }
        //获取0,1,2,3值
        public int getValue(){
            return value;
        }
    }
    //每个界面的默认状态是加载中
    private PageState mState=PageState.STATE_LOADING;

    private View loadingView;//加载中的view
    private View errorView;//加载失败的view
    private View emptyView;//加载为空的view
    private View successView;//加载成功的view
    public static final int ShowSuccess = 1;

    public BasicPager(Context context) {
        super(context);
        init();
    }

    public BasicPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BasicPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化操作
     * 添加布局
     */
    public void init() {
        //第一步，往contentpage中添加4个状态对应的view
        //保证每添加的一个view都填充父窗体
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);

        //1，正在加载的view
        if (loadingView==null) {
            loadingView=View.inflate(getContext(), R.layout.layout_loading, null);
        }
        //加载view
        addView(loadingView, params);

        //2，加载失败的view
        if (errorView==null) {
            errorView = View.inflate(getContext(), R.layout.layout_error, null);
            TextView btn_reload = (TextView) errorView.findViewById(R.id.error_again);
            btn_reload.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //点击加载按钮后将当前的state，置为正在加载的状态
                    mState=PageState.STATE_LOADING;
                    //根据当前的state,显示对应的view
                    showPage();
                    //加载数据并且刷新UI
                    loadDataAndRefreshPage();
                }
            });
        }

        addView(errorView, params);

        //3,加载空的view
        if (emptyView==null) {
            emptyView = View.inflate(getContext(), R.layout.layout_empty, null);
        }
        addView(emptyView, params);

        //4,添加成功的view，由于成功的页面每个view都不同，所以使用一个抽象的方法
        if (successView==null) {
            successView = createSuccessView();
        }
        //不为null时，添加到view中
        if (successView!=null) {
            addView(successView, params);
        }else{//抛出异常
            throw new IllegalArgumentException("The method createSuccessView() can not return null!");
        }

        //第二步，根据当前的state,显示对应的view
        showPage();

        //第三步，加载数据并且刷新UI
        loadDataAndRefreshPage();

    }


    //加载数据并且刷新UI
    public void loadDataAndRefreshPage() {
        // 开启子线程下载数据
        new Thread(){
            @Override
            public void run() {
                //模拟耗时的操作
                SystemClock.sleep(500);
                //1，得到请求回来的数据
                Object data = loadData();
                //2,根据请求回来的data,获取对应的状态，并赋值给当前的state
                mState = checkData(data);
                //3,根据新的state,刷新page
                CommonUtils.runInMainThread(new Runnable() {
                    @Override
                    public void run() {
                        //在主线程更新UI
                        showPage();
                    }
                });
            }
        }.start();
    }

    /**
     * 根据data判断对应的state
     * @param data
     * @return
     */
    protected PageState checkData(Object data) {
        if (data==null) {
            return PageState.STATE_ERROR;//失败的状态
        } else {
            if (data instanceof List) {
                List list = (List) data;
                if (list.size()==0) {
                    //说明本次请求成功，可惜没有数据了，返回加载为空的状态
                    return PageState.STATE_EMPTY;
                }else{
                    //服务器返回的是集合的数据,返回加载成功的状态
                    return PageState.STATE_SUCCESS;
                }
            } else {
                //就是java bean
                return PageState.STATE_SUCCESS;
            }

        }
    }

    //根据当前的state,显示对应的view
    private void showPage() {
        // 首先把四个状态的view都隐藏了
        loadingView.setVisibility(View.INVISIBLE);
        successView.setVisibility(View.INVISIBLE);
        errorView.setVisibility(View.INVISIBLE);
        emptyView.setVisibility(View.INVISIBLE);
        //根据状态的返回值，显示对应的view
        switch (mState.getValue()) {
            case 0://加载中
                loadingView.setVisibility(View.VISIBLE);
                break;
            case 1://加载成功
                successView.setVisibility(View.VISIBLE);
                break;
            case 2://加载失败
                errorView.setVisibility(View.VISIBLE);
                break;
            case 3://加载为空
                emptyView.setVisibility(View.VISIBLE);
                break;
        }
    }

    /**
     * 创建成功的view，由于每个界面的view都不同，所以有每个界面自己实现和提供
     * @return
     */
    public abstract View createSuccessView();

    /**
     * 加载数据，由于我们只关心请求回来的数据，并不关心加载数据的过程，需要每个子类实现此方法，然后自己做加载数据的过程
     * @return
     */
    public abstract Object loadData();

}
