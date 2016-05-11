package com.demo.wd.helper.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.wd.helper.R;
import com.demo.wd.helper.bean.User;
import com.demo.wd.helper.factory.ExplorePagerFactory;
import com.demo.wd.helper.factory.NewsPagerFactory;
import com.demo.wd.helper.pager.explore.FindPager;
import com.demo.wd.helper.pager.news.HomePager;
import com.demo.wd.helper.utils.CommonUtils;
import com.demo.wd.helper.utils.MD5Util;
import com.demo.wd.helper.utils.SpUtils;
import com.demo.wd.helper.utils.StringUtils;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by Administrator on 2016/5/5.
 */
public class LoginActivity extends Activity implements View.OnClickListener{

    private View mLogin;
    private EditText mEt_username;
    private EditText mEt_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        View replace_view = findViewById(R.id.replace_view);
        CommonUtils.initActivityView(this,replace_view);


        View back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToMain();
            }

        });

        TextView title = (TextView) findViewById(R.id.title);
        title.setText("登录");

        // 初始化 Bmob SDK
        // 使用时请将第二个参数Application ID替换成你在Bmob服务器端创建的Application ID
        Bmob.initialize(this, "e7149a10595beb12b3472508ef167556");

        mLogin = findViewById(R.id.login);
        mEt_username = (EditText) findViewById(R.id.et_username);
        mEt_password = (EditText) findViewById(R.id.et_password);
        View btn_confirm = findViewById(R.id.btn_confirm);
        View btn_register = findViewById(R.id.btn_register);

        btn_confirm.setOnClickListener(this);
        btn_register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm://登录确定
                final String username = mEt_username.getText().toString().trim();
                final String password = mEt_password.getText().toString().trim();

                if (StringUtils.isEmpty(username)||StringUtils.isEmpty(password)) {
                    Toast.makeText(CommonUtils.getContext(),"用户名或者密码不能为空！",Toast.LENGTH_SHORT).show();
                    return;
                }
                //登录操作
                //加密密码
                final String newPassword = MD5Util.Md5(MD5Util.Md5(MD5Util.Md5(password))).substring(0,15);

                BmobQuery<User> query = new BmobQuery<User>();
                query.findObjects(this, new FindListener<User>() {
                    @Override
                    public void onSuccess(List<User> object) {
                        for (User us: object) {
                            if (us.getUsername().equals(username)) {
                                if (us.getPassword().equals(newPassword)) {
                                    Toast.makeText(CommonUtils.getContext(),"登陆成功",Toast.LENGTH_SHORT).show();

                                    SpUtils.putParam(CommonUtils.getContext(),"username",username);

                                    moveToMain();
                                }else{
                                    Toast.makeText(CommonUtils.getContext(),"密码错误",Toast.LENGTH_SHORT).show();
                                }
                                return;
                            }
                        }
                        Toast.makeText(CommonUtils.getContext(),"用户不存在",Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onError(int code, String msg) {
                        Toast.makeText(CommonUtils.getContext(),"网络错误！",Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.btn_register://注册
                Intent registerIntent = new Intent(CommonUtils.getContext(),RegisterActivity.class);
                startActivity(registerIntent);
                finish();
                break;

        }
    }

    private void moveToMain() {
        Intent mainIntent = new Intent(CommonUtils.getContext(),MainActivity.class);
        startActivity(mainIntent);
        finish();
        overridePendingTransition(R.anim.slide_left_in,R.anim.slide_right_out);
        HomePager homePager = (HomePager) NewsPagerFactory.createPager(0);
        homePager.startRoll();
        FindPager findPager = (FindPager) ExplorePagerFactory.createPager(1);
        findPager.startChange();
    }
}
