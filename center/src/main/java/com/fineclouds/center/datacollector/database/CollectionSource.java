package com.fineclouds.center.datacollector.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ubuntu on 16-8-17.
 */
public class CollectionSource implements CollectionRepository{
    private final Context context;
    private CollectionDB collectionDB;
    private SQLiteDatabase db;
    public CollectionSource(Context context){
        this.context = context;
        collectionDB =new CollectionDB(context);
        db = collectionDB.getWritableDatabase();
    }
    @Override
    public long insert(String data, String type) {
        ContentValues contentValues=new ContentValues();
        contentValues.put(CollectionDB.CONTENT ,data);
        contentValues.put(CollectionDB.TYPE ,type);
        return db.insert(CollectionDB.COLLECTION_INFO_TABLE,null,contentValues);
    }

    @Override
    public List<String> getAllItem() {
        Cursor cursor = db.query(CollectionDB.COLLECTION_INFO_TABLE, null, null, null, null, null, null);
        return getList(cursor);
    }

    @Override
    public int delete(String type) {
        String[] values ={type};
        String where = CollectionDB.TYPE + " = ?";
        return db.delete(CollectionDB.COLLECTION_INFO_TABLE, where, values);
    }

    @Override
    public int deleteContent(String content) {
        String[] values ={content};
        String where = CollectionDB.CONTENT + " = ?";
        return db.delete(CollectionDB.COLLECTION_INFO_TABLE, where, values);
    }

    @Override
    public int update(String data, String type) {
        String where = CollectionDB.TYPE + " = ?";
        String[] values ={type};
        ContentValues contentValues=new ContentValues();
        contentValues.put(CollectionDB.CONTENT ,data);
        contentValues.put(CollectionDB.TYPE ,type);
        return db.update(CollectionDB.COLLECTION_INFO_TABLE, contentValues, where, values);
    }


    @Override
    public List<String> query(String type) {
        String where = CollectionDB.TYPE + " = ?";
        String[] values ={type};
        Cursor cursor = db
                .query(CollectionDB.COLLECTION_INFO_TABLE, null, where, values, null, null, null);
        return getMultiItemFromCursor(cursor);
    }

    @Override
    public int clearDB() {
        return db.delete(CollectionDB.COLLECTION_INFO_TABLE, null,null);
    }
    public List<String> getList(Cursor cursor){
        List<String> dataList = new ArrayList<>();
        while (cursor.moveToNext()){
            dataList.add(getSingleItemFromCursor(cursor));
        }
        cursor.close();
        return dataList;
    }
    private String getSingleItemFromCursor(Cursor cursor) {
        String content =cursor.getString(cursor.getColumnIndex(CollectionDB.CONTENT));
        return content;
    }
    public List<String> getMultiItemFromCursor(Cursor cursor){
        List<String> dataList = new ArrayList<>();
            while (cursor.moveToNext()){
                dataList.add(getSingleItemFromCursor(cursor));
            }
        cursor.close();
        return dataList;
    }
}
