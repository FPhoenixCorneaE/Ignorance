package com.livelearn.ignorance.http;

import android.util.Log;

import com.apkfuns.logutils.LogUtils;
import com.livelearn.ignorance.application.IgnoranceApplication;
import com.livelearn.ignorance.utils.FileUtils;
import com.livelearn.ignorance.utils.NetworkUtils;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * okHttp的配置
 * 缓存已经添加  目前只支持GET请求的缓存  POST的我在找合适的处理方法
 * 也可根据自己的需要进行相关的修改
 * Created by Administrator on 2016/12/26.
 */

class Okhttp3Utils {

    private static String TAG = Okhttp3Utils.class.getSimpleName();
    private static OkHttpClient mOkHttpClient;
    //缓存大小,100MB
    private static final Cache cache = new Cache(FileUtils.getOkHttpCacheDirectory(), 100 * 1024 * 1024);

    /**
     * 获取OkHttpClient对象
     */
    static OkHttpClient getOkHttpClient() {
        if (null == mOkHttpClient) {
            synchronized (OkHttpClient.class) {
                if (mOkHttpClient == null) {
                    //同样okhttp3后也使用build设计模式
                    mOkHttpClient = new OkHttpClient.Builder()
                            //设置一个自动管理cookies的管理器,这里是一个坑,有些接口会出现空指针异常
//                            .cookieJar(cookieJar)
                            //添加拦截器
                            .addInterceptor(mTokenInterceptor)
                            //打印请求参数和响应数据
                            .addInterceptor(loggingInterceptor)
                            //添加网络连接器
                            .addNetworkInterceptor(networkInterceptor)
                            //设置请求读写的超时时间
                            .connectTimeout(30, TimeUnit.SECONDS)
                            .writeTimeout(30, TimeUnit.SECONDS)
                            .readTimeout(30, TimeUnit.SECONDS)
                            //错误重连
                            .retryOnConnectionFailure(true)
                            //设置缓存目录
                            .cache(cache)
                            .build();

                }
            }
        }
        return mOkHttpClient;
    }


    /**
     * 云端响应头拦截器
     * 用于添加统一请求头  请按照自己的需求添加
     */
    private static final Interceptor mTokenInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            HttpUrl httpUrl = originalRequest
                    .url()
                    .newBuilder()
                    // add common parameter
                    .addQueryParameter("token", "ABcef1234")
                    .addQueryParameter("user_name", "Esdeath")
                    .build();
            Request authorised = originalRequest.newBuilder()
                    // add common header
                    .addHeader("YouzyApp_FromSource", "android-2.65")
                    .addHeader("YouzyApp_IP", NetworkUtils.getLocalIPv4())
//                    .url(httpUrl)
                    .build();
            return chain.proceed(authorised);
        }
    };

    /**
     * 云端响应头拦截器，打印请求参数和响应数据
     */
    private static final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            if (LogUtils.configAllowLog)
                Log.e(LogUtils.configTagPrefix + TAG, message);
        }
    }).setLevel(HttpLoggingInterceptor.Level.BODY);

    /**
     * 云端响应头拦截器，用来配置缓存策略
     */
    private static final Interceptor networkInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();
            if (!NetworkUtils.isNetworkAvailable()) {
                LogUtils.e("无网络");
                request = request.newBuilder()
                        //只走缓存
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }

            Response originalResponse = chain.proceed(request);

            Response response;
            if (NetworkUtils.isNetworkAvailable()) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                int maxAge = 60 * 60; //在线缓存在1小时内可读取
                response = originalResponse.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Pragma")
                        .build();
            } else {
                //无网络时
                int maxStale = 60 * 60 * 24 * 365; //缓存保存时间为365天
                response = originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
            }
            return response;
        }
    };

    /**
     * 自动管理Cookies
     */
    private static final CookieJar cookieJar = new CookieJar() {
        private final PersistentCookieStore cookieStore = new PersistentCookieStore(IgnoranceApplication.getInstance().getApplicationContext());

        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
            //将cookie保存到SharedPreferences中
            if (cookies != null && !cookies.isEmpty()) {
                for (Cookie item : cookies) {
                    cookieStore.add(url, item);
                }
            }
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            //从保存位置读取，注意此处不能为空，否则会导致空指针
            return cookieStore.get(url);
        }
    };
}
