package com.fineclouds.center.datacollector.utils;

import android.content.Context;



/**
 * Created by ubuntu on 16-8-25.
 */
public class PSPrefUtils {
    public static boolean isFirstOpen(Context context, String column) {
        return !PreferenceUtil.getBooleanPrefrences(context, column,PreferenceUtil.PS_COLLECTOR_FILE);
    }
    public static void setFirstOpen(Context context, String column) {
        PreferenceUtil.setBooleanPrefrences(context,column,true,PreferenceUtil.PS_COLLECTOR_FILE);
    }
    public static long getLastTime(Context context, String column){
        return PreferenceUtil.getLongPrefrences(context, column,PreferenceUtil.PS_COLLECTOR_FILE);
    }
    public static void setLastTime(Context context, String column) {
        PreferenceUtil.setLongPrefrences(context,column, System.currentTimeMillis(),PreferenceUtil.PS_COLLECTOR_FILE);
    }
}
