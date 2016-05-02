package com.hainiu.campuslife.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by haijun on 2016/5/2.
 */
public class AlbumPictureTimeList extends BmobObject {
    private String picturetime;
    private int count;
    private String type; //所属相册
    private String userId; //所属相册

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPicturetime() {
        return picturetime;
    }

    public void setPicturetime(String picturetime) {
        this.picturetime = picturetime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
