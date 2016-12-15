package com.fineclouds.center.datacollector.entity;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Created by ubuntu on 16-8-22.
 */
public class ActionItem {
    @SerializedName("eventId")
    private String eventId;
    @SerializedName("eventContent")
    private Map<String,String> eventContent;
    @SerializedName("createTime")
    private long createTime;
//    @SerializedName("headInfo")
//    private HeadInfo headInfo;

    public ActionItem() {
    }

//    public HeadInfo getHeadInfo() {
//        return headInfo;
//    }
//
//    public void setHeadInfo(HeadInfo headInfo) {
//        this.headInfo = headInfo;
//    }

    public Map<String, String> getEventContent() {
        return eventContent;
    }

    public void setEventContent(Map<String, String> eventContent) {
        this.eventContent = eventContent;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
