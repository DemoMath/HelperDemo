package com.demo.wd.helper.bean;

/**
 * Created by Administrator on 2016/5/6.
 */
public class ActiveImageInfo {
    private Integer imgUrl;
    private String des;

    public ActiveImageInfo(Integer imgUrl, String des) {
        super();
        this.imgUrl = imgUrl;
        this.des = des;
    }

    public void setImgUrl(Integer imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Integer getImgUrl() {
        return imgUrl;
    }

    public String getDes() {
        return des;
    }
}
