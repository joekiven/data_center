package com.fineclouds.center.datacollector.upload;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by ubuntu on 16-8-17.
 */
public interface UploadAPI {
    String ACCEPT_JSON = "Accept: application/json";
    String USERID = "User-ID: 0";

    @Headers({
            ACCEPT_JSON,
            USERID
    })
    @FormUrlEncoded
    @POST("/fineos-base-api/collect/")
    Call<String> uploadInfo(@Field("content") String dataInfo, @Field("action_type") int type);
}

