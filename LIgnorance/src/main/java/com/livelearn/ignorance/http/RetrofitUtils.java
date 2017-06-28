package com.livelearn.ignorance.http;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 封装一个retrofit集成0kHttp3的抽象基类
 * Created on 2016/12/26.
 */

public abstract class RetrofitUtils {

    private static Retrofit.Builder mRetrofitBuilder;
    private static OkHttpClient mOkHttpClient;
    private static String BASE_URL = "";

    static String getBaseUrl() {
        return BASE_URL;
    }

    protected static void setBaseUrl(String baseUrl) {
        BASE_URL = baseUrl;
    }


    /**
     * 获取Retrofit对象
     */
    protected Retrofit getRetrofit() {

        if (null == mRetrofitBuilder) {

            if (null == mOkHttpClient) {
                mOkHttpClient = Okhttp3Utils.getOkHttpClient();
            }

            //Retrofit2后使用build设计模式
            mRetrofitBuilder = new Retrofit.Builder()
                    //设置使用okhttp网络请求
                    .client(mOkHttpClient)
                    //解析方法,添加转化库，默认是Gson
                    .addConverterFactory(GsonConverterFactory.create())
                    //添加回调库，采用RxJava
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        }
        return mRetrofitBuilder
                //主机地址,服务器路径
                .baseUrl(getBaseUrl())
                .build();
    }
}
