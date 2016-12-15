package com.fineclouds.center.datacollector.pipelines;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.fineclouds.center.datacollector.entity.AppInfoItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by ubuntu on 16-8-17.
 */
public class AppInfoUtils {
    public static final int FILTER_ALL_APP = 0;
    public static final int FILTER_SYSTEM_APP = 1;
    public static final int FILTER_THIRD_APP = 2;
    public static final int FILTER_SDCARD_APP = 3;

    public synchronized static List<AppInfoItem> getAppList(int filter, Context context) {
        PackageManager mPm = context.getPackageManager();
        List<ApplicationInfo> listAppcations = mPm
                .getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
        Collections.sort(listAppcations,
                new ApplicationInfo.DisplayNameComparator(mPm));// 排序
        List<AppInfoItem> appInfoItems = new ArrayList<AppInfoItem>(); // 保存过滤查到的AppInfo
        switch (filter) {
            case FILTER_ALL_APP:
                appInfoItems.clear();
                for (ApplicationInfo app : listAppcations) {
                    appInfoItems.add(getAppInfo(app,mPm,filter));
                }
                break;
            case FILTER_SYSTEM_APP:
                appInfoItems.clear();
                for (ApplicationInfo app : listAppcations) {
                    if ((app.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                        appInfoItems.add(getAppInfo(app,mPm,filter));
                    }
                }
                break;
            case FILTER_THIRD_APP:
                appInfoItems.clear();

                for (ApplicationInfo app : listAppcations) {
                    if ((app.flags & ApplicationInfo.FLAG_SYSTEM) <= 0
                            || (app.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
                        appInfoItems.add(getAppInfo(app,mPm,filter));
                    }
                }
                break;
            case FILTER_SDCARD_APP:
                appInfoItems.clear();
                for (ApplicationInfo app : listAppcations) {
                    if ((app.flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) != 0) {
                        appInfoItems.add(getAppInfo(app,mPm,filter));
                    }
                }
                break;
        }
        return appInfoItems;
    }

    private static AppInfoItem getAppInfo(ApplicationInfo app, PackageManager mPm, int filter) {
        AppInfoItem appInfoItem = new AppInfoItem();
        appInfoItem.setPkgName(app.packageName);
        appInfoItem.setLabel(app.loadLabel(mPm).toString());
        try {
            PackageInfo packageInfo = mPm.getPackageInfo(app.packageName, 0);
            appInfoItem.setAppVersion(packageInfo.versionName);
            appInfoItem.setAppVersion(String.valueOf(packageInfo.versionCode));
            switch (filter){
                case FILTER_ALL_APP:
                    appInfoItem.setLocal(FILTER_ALL_APP);
                    break;
                case FILTER_SYSTEM_APP:
                    appInfoItem.setLocal(FILTER_SYSTEM_APP);
                    break;
                case FILTER_THIRD_APP:
                    appInfoItem.setLocal(FILTER_THIRD_APP);
                    break;
                case FILTER_SDCARD_APP:
                    appInfoItem.setLocal(FILTER_SDCARD_APP);
                    break;
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appInfoItem;
    }
}
