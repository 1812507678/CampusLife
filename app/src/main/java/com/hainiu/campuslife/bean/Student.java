package com.hainiu.campuslife.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by haijun on 2016/5/1.
 */
public class Student extends BmobObject {
    private String id;
    private String username;
    private String password;
    private String phone;
    private String school;
    private String major;
    private String sex;
    private String iconUrl;
    private BmobFile icon;

    public Student() {
    }

    public Student(String id, String username, String password, String phone, String school,
                   String major, String sex) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.school = school;
        this.major = major;
        this.sex = sex;
    }

    public Student(String id, String username, String password, String phone, String school, String major, String sex, String iconUrl) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.school = school;
        this.major = major;
        this.sex = sex;
        this.iconUrl = iconUrl;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public BmobFile getIcon() {
        return icon;
    }

    public void setIcon(BmobFile icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
