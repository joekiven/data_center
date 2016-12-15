package com.fineclouds.center.datacollector.database;

import java.util.List;

/**
 * Created by ubuntu on 16-8-17.
 */
public interface CollectionRepository {
    long insert(String data, String type);

    List<String> getAllItem();

    int delete(String type);

    int deleteContent(String content);

    // The function which is used to modify the table
    int update(String data, String type);

    List<String> query(String type);

    int clearDB();
}
