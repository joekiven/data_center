package com.fineclouds.center.datacollector.pipelines;

import android.content.Context;

import com.fineclouds.center.CenterConfig;
import com.fineclouds.center.datacollector.entity.DeviceInfo;


/**
 * Created by ubuntu on 16-8-16.
 */
public class DeviceInfoCollector {
    private static final String TOC_REMOVED = "2C:Removed";
    public static DeviceInfo getDeviceInfo(Context context){
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setDeviceModel(DeviceInfoUtils.getDeviceModel());
        deviceInfo.setDeviceType(DeviceInfoUtils.getDeviceType(context));
        deviceInfo.setPlatform(DeviceInfoUtils.getPlatform());
        deviceInfo.setSdkCode(DeviceInfoUtils.getAndroidSDKcode());
        deviceInfo.setSdkName(DeviceInfoUtils.getAndroidSDKname());
        deviceInfo.setScreenWidth(DeviceInfoUtils.getScreenWidth(context));
        deviceInfo.setScreenHeight(DeviceInfoUtils.getScreenHeight(context));
        deviceInfo.setFingerPrint(DeviceInfoUtils.isSupportFP(context));
        if (CenterConfig.is2b()){
            deviceInfo.setFingerChip(DeviceInfoUtils.getFingerChip(context));
            deviceInfo.setRamSize(DeviceInfoUtils.getRAMsize(context));
            deviceInfo.setStorageSize(DeviceInfoUtils.getStorageSize());
        }else {
            deviceInfo.setRamSize(TOC_REMOVED);
            deviceInfo.setStorageSize(TOC_REMOVED);
            deviceInfo.setFingerChip(TOC_REMOVED);

        }
        return deviceInfo;
    }
}
