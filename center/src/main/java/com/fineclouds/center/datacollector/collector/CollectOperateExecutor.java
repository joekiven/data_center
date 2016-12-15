package com.fineclouds.center.datacollector.collector;

import android.content.Context;
import android.util.Log;

import com.fineclouds.center.CenterConfig;
import com.fineclouds.center.datacollector.database.CollectionRepository;
import com.fineclouds.center.datacollector.database.CollectionSource;
import com.fineclouds.center.datacollector.entity.ActionItem;
import com.fineclouds.center.datacollector.entity.AppInfo;
import com.fineclouds.center.datacollector.entity.DeviceInfo;
import com.fineclouds.center.datacollector.entity.HeadInfo;
import com.fineclouds.center.datacollector.entity.PathItem;
import com.fineclouds.center.datacollector.pipelines.AppInfoCollector;
import com.fineclouds.center.datacollector.pipelines.DeviceInfoCollector;
import com.fineclouds.center.datacollector.pipelines.HeadInfoCollector;
import com.google.gson.Gson;


/**
 * Created by ubuntu on 16-8-18.
 */
public class CollectOperateExecutor {
    public static final int DEVICE_INFO = 0;
    public static final int APP_INFO = 1;
    public static final int BASIC_ACTION = 2;
    public static final int USER_ACTION = 4;
    private CollectResultListener collectResultListener;
    private Context context;
    private CollectionRepository repository;
    private ActionItem actionItem;
    private PathItem pathItem;

    public CollectOperateExecutor(Context context, CollectResultListener collectResultListener) {
        this.context=context;
        this.collectResultListener = collectResultListener;
        repository = new CollectionSource(context);
    }
    public CollectOperateExecutor(Context context, PathItem pathItem, CollectionSource collectionSource) {
        this.context=context;
        this.pathItem=pathItem;
        repository = collectionSource;
    }
    public CollectOperateExecutor(Context context, ActionItem actionItem, CollectionSource collectionSource) {
        this.context=context;
        this.actionItem=actionItem;
        repository = collectionSource;
    }

    public void getDeviceInfo() {
        if (CenterConfig.isDebugMode()) return;
        CollectionOperatorTask insertOption = new CollectionOperatorTask(DEVICE_INFO);
        insertOption.start();
    }
    public void getAppInfo() {
        if (CenterConfig.isDebugMode()) return;
        CollectionOperatorTask insertOption = new CollectionOperatorTask(APP_INFO);
        insertOption.start();
    }
    public void saveBasicEvent(){
        if (CenterConfig.isDebugMode()) return;
        CollectionOperatorTask insertOption = new CollectionOperatorTask(BASIC_ACTION,pathItem);
        insertOption.start();
    }
    public void saveCountEvent(){
        if (CenterConfig.isDebugMode()) return;
        CollectionOperatorTask insertOption = new CollectionOperatorTask(USER_ACTION,actionItem);
        insertOption.start();
    }

    private class CollectionOperatorTask extends Thread {
        private int operation;
        private HeadInfo headInfo;
        private DeviceInfo deviceInfo;
        private AppInfo appInfo;
        private ActionItem actionItem;
        private PathItem pathItem;
        private Gson gson;
        public CollectionOperatorTask(int operation){
            this.operation = operation;
            gson = new Gson();
        }
        public CollectionOperatorTask(int operation,PathItem pathItem){
            this.operation = operation;
            this.pathItem = pathItem;
            gson = new Gson();
        }
        public CollectionOperatorTask(int operation,ActionItem actionItem){
            this.operation = operation;
            this.actionItem= actionItem;
            gson = new Gson();
        }
        @Override
        public void run() {
            switch (operation) {
                case DEVICE_INFO:
                    headInfo = HeadInfoCollector.getHeadInfo(context);
                    deviceInfo = DeviceInfoCollector.getDeviceInfo(context);
                    deviceInfo.setHeadInfo(headInfo);
                    String deviceJson = gson.toJson(deviceInfo);
                    collectResultListener.insertDeviceInfoResult(repository.insert(deviceJson, String.valueOf(DEVICE_INFO)));
                    Log.d("fine_center", "CollectionOperatorTask: saveDeviceInfo"+deviceJson);
                    break;
                case APP_INFO:
                    if (CenterConfig.is2b()){
                        headInfo= HeadInfoCollector.getHeadInfo(context);
                        appInfo=new AppInfo();
                        appInfo.setAppInfo(AppInfoCollector.getAppInfo(context));
                        appInfo.setHeadInfo(headInfo);
                        String appJson = gson.toJson(appInfo);
                        collectResultListener.insertAppInfoResult(repository.insert(appJson, String.valueOf(APP_INFO)));
                        Log.d("fine_center", "CollectionOperatorTask: saveAppInfo "+appJson);
                    }
                    break;
                case BASIC_ACTION:
                    String pathJson = gson.toJson(pathItem);
                    long base=repository.insert(pathJson, String.valueOf(BASIC_ACTION));
                    Log.d("fine_center", "CollectionOperatorTask: saveBasicAcion result:"+base+"//"+pathJson);
                    break;
                case USER_ACTION:
                    String actionJson = gson.toJson(actionItem);
                    long user = repository.insert(actionJson, String.valueOf(USER_ACTION));
                    Log.d("fine_center", "CollectionOperatorTask: saveUserAction result: "+user+"//"+actionJson);
                    break;
            }
        }
    }
}
