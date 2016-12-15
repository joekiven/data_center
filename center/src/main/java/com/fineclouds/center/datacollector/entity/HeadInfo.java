package com.fineclouds.center.datacollector.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ubuntu on 15-12-9.
 */
public class HeadInfo {
    @SerializedName("packageName")
    private String packageName;
    @SerializedName("userId")
    private String userId;
    @SerializedName("appVersion")
    private String appVersion;
    @SerializedName("local")
    private String local;
    @SerializedName("networkOperator")
    private String networkOperator;
    @SerializedName("appVersionCode")
    private String appVersionCode;
    @SerializedName("mac")
    private String mac;
    @SerializedName("apn")
    private String apn;
    @SerializedName("imei")
    private String imei;
    @SerializedName("imei2")
    private String imei2;
    @SerializedName("imsi")
    private String imsi;
    @SerializedName("imsi2")
    private String imsi2;
    @SerializedName("spn")
    private String spn;
    @SerializedName("spn2")
    private String spn2;
    @SerializedName("mccmnc")
    private String mccmnc;
    @SerializedName("ipAddress")
    private String ipAddress;
    @SerializedName("createTime")
    private String createTime;
    @SerializedName("projectNum")
    private String projectNum;

    public HeadInfo(String packageName, String apn, String appVersion, String appVersionCode, String createTime, String imei2, String imei, String imsi2, String imsi, String ipAddress, String local, String mac, String networkOperator, String spn2, String spn, String userId, String mccmnc, String projectNum) {
        this.packageName=packageName;
        this.apn = apn;
        this.appVersion = appVersion;
        this.appVersionCode = appVersionCode;
        this.createTime = createTime;
        this.imei2 = imei2;
        this.imei = imei;
        this.imsi2 = imsi2;
        this.imsi = imsi;
        this.ipAddress = ipAddress;
        this.local = local;
        this.mac = mac;
        this.networkOperator = networkOperator;
        this.spn2 = spn2;
        this.spn = spn;
        this.userId = userId;
        this.mccmnc=mccmnc;
        this.projectNum=projectNum;
    }

    public HeadInfo() {
    }

    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getApn() {
        return apn;
    }

    public void setApn(String apn) {
        this.apn = apn;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppVersionCode() {
        return appVersionCode;
    }

    public void setAppVersionCode(String appVersionCode) {
        this.appVersionCode = appVersionCode;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getImei2() {
        return imei2;
    }

    public void setImei2(String imei2) {
        this.imei2 = imei2;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getImsi2() {
        return imsi2;
    }

    public void setImsi2(String imsi2) {
        this.imsi2 = imsi2;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getNetworkOperator() {
        return networkOperator;
    }

    public void setNetworkOperator(String networkOperator) {
        this.networkOperator = networkOperator;
    }

    public String getSpn2() {
        return spn2;
    }

    public void setSpn2(String spn2) {
        this.spn2 = spn2;
    }

    public String getSpn() {
        return spn;
    }

    public void setSpn(String spn) {
        this.spn = spn;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMccmnc() {
        return mccmnc;
    }

    public void setMccmnc(String mccmnc) {
        this.mccmnc = mccmnc;
    }
}
