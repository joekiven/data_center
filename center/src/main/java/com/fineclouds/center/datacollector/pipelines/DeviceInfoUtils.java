package com.fineclouds.center.datacollector.pipelines;

import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.app.ActivityCompat;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.samsung.android.sdk.SsdkUnsupportedException;
import com.samsung.android.sdk.pass.Spass;

import java.io.File;

/**
 * Created by ubuntu on 16-8-16.
 */
public class DeviceInfoUtils {
    public static final String SUPPORT_FP = "1";
    public static final String UNSUPPORT_FP = "0";
    public static final String NO_PERMISSION_FP = "-1";

    private static int TABLET = 0;
    private static int PHONE = 1;

    private static final String MA_SENSOR = "/dev/madev0";
    private static final String SILEAD_SENSOR = "/dev/silead_fp_dev";
    private static boolean isFeatureEnabled_fingerprint = false;
    private static final String UNKNOW_CHIP = "0";
    private static final String MA_CHIP = "1";
    private static final String SILEAD_CHIP = "2";
    private static final String SAMSUNG_CHIP = "3";

    public static String getDeviceType(Context context) {
        if ((context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE) return String.valueOf(TABLET);
        else return String.valueOf(PHONE);
    }

    public static String getPlatform() {
        if (Build.VERSION.RELEASE == null) {
            return "";
        } else return Build.VERSION.RELEASE;
    }

    public static String getDeviceModel() {
        if (Build.MODEL == null) {
            return "";
        } else return Build.MODEL;
    }

    public static String getAndroidSDKcode() {
        return String.valueOf(Build.VERSION.SDK_INT);
    }

    public static String getAndroidSDKname() {
        if (Build.VERSION.SDK == null) {
            return "";
        } else return String.valueOf(Build.VERSION.SDK);
    }

    public static String getRAMsize(Context context) {
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        long availableMegs = mi.totalMem / 1000000;
        return String.valueOf(availableMegs);
    }

    public static String getStorageSize() {
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        long bytesAvailable = (long) stat.getBlockSize() * (long) stat.getBlockCount();
        long megAvailable = bytesAvailable / 1000000;
        return String.valueOf(megAvailable);
    }

    public static String getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        int deviceWidth = displayMetrics.widthPixels;
        return String.valueOf(deviceWidth);
    }

    public static String getScreenHeight(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        int deviceHeight = displayMetrics.heightPixels;
        return String.valueOf(deviceHeight);
    }

    public static String isSupportFP(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            FingerprintManager mFingerprintManager = (FingerprintManager) context.getSystemService(Context.FINGERPRINT_SERVICE);
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return NO_PERMISSION_FP;
            }
            if (mFingerprintManager.isHardwareDetected()) {
                return SUPPORT_FP;
            }
        } else {
            if (isMASensorSupport()) {
                return SUPPORT_FP;
            } else if (isSileadSensorSupport()) {
                return SUPPORT_FP;
            }
//            else if(isGoodixSensorSupport()) {
//                return "1";
//            }
            else if (isSamsungSensorSupport(context)) {
                return SUPPORT_FP;
            }
        }
        return UNSUPPORT_FP;
    }

    private static boolean isSileadSensorSupport() {
        File sileadFile = new File(SILEAD_SENSOR);
        if (sileadFile.exists()) {
            return true;
        }
        return false;
    }

    private static boolean isMASensorSupport() {
        File MAFile = new File(MA_SENSOR);
        return MAFile.exists();
    }

    //    private static boolean isGoodixSensorSupport(){
//        boolean isSupport = false;
//        GoodixFingerManager goodixFpManager = GoodixFingerManager.getFpManager();
//        if(goodixFpManager != null) {
//            if(goodixFpManager.isServiceExist()) {
//                isSupport = true;
//            }
//        }
//
//        return isSupport;
//    }
    private static boolean isSamsungSensorSupport(Context context) {
        boolean isSupport = true;
        Spass mSpass = new Spass();
        try {
            mSpass.initialize(context);
        } catch (SsdkUnsupportedException | UnsupportedOperationException | SecurityException e) {
            isSupport = false;
        }

        if (isSupport) {
            isFeatureEnabled_fingerprint = mSpass.isFeatureEnabled(Spass.DEVICE_FINGERPRINT);
            if (!isFeatureEnabled_fingerprint) {
                isSupport = false;
            }
        }
        return isSupport;
    }

    public static String getFingerChip(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            FingerprintManager mFingerprintManager = (FingerprintManager) context.getSystemService(Context.FINGERPRINT_SERVICE);
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return UNKNOW_CHIP;
            }
            if (mFingerprintManager.isHardwareDetected()) {
                return UNKNOW_CHIP;
            }
        }else{
            if(isMASensorSupport()){
                return MA_CHIP;
            }else if(isSileadSensorSupport()){
                return SILEAD_CHIP;
            }
//            else if(isGoodixSensorSupport()) {
//                return "1";
//            }
            else if(isSamsungSensorSupport(context)){
                return SAMSUNG_CHIP;
            }
        }
        return UNKNOW_CHIP;
    }
}
