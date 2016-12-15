package com.fineclouds.center.datacollector;

import android.content.Context;

import com.fineclouds.center.CenterConfig;
import com.fineclouds.center.datacollector.collector.CollectOperateExecutor;
import com.fineclouds.center.datacollector.entity.ActionItem;
import com.fineclouds.center.datacollector.entity.PathItem;

import java.util.Map;

/**
 * Created by ubuntu on 16-8-22.
 */
public class Collector {
    private static String lastActivity = "AppOpen";
    private static long startTime=0;
    private static long stopTime=0;
    public static void countEvent(Context context, String eventId, Map<String, String> eventContent ){
        ActionItem actionItem =new ActionItem();
        actionItem.setEventId(eventId);
        actionItem.setEventContent(eventContent);
//        actionItem.setHeadInfo(HeadInfoCollector.getHeadInfo(context));
        actionItem.setCreateTime(System.currentTimeMillis());
        CollectOperateExecutor executor = new CollectOperateExecutor(context,actionItem, CenterConfig.getCollectionSource());
        executor.saveCountEvent();
    }
    public static void onStartEvent(){
        startTime= System.currentTimeMillis();
    }
    public static void onEndEvent(Context context){
//        Log.d("statistic", "Collector : onEndEvent : end ");
        stopTime= System.currentTimeMillis();
        long stayTime = (stopTime-startTime)/1000;
        String currentActivity = context.getClass().getSimpleName();
        PathItem pathItem =new PathItem();
        pathItem.setActvtyName(currentActivity);
        pathItem.setLastActvty(lastActivity);
        pathItem.setStayTime(String.valueOf(stayTime));
        pathItem.setCreateTime(stopTime);
//        pathItem.setHeadInfo(HeadInfoCollector.getHeadInfo(context));
        lastActivity= currentActivity;
        CollectOperateExecutor executor = new CollectOperateExecutor(context,pathItem, CenterConfig.getCollectionSource());
        executor.saveBasicEvent();
    }
}