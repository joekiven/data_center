package com.fineclouds.center.datacollector.database;

import android.content.Context;

/**
 * Created by ubuntu on 16-8-18.
 */
public class SQLOperateExecutor {
    public static final int INSERT = 1;
    public static final int UPDATE = 2;
    public static final int DELETE = 3;
    public static final int QUERY = 4;
    public static final int CLEAR = 5;
    private CollectionRepository repository;
    private QueryResultListener queryResultListener;

    public SQLOperateExecutor(Context context, QueryResultListener queryResultListener) {
        repository = new CollectionSource(context);
        this.queryResultListener=queryResultListener;
    }

    public void insert(String content, String type) {
        CollectionOperatorTask insertOption = new CollectionOperatorTask(INSERT, content,type);
        insertOption.start();
    }

    public void update(String content, String type) {
        CollectionOperatorTask updateOption = new CollectionOperatorTask(UPDATE, content,type);
        updateOption.start();
    }

    public void delete(String type) {
        CollectionOperatorTask deleteOption = new CollectionOperatorTask(DELETE, type);
        deleteOption.start();
    }
    public void query(String type){
        CollectionOperatorTask deleteOption = new CollectionOperatorTask(QUERY, type);
        deleteOption.start();
    }
    public void clear(){
        CollectionOperatorTask deleteOption = new CollectionOperatorTask(CLEAR);
        deleteOption.start();
    }

    private class CollectionOperatorTask extends Thread {
        private int operation;
        private String content;
        private String type;
        public CollectionOperatorTask(int operation, String content, String type) {
            this.operation = operation;
            this.content = content;
            this.type = type;
        }
        public CollectionOperatorTask(int operation, String type){
            this.type=type;
            this.operation = operation;
        }
        public CollectionOperatorTask(int operation){
            this.operation = operation;
        }
        @Override
        public void run() {
            switch (operation) {
                case INSERT:
                    queryResultListener.getInsertResult(repository.insert(content,type));
                    break;
                case UPDATE:
                    queryResultListener.getResult(repository.update(content,type),UPDATE);
                    break;
                case DELETE:
                    queryResultListener.getResult(repository.delete(type),DELETE);
                    break;
                case QUERY:
                    queryResultListener.getQueryResult(repository.query(type));
                    break;
                case CLEAR:
                    queryResultListener.getResult(repository.clearDB(),CLEAR);
                    break;
            }

        }
    }
}
