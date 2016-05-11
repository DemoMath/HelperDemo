package com.demo.wd.helper.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TabWidget;
import android.widget.TextView;

import com.demo.wd.helper.R;
import com.demo.wd.helper.fragment.main.ExploreFragment;
import com.demo.wd.helper.fragment.main.MeFragment;
import com.demo.wd.helper.fragment.main.NewsFragment;
import com.demo.wd.helper.fragment.main.TalkoverFragment;
import com.demo.wd.helper.ui.widget.MenuDialog;
import com.demo.wd.helper.ui.widget.ToolsDialog;

public class MainActivity extends FragmentActivity implements OnClickListener {

    private FragmentManager mSupportFragmentManager;
    private FragmentTabHost mTabhost;
    private ImageButton mIbPlus;
    private int mCurrentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置状态栏下沉
        putStateBarSink();


        //得到状态栏的高度
        //加载mainView
        View view = initView();
        //设置界面显示
        setContentView(view);

        // 通过id得到控件
        initWidget();
        // 加载actionbar
//        initActionBar();
    }


    private void putStateBarSink() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    @NonNull
    private View initView() {
        int statusBarHeight2 = getStatusBarHeight2(getApplicationContext());
        View view = View.inflate(getApplicationContext(), R.layout.activity_main_new, null);
        View replace_view = view.findViewById(R.id.replace_view);
        ViewGroup.LayoutParams layoutParams = replace_view.getLayoutParams();
        layoutParams.height = statusBarHeight2;
        replace_view.setLayoutParams(layoutParams);
        return view;
    }


    // 推荐的 获得状态栏高度的方式
    private int getStatusBarHeight2(Context context){
        int ret = -1;
        int resourceId = getResources().getIdentifier("status_bar_height","dimen","android");
        if (resourceId > 0){
            ret = context.getResources().getDimensionPixelSize(resourceId);
        }
        return ret;
    }

    /**
     * 加载控件
     */
    private void initWidget() {
        RelativeLayout declare = (RelativeLayout) findViewById(R.id.declare_layout);//声明
        RelativeLayout feedback = (RelativeLayout) findViewById(R.id.feedback_layout);//反馈
        RelativeLayout author = (RelativeLayout) findViewById(R.id.author_layout);//作者
        RelativeLayout look = (RelativeLayout) findViewById(R.id.look_layout);//查看源码
        TextView setting = (TextView) findViewById(R.id.tv_setting);//设置
        TextView back = (TextView) findViewById(R.id.tv_back);//设置

        //中间的加号
        mIbPlus = (ImageButton) findViewById(R.id.ib_plus);

        mSupportFragmentManager = getSupportFragmentManager();
        mTabhost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabhost.setup(this, mSupportFragmentManager, R.id.mainview);

        String name[] = {"综合", "讨论", "", "发现", "我"};
        int icons[] = {R.drawable.tab_icon_new, R.drawable.tab_icon_tweet, 0,
                R.drawable.tab_icon_explore, R.drawable.tab_icon_me};
        Class<?> fragments[] = {NewsFragment.class, TalkoverFragment.class,
                NewsFragment.class, ExploreFragment.class, MeFragment.class};

        for (int i = 0; i < 5; i++) {
            TextView view = (TextView) View.inflate(getApplicationContext(),
                    R.layout.layout_tabhost_item, null);
            view.setText(name[i]);

            view.setCompoundDrawablesWithIntrinsicBounds(0,
                    icons[i], 0, 0);
            if (i == 2) {
                view.setVisibility(View.INVISIBLE);
            }

            mTabhost.addTab(mTabhost.newTabSpec(name[i]).setIndicator(view),
                    fragments[i], null);

        }

        TabWidget tabWidget = mTabhost.getTabWidget();
        tabWidget.setShowDividers(0);

        mIbPlus.setOnClickListener(this);
        declare.setOnClickListener(this);
        feedback.setOnClickListener(this);
        author.setOnClickListener(this);
        look.setOnClickListener(this);
        setting.setOnClickListener(this);
        back.setOnClickListener(this);
    }

/*

    */
/**
     * 设置ActionBar
     *//*

    private void initActionBar() {

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.app_name);

        actionBar.setIcon(R.drawable.drawer_menu_item_background);

        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setHomeButtonEnabled(true);

//		actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
//				R.drawable.ic_drawer, 0, 0);

        DrawerArrowDrawable drawerImage = new DrawerArrowDrawable(getApplicationContext()) {
            @Override
            public boolean isLayoutRtl() {
                return true;
            }
        };

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, drawerImage, 0, 0);

        actionBarDrawerToggle.syncState();

        mDrawerLayout.setDrawerListener(new DrawerListener() {

            @Override
            public void onDrawerStateChanged(int newState) {
                actionBarDrawerToggle.onDrawerStateChanged(newState);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                actionBarDrawerToggle.onDrawerSlide(drawerView, slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                actionBarDrawerToggle.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                actionBarDrawerToggle.onDrawerClosed(drawerView);
            }
        });
    }

    */
/**
     * 当ActionBar按钮点击时
     *//*


    // 当home键被点击的时候调用
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        actionBarDrawerToggle.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }
*/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_plus:
                ToolsDialog toolsDialog = new ToolsDialog(MainActivity.this, R.style.MyDialogTheme);
                toolsDialog.show();
                break;

            case R.id.declare_layout:
                MenuDialog menuDeclareDialog = new MenuDialog(MainActivity.this,R.style.MyDialogTheme);
                menuDeclareDialog.setContentView(R.layout.layout_declare);
                menuDeclareDialog.show();
                break;

            case R.id.feedback_layout:
                MenuDialog menuFeddbackDialog = new MenuDialog(MainActivity.this,R.style.MyDialogTheme);
                menuFeddbackDialog.setContentView(R.layout.layout_feedback);
                menuFeddbackDialog.show();
                break;

            case R.id.author_layout:
                MenuDialog menuAuthorDialog = new MenuDialog(MainActivity.this,R.style.MyDialogTheme);
                menuAuthorDialog.setContentView(R.layout.layout_author);
                menuAuthorDialog.show();
                break;

            case R.id.look_layout:
                MenuDialog menuLookDialog = new MenuDialog(MainActivity.this,R.style.MyDialogTheme);
                menuLookDialog.setContentView(R.layout.layout_looksoucre);
                menuLookDialog.show();
                break;

            case R.id.tv_back:
                break;

            case R.id.tv_setting:
                break;
        }
    }
}
