package com.fineclouds.center.datacollector.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ubuntu on 16-8-18.
 */
public class AppInfo {
    @SerializedName("appInfos")
    private List<AppInfoItem> appInfos;
    @SerializedName("headInfo")
    private HeadInfo headInfo;

    public AppInfo() {
    }

    public List<AppInfoItem> getAppInfo() {
        return appInfos;
    }

    public void setAppInfo(List<AppInfoItem> appInfo) {
        this.appInfos = appInfo;
    }

    public HeadInfo getHeadInfo() {
        return headInfo;
    }

    public void setHeadInfo(HeadInfo headInfo) {
        this.headInfo = headInfo;
    }
}
