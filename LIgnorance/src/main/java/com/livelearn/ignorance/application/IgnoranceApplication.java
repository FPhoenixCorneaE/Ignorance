package com.livelearn.ignorance.application;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.apkfuns.logutils.LogUtils;
import com.facebook.fresco.helper.Phoenix;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.model.db.GreenDaoManager;
import com.livelearn.ignorance.utils.CleanCacheUtils;
import com.livelearn.ignorance.utils.DisplayUtils;
import com.livelearn.ignorance.utils.NetworkUtils;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.utils.SharedPreferencesUtils;
import com.livelearn.ignorance.utils.ToastUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class IgnoranceApplication extends MultiDexApplication {

    private static IgnoranceApplication instance;

    public static synchronized IgnoranceApplication getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        // 配置日志是否输出(默认true)
        LogUtils.configAllowLog = true;
        // 配置日志前缀
        LogUtils.configTagPrefix = "ILog-";

        //初始化SharedPreferences
        SharedPreferencesUtils.init(getInstance());

        //初始化Toast
        ToastUtils.init(getInstance());

        //初始化Display
        DisplayUtils.init(getInstance());

        //初始化Resource
        ResourceUtils.init(getInstance());

        //初始化Network
        NetworkUtils.init(getInstance());

        //GreenDao的初始化,数据库的创建、更新
        setDataBase();

        //文件缓存数据结构有变，必须清空文件缓存，否则程序会抛错
        clearFileCache();

        //初始化Facebook图片加载库Fresco
        Phoenix.init(getInstance());
    }

    /**
     * 数据库的创建、更新
     */
    private void setDataBase() {
        try {
            Properties properties = new Properties();
            InputStream inputStream = getAssets().open("config.properties");
            properties.load(inputStream);
            long newDBVersion = Long.valueOf(properties.getProperty("newDBVersion"));
            long oldDBVersion = SharedPreferencesUtils.getLong(Constant.USER_INFO, Constant.DB_VERSION);
            if (newDBVersion > oldDBVersion) {
                SharedPreferencesUtils.put(Constant.USER_INFO, Constant.DB_VERSION, newDBVersion);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        GreenDaoManager.getInstance().startLoadDB();
                    }
                }).start();
            }
            inputStream.close();
        } catch (IOException e) {
            LogUtils.e(e);
        }
    }

    /**
     * 清空文件缓存
     */
    private void clearFileCache() {
        try {
            Properties properties = new Properties();
            InputStream inputStream = getAssets().open("config.properties");
            properties.load(inputStream);
            long newFileCacheBeanVersion = Long.valueOf(properties.getProperty("newFileCacheBeanVersion"));
            long oldFileCacheBeanVersion = SharedPreferencesUtils.getLong(Constant.USER_INFO, Constant.FILE_CACHE_BEAN_VERSION);
            if (newFileCacheBeanVersion > oldFileCacheBeanVersion) {
                SharedPreferencesUtils.put(Constant.USER_INFO, Constant.FILE_CACHE_BEAN_VERSION, newFileCacheBeanVersion);
                CleanCacheUtils.cleanFiles();
            }
            inputStream.close();
        } catch (IOException e) {
            LogUtils.e(e);
        }
    }
}
