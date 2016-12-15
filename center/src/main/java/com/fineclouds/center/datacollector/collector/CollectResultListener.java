package com.fineclouds.center.datacollector.collector;


/**
 * Created by ubuntu on 15-11-4.
 */
public interface CollectResultListener {
    void insertAppInfoResult(long uri);
    void insertDeviceInfoResult(long uri);
}
