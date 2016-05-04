package com.hainiu.campuslife.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by haijun on 2016/5/4.
 */
public class RemindAndTask extends BmobObject {
    private String remindorTaskId;
    private String userId;
    private String date;
    private String content;
    private String type;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemindorTaskId() {
        return remindorTaskId;
    }

    public void setRemindorTaskId(String remindorTaskId) {
        this.remindorTaskId = remindorTaskId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
