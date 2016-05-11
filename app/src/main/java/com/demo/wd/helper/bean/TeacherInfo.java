package com.demo.wd.helper.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by Administrator on 2016/5/5.
 */
public class TeacherInfo extends BmobObject {
    private String name;
    private String desc;

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
