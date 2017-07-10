package com.yishu.retrofit2;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by simon on 17/7/10.
 */

public interface ServerApi {
    @GET("version")
    Call<ResponseBody> checkUpdate(@Query("types") String type);


}
