package com.fineclouds.center.datacollector.pipelines;


import android.content.Context;

import com.fineclouds.center.datacollector.entity.AppInfoItem;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ubuntu on 16-8-16.
 */
public class AppInfoCollector {
    public static final int FILTER_ALL_APP = 0;
    public static final int FILTER_SYSTEM_APP = 1;
    public static final int FILTER_THIRD_APP = 2;
    public static final int FILTER_SDCARD_APP = 3;
    public static List<AppInfoItem> getAppInfo(Context context){
        List<AppInfoItem> appInfoItemList = new ArrayList<>();
        addArraylist(appInfoItemList,AppInfoUtils.getAppList(FILTER_THIRD_APP,context));
        addArraylist(appInfoItemList,AppInfoUtils.getAppList(FILTER_SDCARD_APP,context));
        return appInfoItemList;
    }
    private static void addArraylist(List<AppInfoItem> masterList, List<AppInfoItem> addList){
        for (AppInfoItem appInfoItem : addList) {
            masterList.add(appInfoItem);
        }
    }
}
