package com.hainiu.campuslife.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by haijun on 2016/5/3.
 */
public class KinkuPayDetial extends BmobObject  {
    private String payId;
    private String blongToJinku;
    private String payData;
    private int payCount;
    private String paySubject;
    private String userId;

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getBlongToJinku() {
        return blongToJinku;
    }

    public void setBlongToJinku(String blongToJinku) {
        this.blongToJinku = blongToJinku;
    }

    public String getPayData() {
        return payData;
    }

    public void setPayData(String payData) {
        this.payData = payData;
    }

    public int getPayCount() {
        return payCount;
    }

    public void setPayCount(int payCount) {
        this.payCount = payCount;
    }

    public String getPaySubject() {
        return paySubject;
    }

    public void setPaySubject(String paySubject) {
        this.paySubject = paySubject;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
