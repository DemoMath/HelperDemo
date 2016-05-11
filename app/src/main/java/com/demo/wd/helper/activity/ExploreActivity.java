package com.demo.wd.helper.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.demo.wd.helper.R;
import com.demo.wd.helper.factory.ExplorePagerFactory;
import com.demo.wd.helper.fragment.main.ExploreFragment;
import com.demo.wd.helper.utils.CommonUtils;

/**
 * Created by Administrator on 2016/5/5.
 */
public class ExploreActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);

        View replace_view = findViewById(R.id.replace_view);
        CommonUtils.initActivityView(this,replace_view);

        int exploreKey = getIntent().getIntExtra("exploreKey", -1);
        View back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_left_in,R.anim.slide_right_out);
            }
        });
        TextView title = (TextView) findViewById(R.id.title);
        switch (exploreKey) {
            case ExploreFragment.FRIENDSGROUP:
                title.setText("朋友圈");
                break;
            case ExploreFragment.FIND:
                title.setText("找人");
                break;
            case ExploreFragment.ANSWER:
                title.setText("答疑");
                break;
        }
        FrameLayout content = (FrameLayout) findViewById(R.id.content);
        View detailView = ExplorePagerFactory.createPager(exploreKey);
        CommonUtils.removeFromParent(detailView);
        content.addView(detailView);
    }
}
