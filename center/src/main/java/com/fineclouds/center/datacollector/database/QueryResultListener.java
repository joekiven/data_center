package com.fineclouds.center.datacollector.database;


import java.util.List;

/**
 * Created by ubuntu on 15-11-4.
 */
public interface QueryResultListener {
    void getQueryResult(List<String> content);
    void getResult(int id, int operation);
    void getInsertResult(long uri);
}
