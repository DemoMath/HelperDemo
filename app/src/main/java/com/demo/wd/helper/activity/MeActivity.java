package com.demo.wd.helper.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.demo.wd.helper.R;
import com.demo.wd.helper.factory.MePagerFactory;
import com.demo.wd.helper.fragment.main.MeFragment;
import com.demo.wd.helper.utils.CommonUtils;
import com.demo.wd.helper.utils.SpUtils;

/**
 * Created by Administrator on 2016/5/5.
 */
public class MeActivity extends Activity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        int meKey = getIntent().getIntExtra("meKey", -1);
        FrameLayout content = (FrameLayout) findViewById(R.id.content);
        View detailView = MePagerFactory.createPager(meKey);
        CommonUtils.removeFromParent(detailView);
        content.addView(detailView);

        View replace_view = findViewById(R.id.replace_view);
        CommonUtils.initActivityView(this,replace_view);

        View back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slide_left_in,R.anim.slide_right_out);
            }
        });

        TextView title = (TextView) findViewById(R.id.title);
        switch (meKey) {
            case MeFragment.MINE:
                title.setText("个人信息");
                View rl_qiehuan = findViewById(R.id.rl_qiehuan);
                rl_qiehuan.setOnClickListener(this);
                break;
            case MeFragment.MESSAGE:
                title.setText("我的消息");
                break;
            case MeFragment.QIAODAN:
                title.setText("签到记录");
                break;
            case MeFragment.TEACHER:
                title.setText("我的导师");
                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_qiehuan:
                SpUtils.putParam(CommonUtils.getContext(),"username","");
                Intent intentLogin = new Intent(CommonUtils.getContext(), LoginActivity.class);
                startActivity(intentLogin);
                finishAffinity();
                break;
        }
    }
}
