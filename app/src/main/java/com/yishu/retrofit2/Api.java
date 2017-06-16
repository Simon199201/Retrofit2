package com.yishu.retrofit2;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by simon on 17/6/16.
 */

public interface Api {
    @POST
    Observable<BaseResult> login(@Body UserParam param);

    @GET("group/{id}/repos")
    Observable<User> getUserInfoWithRx(@Path("id") int userid);

    @GET("users/{user}/repos")
    Call<User> getUserInfo();

    @GET("users/{user}/repos")
    Call<User> getUserInfo(@Query("id") int id);

    @GET("group/{id}/repos")
    Call<User> getUserInfoWithPath(@Path("id") int userid);

    @GET("group/repos")
    Call<User> getUserInfoWithMap(@QueryMap() Map<String, String> map);

    @POST("user/new")
    Call<BaseResult> saveUser(@Body User user);

    @FormUrlEncoded
    @POST("user/edit")
    Call<BaseResult> editUser(@Field("id") int user_id, @Field("username") String username);
}

