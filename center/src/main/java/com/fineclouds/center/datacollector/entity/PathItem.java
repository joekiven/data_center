package com.fineclouds.center.datacollector.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ubuntu on 16-8-23.
 */
public class PathItem {
    @SerializedName("actvtyName")
    private String actvtyName;
    @SerializedName("lastActvty")
    private String lastActvty;
    @SerializedName("stayTime")
    private String stayTime;
    @SerializedName("createTime")
    private long createTime;
//    @SerializedName("headInfo")
//    private HeadInfo headInfo;

    public PathItem() {
    }

    public String getActvtyName() {
        return actvtyName;
    }

    public void setActvtyName(String actvtyName) {
        this.actvtyName = actvtyName;
    }

//    public HeadInfo getHeadInfo() {
//        return headInfo;
//    }

//    public void setHeadInfo(HeadInfo headInfo) {
//        this.headInfo = headInfo;
//    }
//
    public String getLastActvty() {
        return lastActvty;
    }

    public void setLastActvty(String lastActvty) {
        this.lastActvty = lastActvty;
    }

    public String getStayTime() {
        return stayTime;
    }

    public void setStayTime(String stayTime) {
        this.stayTime = stayTime;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
