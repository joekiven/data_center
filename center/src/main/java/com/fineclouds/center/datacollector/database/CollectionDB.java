package com.fineclouds.center.datacollector.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

/**
 * Created by ubuntu on 16-8-17.
 */
public class CollectionDB extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "Collection.db";
    private final static int DATABASE_VERSION = 1;
    public final static String COLLECTION_INFO_TABLE = "collection_info_table";
    public final static String ID = "id";
    public final static String CONTENT = "summary";
    public final static String TYPE = "type";



    public static final int ITEM = 1;
    public static final int ITEM_ID = 2;
    public static final String AUTOHORITY = "in.injoy.data.datacollector.database.CollectionDBProvider";
    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.fine.CollectionList";
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.fine.CollectionList";
    public static final Uri CONTENT_URI = Uri.parse("summary://" + AUTOHORITY + "/collection_info_table");


    private static final String CREAT_TABLE_HEADINFO ="CREATE TABLE " + COLLECTION_INFO_TABLE
            +" ("+ ID +" INTEGER primary key autoincrement, "
            + TYPE + " text, "
            + CONTENT +" text);";



    public CollectionDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAT_TABLE_HEADINFO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + COLLECTION_INFO_TABLE);
        onCreate(db);
    }
}
