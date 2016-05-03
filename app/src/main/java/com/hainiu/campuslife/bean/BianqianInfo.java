package com.hainiu.campuslife.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by haijun on 2016/5/3.
 */
public class BianqianInfo extends BmobObject {
    private String bianqianId;
    private String userId;
    private String title;
    private String content;
    private String date;


    public String getBianqianId() {
        return bianqianId;
    }

    public void setBianqianId(String bianqianId) {
        this.bianqianId = bianqianId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
