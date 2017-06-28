package com.livelearn.ignorance.retrofitinterface.rxcache;

import com.livelearn.ignorance.utils.FileUtils;

import java.io.File;

import io.rx_cache.internal.RxCache;

/**
 * 缓存提供者工厂
 * Created  on 2017/5/6.
 */

public class CacheProvidersFactory {

    public static final String LONGTIME_BOOK_CACHE_DIRECTORY = "_LongTime_Book";
    public static final String DOUBAN_BOOK_CACHE_DIRECTORY = "_DouBan_Book";
    public static final String TIANGOU_PRETTYPICTURE_CACHE_DIRECTORY = "_TianGou_PrettyPicture";
    public static final String PHOTO_LITHOGRAPHY_CACHE_DIRECTORY = "_Photo_Lithography";

    private static File okHttpCacheDirectory = FileUtils.getOkHttpCacheDirectory();

    public static <T extends CacheProviders> T generateCacheProviders(String cacheDirectory, Class<T> clazz) {
        return new RxCache.Builder()
                .useExpiredDataIfLoaderNotAvailable(true)
                .persistence(FileUtils.getCacheDirectory(okHttpCacheDirectory, cacheDirectory))
                .using(clazz);
    }
}
