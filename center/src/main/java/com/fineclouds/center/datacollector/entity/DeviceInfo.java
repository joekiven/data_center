package com.fineclouds.center.datacollector.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ubuntu on 16-8-16.
 */
public class DeviceInfo {
    @SerializedName("deviceType")
    private String deviceType;
    @SerializedName("platform")
    private String platform;
    @SerializedName("deviceModel")
    private String deviceModel;
    @SerializedName("sdkCode")
    private String sdkCode;
    @SerializedName("sdkName")
    private String sdkName;
    @SerializedName("ramSize")
    private String ramSize;
    @SerializedName("storageSize")
    private String storageSize;
    @SerializedName("screenWidth")
    private String screenWidth;
    @SerializedName("screenHeight")
    private String screenHeight;
    @SerializedName("fingerPrint")
    private String fingerPrint;
    @SerializedName("headInfo")
    private HeadInfo headInfo;
    @SerializedName("fingerChip")
    private String fingerChip;

    public DeviceInfo() {
    }

    public String getFingerChip() {
        return fingerChip;
    }

    public void setFingerChip(String fingerChip) {
        this.fingerChip = fingerChip;
    }

    public HeadInfo getHeadInfo() {
        return headInfo;
    }

    public void setHeadInfo(HeadInfo headInfo) {
        this.headInfo = headInfo;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getRamSize() {
        return ramSize;
    }

    public void setRamSize(String ramSize) {
        this.ramSize = ramSize;
    }

    public String getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(String screenHeight) {
        this.screenHeight = screenHeight;
    }

    public String getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(String screenWidth) {
        this.screenWidth = screenWidth;
    }

    public String getSdkCode() {
        return sdkCode;
    }

    public void setSdkCode(String sdkCode) {
        this.sdkCode = sdkCode;
    }

    public String getSdkName() {
        return sdkName;
    }

    public void setSdkName(String sdkName) {
        this.sdkName = sdkName;
    }

    public String getStorageSize() {
        return storageSize;
    }

    public void setStorageSize(String storageSize) {
        this.storageSize = storageSize;
    }
}
