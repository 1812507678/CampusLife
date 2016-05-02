package com.hainiu.campuslife.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by haijun on 2016/5/1.
 */
public class AlbumCategory extends BmobObject{
    private String albumId;
    private String name;
    private BmobFile icon;
    private String iconUrl;
    private String userId;

    public AlbumCategory() {
    }

    public AlbumCategory(String albumId, String name, BmobFile icon, String iconUrl) {
        this.albumId = albumId;
        this.name = name;
        this.icon = icon;
        this.iconUrl = iconUrl;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BmobFile getIcon() {
        return icon;
    }

    public void setIcon(BmobFile icon) {
        this.icon = icon;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
