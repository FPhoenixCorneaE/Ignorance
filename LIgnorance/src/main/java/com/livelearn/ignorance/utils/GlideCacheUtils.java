package com.livelearn.ignorance.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Looper;

import com.apkfuns.logutils.LogUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.livelearn.ignorance.application.IgnoranceApplication;
import com.livelearn.ignorance.common.FilesDirectory;

import java.io.File;

/**
 * Glide缓存工具类
 * Created on 2017/5/19.
 */

public class GlideCacheUtils {

    @SuppressLint("StaticFieldLeak")
    private static final Context mContext = IgnoranceApplication.getInstance().getApplicationContext();
    private static final File cacheDir = FilesDirectory.CACHE_DIR;
    private static final File externalCacheDir = FilesDirectory.EXTERNAL_CACHE_DIR;

    /**
     * 清除图片磁盘缓存
     */
    private static void clearImageDiskCache() {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.get(mContext).clearDiskCache();
                    }
                }).start();
            } else {
                Glide.get(mContext).clearDiskCache();
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    /**
     * 清除图片内存缓存
     */
    private static void clearImageMemoryCache() {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
                Glide.get(mContext).clearMemory();
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
    }

    /**
     * 清除图片所有缓存
     */
    public static void clearImageAllCache() {
        clearImageDiskCache();
        clearImageMemoryCache();
        String imageExternalCatchDir = externalCacheDir + ExternalCacheDiskCacheFactory.DEFAULT_DISK_CACHE_DIR;
        CleanCacheUtils.deleteFolderFile(imageExternalCatchDir, true);
    }

    /**
     * 获取Glide造成的缓存大小
     *
     * @return CacheSize
     */
    public static long getCacheSize() {
        try {
            return CleanCacheUtils.getFolderSize(new File(cacheDir + "/" + InternalCacheDiskCacheFactory.DEFAULT_DISK_CACHE_DIR));
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return 0;
    }
}
