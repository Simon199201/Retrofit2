package com.yishu.retrofit2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Api api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("www.baidu.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        api = retrofit.create(Api.class);

    }

    public void requestApi(View view) {
        //无参
        Call<User> call = api.getUserInfo();
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        //GET 有参 query
        Call<User> call1 = api.getUserInfo(1);
        call1.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        //GET 有参 path
        Call<User> call2 = api.getUserInfoWithPath(1);
        call2.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        //GET 有参 map
        Map<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(1));
        map.put("username", "simon");
        Call<User> call3 = api.getUserInfoWithMap(map);
        call3.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });

        //POST javabean
        api.saveUser(new User("simon", "123")).enqueue(new Callback<BaseResult>() {
            @Override
            public void onResponse(Call<BaseResult> call, Response<BaseResult> response) {

            }

            @Override
            public void onFailure(Call<BaseResult> call, Throwable t) {

            }
        });

        //post form
        api.editUser(1, "mark").enqueue(new Callback<BaseResult>() {
            @Override
            public void onResponse(Call<BaseResult> call, Response<BaseResult> response) {

            }

            @Override
            public void onFailure(Call<BaseResult> call, Throwable t) {

            }
        });


    }
    //rx java + retrofit2 链式 简洁 明了

    public void loginWithRxJava(View view) {
        api.login(new UserParam("simon", "123456")).flatMap(new Func1<BaseResult, Observable<User>>() {
            @Override
            public Observable<User> call(BaseResult baseResult) {
                return api.getUserInfoWithRx(baseResult.getUser_id());
            }
        }).subscribeOn(Schedulers.io()).subscribe(new Action1<User>() {
            @Override
            public void call(User user) {
                Toast.makeText(MainActivity.this, user.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
