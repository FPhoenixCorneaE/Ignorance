package com.livelearn.ignorance.retrofitinterface.rxapi;

import com.livelearn.ignorance.common.BaseUrl;
import com.livelearn.ignorance.http.HttpResultFuncCache;
import com.livelearn.ignorance.http.RetrofitUtils;
import com.livelearn.ignorance.model.bean.image.TianGouPrettyPictureModel;
import com.livelearn.ignorance.model.bean.image.TianGouPrettyPictureShowModel;
import com.livelearn.ignorance.retrofitinterface.image.TianGouPrettyPicture;
import com.livelearn.ignorance.retrofitinterface.rxcache.CacheProvidersFactory;
import com.livelearn.ignorance.retrofitinterface.rxcache.TianGouPrettyPictureCacheProviders;
import com.livelearn.ignorance.utils.RxUtils;

import io.rx_cache.DynamicKey;
import io.rx_cache.EvictDynamicKey;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * 天狗
 * Created on 2017/6/21.
 */

public class TianGouAPIs extends RetrofitUtils {

    private static final TianGouPrettyPictureCacheProviders TIANGOU_PRETTYPICTURE_CACHE_PROVIDERS = CacheProvidersFactory.generateCacheProviders(CacheProvidersFactory.TIANGOU_PRETTYPICTURE_CACHE_DIRECTORY, TianGouPrettyPictureCacheProviders.class);
    private static volatile TianGouAPIs defaultInstance;

    private TianGouAPIs() {
    }

    /**
     * 饿汉式单例模式
     */
    public static TianGouAPIs getDefault() {
        setBaseUrl(BaseUrl.TIANGOU_IMAGE_URL_BASE);
        if (defaultInstance == null) {
            synchronized (TianGouAPIs.class) {
                if (defaultInstance == null) {
                    defaultInstance = new TianGouAPIs();
                }
            }
        }
        return defaultInstance;
    }

    /**
     * 获取天狗美图列表
     */
    @SuppressWarnings("unchecked")
    public Subscription getTianGouPrettyPictureList(int type, int page, int rows, Subscriber<TianGouPrettyPictureModel> subscriber) {
        Observable observable = getRetrofit().create(TianGouPrettyPicture.class).getPrettyPictureList(type, page, rows);
        Observable observableCache = TIANGOU_PRETTYPICTURE_CACHE_PROVIDERS.getTianGouPrettyPictureList(observable, new DynamicKey("天狗美图&" + type + "&" + page + "&" + rows), new EvictDynamicKey(false)).map(new HttpResultFuncCache<TianGouPrettyPictureModel>());
        return RxUtils.setSubscribe(observableCache, subscriber);
    }

    /**
     * 获取天狗美图展示
     */
    @SuppressWarnings("unchecked")
    public Subscription getTianGouPrettyPictureShow(int id, Subscriber<TianGouPrettyPictureShowModel> subscriber) {
        Observable observable = getRetrofit().create(TianGouPrettyPicture.class).getPrettyPictureShow(id);
        Observable observableCache = TIANGOU_PRETTYPICTURE_CACHE_PROVIDERS.getTianGouPrettyPictureShow(observable, new DynamicKey("天狗美图展示&" + id), new EvictDynamicKey(false)).map(new HttpResultFuncCache<TianGouPrettyPictureShowModel>());
        return RxUtils.setSubscribe(observableCache, subscriber);
    }
}
