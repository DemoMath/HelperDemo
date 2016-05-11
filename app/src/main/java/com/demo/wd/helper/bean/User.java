package com.demo.wd.helper.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/5/5.
 */
public class User extends BmobObject {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
