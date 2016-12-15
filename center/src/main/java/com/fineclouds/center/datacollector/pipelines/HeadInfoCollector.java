package com.fineclouds.center.datacollector.pipelines;

import android.content.Context;

import com.fineclouds.center.CenterConfig;
import com.fineclouds.center.datacollector.entity.HeadInfo;

import java.util.Locale;


/**
 * Created by ubuntu on 16-8-16.
 */
public class HeadInfoCollector {
    private static final String TOC_REMOVED = "2C:Removed";
    public static HeadInfo getHeadInfo(Context context){
        HeadInfo headInfo = new HeadInfo();
        headInfo.setPackageName(context.getPackageName());
        headInfo.setAppVersion(HeadInfoUtils.getAppVersionName(context));
        headInfo.setAppVersionCode(HeadInfoUtils.getAppVersionCode(context));
        headInfo.setLocal(Locale.getDefault().toString());
        headInfo.setImei(HeadInfoUtils.getImei(context));
        headInfo.setIpAddress(HeadInfoUtils.getLocalIpAddress());
        headInfo.setCreateTime(String.valueOf(System.currentTimeMillis()));
        headInfo.setImei2("");
        headInfo.setImsi2("");
        headInfo.setSpn2("");
        headInfo.setUserId(HeadInfoUtils.uuid(context));
        headInfo.setProjectNum(CenterConfig.getProjectNum());
        if (CenterConfig.is2b()){
            headInfo.setMac(HeadInfoUtils.getMacAddr());
            headInfo.setNetworkOperator(HeadInfoUtils.getNetworkOperatorName(context));
            headInfo.setSpn(HeadInfoUtils.getSPN(context));
            headInfo.setApn(String.valueOf(HeadInfoUtils.getNetworkState(context)));
            headInfo.setImsi(HeadInfoUtils.getImsi(context));
            headInfo.setMccmnc(HeadInfoUtils.getOperatorMccMnc(context));
        }else {
            headInfo.setMac(TOC_REMOVED);
            headInfo.setNetworkOperator(TOC_REMOVED);
            headInfo.setSpn(TOC_REMOVED);
            headInfo.setApn(TOC_REMOVED);
            headInfo.setImsi(TOC_REMOVED);
            headInfo.setMccmnc(TOC_REMOVED);
        }
        return headInfo;
    }
}
