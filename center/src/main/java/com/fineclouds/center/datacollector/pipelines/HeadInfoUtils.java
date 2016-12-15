package com.fineclouds.center.datacollector.pipelines;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

/**
 * Created by ubuntu on 16-8-11.
 */
public class HeadInfoUtils {
    private static final String TAG = "HeadInfoUtils";
    //没有网络连接
    public static final int NETWORN_NONE = 0;
    //wifi连接
    public static final int NETWORN_WIFI = 1;
    //手机网络数据连接类型
    public static final int NETWORN_2G = 2;
    public static final int NETWORN_3G = 3;
    public static final int NETWORN_4G = 4;
    public static final int NETWORN_MOBILE = 5;
    public static final String EMPTY_MAC = "";
    public static final String ERROR_MAC = "02:00:00:00:00:00";

    public static final int NO_PERMISSION = -1;

    private static String sID = null;
    private static final String INSTALL_ID = "INSTALL_ID";
    private static final String NO_MIDEA = "NO_MIDEA.noMedia";

    //<uses-permission android:name="android.permission.INTERNET" />
    public static String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return EMPTY_MAC;
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(Integer.toHexString(b & 0xFF)).append(":");
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ERROR_MAC;
    }

    //    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    //　same as imei.
    public String getUniqueID(Context context) {
        String myAndroidDeviceId;
        TelephonyManager mTelephony = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (mTelephony.getDeviceId() != null) {
            myAndroidDeviceId = mTelephony.getDeviceId();
        } else {
            myAndroidDeviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        }
        return myAndroidDeviceId;
    }

    //    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    public static String getSPN(Context context) {
        if (!checkReadPhoneStatePermission(context)) {
            return String.valueOf(NO_PERMISSION);
        }
        TelephonyManager telephonyManager = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE));
        String operator =  telephonyManager.getSimOperatorName();
        if (operator==null) operator="";
        return operator;
    }

    //    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    public static String getNetworkOperatorName(Context context) {
        if (!checkReadPhoneStatePermission(context)){
            return String.valueOf(NO_PERMISSION);
        }
        TelephonyManager telephonyManager = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE));
        String operator = telephonyManager.getNetworkOperatorName();
        if (operator==null) operator="";
        return operator;
    }

    //    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    public static String getOperatorMccMnc(Context context) {
        if (!checkReadPhoneStatePermission(context)) {
            return String.valueOf(NO_PERMISSION);
        }
        TelephonyManager telephonyManager = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE));
        String mccmnc= telephonyManager.getNetworkOperator();
        if (mccmnc==null) mccmnc="";
        return mccmnc;
    }

    // <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
    public static int getNetworkState(Context context) {
//获取系统的网络服务
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//如果当前没有网络
        if (null == connManager)
            return NETWORN_NONE;
//获取当前网络类型，如果为空，返回无网络
        NetworkInfo activeNetInfo = connManager.getActiveNetworkInfo();
        if (activeNetInfo == null || !activeNetInfo.isAvailable()) {
            return NETWORN_NONE;
        }
// 判断是不是连接的是不是wifi
        NetworkInfo wifiInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (null != wifiInfo) {
            NetworkInfo.State state = wifiInfo.getState();
            if (null != state)
                if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
                    return NETWORN_WIFI;
                }
        }
// 如果不是wifi，则判断当前连接的是运营商的哪种网络2g、3g、4g等
        NetworkInfo networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (null != networkInfo) {
            NetworkInfo.State state = networkInfo.getState();
            String strSubTypeName = networkInfo.getSubtypeName();
            if (null != state)
                if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
                    switch (activeNetInfo.getSubtype()) {
//如果是2g类型
                        case TelephonyManager.NETWORK_TYPE_GPRS: // 联通2g
                        case TelephonyManager.NETWORK_TYPE_CDMA: // 电信2g
                        case TelephonyManager.NETWORK_TYPE_EDGE: // 移动2g
                        case TelephonyManager.NETWORK_TYPE_1xRTT:
                        case TelephonyManager.NETWORK_TYPE_IDEN:
                            return NETWORN_2G;
//如果是3g类型
                        case TelephonyManager.NETWORK_TYPE_EVDO_A: // 电信3g
                        case TelephonyManager.NETWORK_TYPE_UMTS:
                        case TelephonyManager.NETWORK_TYPE_EVDO_0:
                        case TelephonyManager.NETWORK_TYPE_HSDPA:
                        case TelephonyManager.NETWORK_TYPE_HSUPA:
                        case TelephonyManager.NETWORK_TYPE_HSPA:
                        case TelephonyManager.NETWORK_TYPE_EVDO_B:
                        case TelephonyManager.NETWORK_TYPE_EHRPD:
                        case TelephonyManager.NETWORK_TYPE_HSPAP:
                            return NETWORN_3G;
//如果是4g类型
                        case TelephonyManager.NETWORK_TYPE_LTE:
                            return NETWORN_4G;
                        default:
//中国移动 联通 电信 三种3G制式
//                            if (strSubTypeName.equalsIgnoreCase("TD-SCDMA") || strSubTypeName.equalsIgnoreCase("WCDMA") || strSubTypeName.equalsIgnoreCase("CDMA2000")) {
//                                return NETWORN_3G;
//                            } else {
//                                return NETWORN_MOBILE;
//                            }
                    }
                }
        }
        return NETWORN_NONE;
    }


    public static String getAppVersionName(Context context){
        PackageManager manager = context.getPackageManager();
        PackageInfo info ;
        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static String getAppVersionCode(Context context){
        PackageManager manager = context.getPackageManager();
        PackageInfo info;
        try {
            info = manager.getPackageInfo(context.getPackageName(), 0);
            return String.valueOf(info.versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    //    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    public static String getImei(Context context){
        if (!checkReadPhoneStatePermission(context)) {
            return String.valueOf(NO_PERMISSION);
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId;
        try {
            deviceId = telephonyManager.getDeviceId();
            if (deviceId == null) {
                deviceId = "";
            }
            return deviceId;
        }catch (Exception e){
            deviceId="";
            return deviceId;
        }
    }
    public static String getImsi(Context context) {
        if (!checkReadPhoneStatePermission(context)) {
            return String.valueOf(NO_PERMISSION);
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId;
        try {
            deviceId=telephonyManager.getSubscriberId();
            if (deviceId==null)deviceId="";
            return deviceId;
        }catch (Exception e){
            deviceId="";
            return deviceId;
        }
    }

    private static boolean checkReadPhoneStatePermission(Context context) {
        int result = ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE);
        if (result != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }
    private static boolean checkSDWritePermission(Context context) {
        int result = ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }
    public static String getInstallTime(Context context){
        PackageManager pm = context.getPackageManager();
        PackageInfo appInfo = null;
        try {
            appInfo = pm.getPackageInfo(context.getPackageName(), 0);
            long firstTime = appInfo.firstInstallTime;
            return String.valueOf(firstTime);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return Formatter.formatIpAddress(inetAddress.hashCode());
                    }
                }
            }
        } catch (SocketException ex) {
            return "";
        }
        return "";
    }
    public synchronized static String uuid(Context context) {
        if (sID == null) {
            File installation;
            File noMedia;
            if (checkSDWritePermission(context)){
                String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Android/.uuid/";
                if (new File(path).mkdirs()){
                    installation = new File(path, INSTALL_ID);
                    noMedia = new File(path, NO_MIDEA);
                }else {
                    installation = new File(context.getFilesDir(), INSTALL_ID);
                    noMedia = new File(context.getFilesDir(), NO_MIDEA);
                }
            }
            else {
                installation = new File(context.getFilesDir(), INSTALL_ID);
                noMedia = new File(context.getFilesDir(), NO_MIDEA);
            }
            try {
                if (!installation.exists()){
                    writeInstallationFile(installation);
                    writeNoMediaFile(noMedia);
                }
                sID = readInstallationFile(installation);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return sID;
    }

    private static void writeNoMediaFile(File file) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(file);
        String noMedia = NO_MIDEA;
        outputStream.write(noMedia.getBytes());
        outputStream.close();
    }

    private static String readInstallationFile(File installation) throws IOException {
        RandomAccessFile f = new RandomAccessFile(installation, "r");
        byte[] bytes = new byte[(int) f.length()];
        f.readFully(bytes);
        f.close();
        return new String(bytes);
    }

    private static void writeInstallationFile(File installation) throws IOException {
        FileOutputStream out = new FileOutputStream(installation);
        String id = UUID.randomUUID().toString();
//        Log.e(TAG, "writeInstallationFile: uuid : " + id);
        out.write(id.getBytes());
        out.close();
    }
}
