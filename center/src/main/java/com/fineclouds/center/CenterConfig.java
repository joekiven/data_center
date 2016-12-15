package com.fineclouds.center;


import com.fineclouds.center.datacollector.database.CollectionSource;
/**
 * Created by ubuntu on 16-8-29.
 */
public class CenterConfig {
    private static boolean DEBUG_MODE = false;
    private static CollectionSource COLLECTION_SOURCE;
    private static boolean IS_2B=false;
    private static String PROJECT_NUM = "Unspecified";
    private static String DOWNLOAD_APK_NAME =“XXX.APK”

    public static CollectionSource getCollectionSource() {
        return COLLECTION_SOURCE;
    }

    public static void setCollectionSource(CollectionSource collectionSource) {
        CenterConfig.COLLECTION_SOURCE = collectionSource;
    }
    public static String SERVER_BASE_URL = "http://xxx.xxx.com:8080";

    public static boolean is2b() {
        return IS_2B;
    }

    public static void setIs2b(boolean is2b) {
        IS_2B = is2b;
    }

    public static String getServerBaseUrl() {
        return SERVER_BASE_URL;
    }

    public static void setServerBaseUrl(String serverBaseUrl) {
        SERVER_BASE_URL = serverBaseUrl;
    }

    public static boolean isDebugMode() {
        return DEBUG_MODE;
    }

    public static void setDebugMode(boolean debugMode) {
        CenterConfig.DEBUG_MODE = debugMode;
    }

    public static String getProjectNum() {
        return PROJECT_NUM;
    }

    public static void setProjectNum(String projectNum) {
        CenterConfig.PROJECT_NUM = projectNum;
    }
    public static String getDownloadApkName() {
        return DOWNLOAD_APK_NAME;
    }

    public static void setDownloadApkName(String apkName) {
        CenterConfig.DOWNLOAD_APK_NAME = apkName;
    }
}
