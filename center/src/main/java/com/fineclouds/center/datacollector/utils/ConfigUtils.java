package com.fineclouds.center.datacollector.utils;

/**
 * Created by ubuntu on 16-8-29.
 */
public class ConfigUtils {
    public static int getProjectCode(String projectNum){
        switch (projectNum){
            case "google_test":
                return 0;
            case "FINEOS_TEST":
                return 1;
            case "google_play":
                return 2;
            case "MMX_001":
                return 3;
            case "SYM_001":
                return 4;
            case "BLU_001":
                return 5;
            default:
                return -1;
        }
    }
}
