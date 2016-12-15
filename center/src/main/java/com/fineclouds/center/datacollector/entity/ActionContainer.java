package com.fineclouds.center.datacollector.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ubuntu on 16-8-25.
 */
public class ActionContainer {
    @SerializedName("actionList")
    private List<String> actionList;
    @SerializedName("headInfo")
    private HeadInfo headInfo;

    public ActionContainer() {
    }

    public List<String> getActionList() {
        return actionList;
    }

    public void setActionList(List<String> actionList) {
        this.actionList = actionList;
    }

    public HeadInfo getHeadInfo() {
        return headInfo;
    }

    public void setHeadInfo(HeadInfo headInfo) {
        this.headInfo = headInfo;
    }
}
