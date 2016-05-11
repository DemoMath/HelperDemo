package com.demo.wd.helper.bean;

/**
 * Created by Administrator on 2016/5/7.
 */
public class TalkInfo {
    private String title;
    private String content;
    private String time;
    private String type;
    private String discuss;

    public TalkInfo(String title, String content, String time, String type, String discuss) {
        super();
        this.title = title;
        this.content = content;
        this.time = time;
        this.type = type;
        this.discuss = discuss;
    }
    public TalkInfo() {
        super();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDiscuss(String discuss) {
        this.discuss = discuss;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getTime() {
        return time;
    }

    public String getType() {
        return type;
    }

    public String getDiscuss() {
        return discuss;
    }
}
