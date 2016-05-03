package com.hainiu.campuslife.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by haijun on 2016/5/3.
 */
public class JinkuCategory extends BmobObject {
    private String categroyId;
    private String name;
    private int totalAmount;
    private int BlanceAmount;
    private String userId;

    public String getCategroyId() {
        return categroyId;
    }

    public void setCategroyId(String categroyId) {
        this.categroyId = categroyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getBlanceAmount() {
        return BlanceAmount;
    }

    public void setBlanceAmount(int blanceAmount) {
        BlanceAmount = blanceAmount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
