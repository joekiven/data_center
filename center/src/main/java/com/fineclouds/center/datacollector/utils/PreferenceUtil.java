package com.fineclouds.center.datacollector.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by xiaoyue on 15-10-22.
 */
public class PreferenceUtil {

    static SharedPreferences prefs;
    public static final String PS_COLLECTOR_FILE = "ps_collector_file";
    public static final String PS_LAST_UPLOAD_USER_ACTION_TIME = "ps_last_upload_user_action_time";


    /**
     * set String Preference Value
     *
     * @param context
     * @param prefName Preference name
     * @param Value    Preference value
     */
    public static void setStringPrefrences(Context context, String prefName, String Value, String PREFS_FILE_NAME) {
        prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(prefName, Value);
        editor.commit();
    }

    /**
     * get String Preference Value
     *
     * @param context
     * @param prefName
     * @return
     */
    public static String getStringPrefrences(Context context, String prefName, String PREFS_FILE_NAME) {
        prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        if (prefs.contains(prefName))
            return prefs.getString(prefName, "");
        else
            return "";
    }

    // -----------------------------------------------

    /**
     * set Integer Preference Value
     *
     * @param context
     * @param prefName
     * @param Value
     */
    public static void setIntPrefrences(Context context, String prefName, int Value, String PREFS_FILE_NAME) {
        prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(prefName, Value);
        editor.commit();
    }

    // -----------------------------------------------

    /**
     * get Integer Preference Value
     *
     * @param context
     * @param prefName
     * @return
     */
    public static int getIntPrefrences(Context context, String prefName, String PREFS_FILE_NAME) {
        prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        return prefs.getInt(prefName, 0);
    }

    // -----------------------------------------------

    /**
     * set Boolean Preference Value
     *
     * @param context
     * @param prefName
     * @param Value
     */
    public static void setBooleanPrefrences(Context context, String prefName, Boolean Value, String PREFS_FILE_NAME) {
        prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(prefName, Value);
        editor.commit();
    }

    // -----------------------------------------------

    /**
     * get Boolean Preference Value
     *
     * @param context
     * @param prefName
     * @return
     */
    public static boolean getBooleanPrefrences(Context context, String prefName, String PREFS_FILE_NAME) {
        prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        return prefs.getBoolean(prefName, false);
    }

    // -----------------------------------------------

    /**
     * set Float Preference Value
     *
     * @param context
     * @param prefName
     * @param Value
     */
    public static void setFloatPrefrences(Context context, String prefName, Float Value, String PREFS_FILE_NAME) {
        prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putFloat(prefName, Value);
        editor.commit();
    }

    // -----------------------------------------------
    // static File SDCardRoot = Environment.getExternalStorageDirectory();

    /**
     * get Float Preference Value
     *
     * @param context
     * @param prefName
     * @return
     */
    public static float getFloatPrefrences(Context context, String prefName, String PREFS_FILE_NAME) {
        prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        return prefs.getFloat(prefName, 0);
    }

    // ------------------------------------------------

    /**
     * set Long Preference Value
     *
     * @param context
     * @param prefName
     * @param Value
     */
    public static void setLongPrefrences(Context context, String prefName, Long Value, String PREFS_FILE_NAME) {
        prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong(prefName, Value);
        editor.commit();
    }

    // -----------------------------------------------

    /**
     * get Long Preference Value
     *
     * @param context
     * @param prefName
     * @return
     */
    public static long getLongPrefrences(Context context, String prefName, String PREFS_FILE_NAME) {
        prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        return prefs.getLong(prefName, 0);
    }


    /**
     * remove all the preferences of your app. Note: only remove all which set
     * by using this sdk.
     *
     * @param context
     */
    public static void removeAllPrefrences(Context context, String PREFS_FILE_NAME) {
        prefs = context.getSharedPreferences(PREFS_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
    }



}
