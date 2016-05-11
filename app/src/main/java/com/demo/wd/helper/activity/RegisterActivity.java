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
import com.demo.wd.helper.utils.CommonUtils;
import com.demo.wd.helper.utils.MD5Util;
import com.demo.wd.helper.utils.StringUtils;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by Administrator on 2016/5/5.
 */
public class RegisterActivity extends Activity implements View.OnClickListener{

    private View mRegister;
    private EditText mEt_register_username;
    private EditText mEt_register_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        View replace_view = findViewById(R.id.replace_view);
        CommonUtils.initActivityView(this,replace_view);

        View back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToLogin();
            }
        });

        TextView title = (TextView) findViewById(R.id.title);
        title.setText("注册");


        // 初始化 Bmob SDK
        // 使用时请将第二个参数Application ID替换成你在Bmob服务器端创建的Application ID
        Bmob.initialize(this, "e7149a10595beb12b3472508ef167556");

        mRegister = findViewById(R.id.register);
        mEt_register_username = (EditText) findViewById(R.id.et_register_username);
        mEt_register_password = (EditText) findViewById(R.id.et_register_password);
        View btn_ok = findViewById(R.id.btn_ok);
        View btn_cancel = findViewById(R.id.btn_cancel);

        btn_ok.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ok://注册确定
                final String registerUsername = mEt_register_username.getText().toString().trim();
                String registerPassword = mEt_register_password.getText().toString().trim();
                if (StringUtils.isEmpty(registerUsername)||StringUtils.isEmpty(registerPassword)) {
                    Toast.makeText(CommonUtils.getContext(),"用户名或者密码不能为空！",Toast.LENGTH_SHORT).show();
                    return;
                }
                //加密密码
                String newRegisterPassword = MD5Util.Md5(MD5Util.Md5(MD5Util.Md5(registerPassword))).substring(0,15);
                //注册操作
                User mRegisterUser = new User();
                mRegisterUser.setUsername(registerUsername);
                mRegisterUser.setPassword(newRegisterPassword);
                mRegisterUser.save(this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        moveToLogin();
                    }
                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(CommonUtils.getContext(),"账号已存在",Toast.LENGTH_SHORT).show();
                    }
                });
                break;

            case R.id.btn_cancel://取消
                moveToLogin();
                break;
        }
    }
    public void moveToLogin() {
        Intent loginIntent = new Intent(CommonUtils.getContext(),LoginActivity.class);
        startActivity(loginIntent);
        finish();
        overridePendingTransition(R.anim.slide_left_in,R.anim.slide_right_out);
    }

}
