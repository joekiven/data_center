package com.fineclouds.center.datacollector.upload;

import android.content.Context;
import android.util.Log;

import com.fineclouds.center.CenterConfig;
import com.fineclouds.center.datacollector.database.CollectionRepository;
import com.fineclouds.center.datacollector.database.CollectionSource;
import com.fineclouds.center.datacollector.entity.ActionContainer;
import com.fineclouds.center.datacollector.pipelines.HeadInfoCollector;
import com.fineclouds.center.datacollector.utils.PreferenceUtil;
import com.fineclouds.center.datacollector.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ubuntu on 16-8-17.
 */
public class UploadOperateExecutor {
    private Context context;
    private final UploadAPI updateServer;
    private Retrofit mRetrofit;
    public static final int DEVICE_INFO = 0;
    public static final int APP_INFO = 1;
    public static final int BASIC_ACTION = 2;
    public static final int USER_ACTION = 4;
    private CollectionRepository repository;
    public UploadOperateExecutor(Context context) {
        this.context = context;
        repository = new CollectionSource(context);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(CenterConfig.getServerBaseUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        updateServer = mRetrofit.create(UploadAPI.class);
    }
    public void upLoadDeviceInfo() {
        if (!isNetworkAvailable()) return;
        UploadOperatorTask operatorTask = new UploadOperatorTask(DEVICE_INFO);
        operatorTask.start();
    }
    public void upLoadAppInfo() {
        if (!isNetworkAvailable()) return;
        UploadOperatorTask operatorTask = new UploadOperatorTask(APP_INFO);
        operatorTask.start();
    }
    public void upLoadBasicActionInfo(){
        if (!isNetworkAvailable()) return;
        UploadOperatorTask operatorTask = new UploadOperatorTask(BASIC_ACTION);
        operatorTask.start();
    }
    public void upLoadUserActionInfo(){
        if (!isNetworkAvailable()) return;
        UploadOperatorTask operatorTask = new UploadOperatorTask(USER_ACTION);
        operatorTask.start();
    }
    private boolean isNetworkAvailable() {
        return Utils.isNetworkAvailable(context);
    }
    private class UploadOperatorTask extends Thread {
        private int operation;
        private List<String> contentList = new ArrayList<>();
        int messageSize=0;
        public UploadOperatorTask(int operation){
            this.operation = operation;
        }
        @Override
        public void run() {
            switch (operation) {
                case DEVICE_INFO:
                    contentList = repository.query(String.valueOf(DEVICE_INFO));
                    messageSize=contentList.size();
                    for (int i = 0;i<messageSize;i++){
                        try {
                            Log.d("fine_center", "UploadOperatorTask: devicelist: "+contentList.get(i));
                            Call<String> stringCall=updateServer.uploadInfo(contentList.get(i),DEVICE_INFO);
                            Response<String> response = stringCall.execute();
                            if (Objects.equals(response.body(), "success")){
                                Log.d("fine_center", "UploadOperatorTask : uploadDeviceINfo success!");
                                repository.deleteContent(contentList.get(i));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case APP_INFO:
                    contentList = repository.query(String.valueOf(APP_INFO));
                    messageSize=contentList.size();
                    for (int i = 0;i<messageSize;i++){
                        try {
                            Log.d("fine_center", "UploadOperatorTask: applist: "+contentList.get(i));
                            Call<String> stringCall=updateServer.uploadInfo( contentList.get(i),APP_INFO);
                            Response<String> response = stringCall.execute();
                            if (Objects.equals(response.body(), "success")){
                                Log.d("fine_center", "UploadOperatorTask : uploadAPP_Info success!");
                                repository.deleteContent(contentList.get(i));
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case BASIC_ACTION:
                    contentList = repository.query(String.valueOf(BASIC_ACTION));
                    messageSize=contentList.size();
//                    for (int i = 0;i<messageSize;i++){
//                        try {
//                            Log.d("statistic", "UploadOperatorTask : run1 :  "+contentList.get(i));
//                            updateServer.uploadInfo( contentList.get(i),BASIC_ACTION).execute();
//                            repository.deleteContent(contentList.get(i));
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
                    if (contentList.isEmpty()) return;
                    reformatDataAndUpload(BASIC_ACTION);
                    break;
                case USER_ACTION:
                    contentList = repository.query(String.valueOf(USER_ACTION));
                    messageSize=contentList.size();
//                    for (int i = 0;i<messageSize;i++){
//                        try {
//                            updateServer.uploadInfo( contentList.get(i),USER_ACTION).execute();
//                            repository.deleteContent(contentList.get(i));
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
                    if (contentList.isEmpty()) return;
                    reformatDataAndUpload(USER_ACTION);
                    break;
            }
        }

        private void reformatDataAndUpload(int Action) {
            ActionContainer actionContainer = new ActionContainer();
            actionContainer.setActionList(contentList);
            actionContainer.setHeadInfo(HeadInfoCollector.getHeadInfo(context));
            Gson gson = new Gson();
            String action = gson.toJson(actionContainer);
            action = action.replace("\\","");
            action = action.replace("\"{","{");
            action = action.replace("}\"","}");
            try {
                Log.d("fine_center", "UploadOperatorTask : reformatDataAndUpload :  "+action);
                Call<String> stringCall=updateServer.uploadInfo( action,Action);
                Response<String> response = stringCall.execute();

                if (Objects.equals(response.body(), "success")){
                    Log.d("fine_center", "UploadOperatorTask : upload userInfo success");
                    repository.delete(String.valueOf(Action));
                        PreferenceUtil.setLongPrefrences(context, PreferenceUtil.PS_LAST_UPLOAD_USER_ACTION_TIME, System.currentTimeMillis(), PreferenceUtil.PS_COLLECTOR_FILE);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
