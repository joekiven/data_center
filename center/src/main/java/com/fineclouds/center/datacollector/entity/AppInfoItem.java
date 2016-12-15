package com.fineclouds.center.datacollector.entity;

import com.google.gson.annotations.SerializedName;

public class AppInfoItem {
    @SerializedName("pkgName")
    private String pkgName;    //应用程序所对应的包名
    @SerializedName("label")
    private String label;
    @SerializedName("appVersion")
    private String appVersion;
    @SerializedName("appVersionCode")
    private String appVersionCode;
    @SerializedName("headInfo")
    private HeadInfo headInfo;
    @SerializedName("local")
    private int local;       //item headitem or listitem

    public AppInfoItem() {
    }

    public HeadInfo getHeadInfo() {
        return headInfo;
    }

    public void setHeadInfo(HeadInfo headInfo) {
        this.headInfo = headInfo;
    }

    public void setAppVersionCode(String appVersionCode) {
        this.appVersionCode = appVersionCode;
    }

    public String getAppVersionCode() {
        return appVersionCode;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getLocal() {
        return local;
    }

    public void setLocal(int local) {
        this.local = local;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }
}