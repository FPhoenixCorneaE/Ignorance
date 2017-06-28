package com.livelearn.ignorance.retrofitinterface.rxapi;

import com.livelearn.ignorance.common.BaseUrl;
import com.livelearn.ignorance.http.HttpResultFuncCache;
import com.livelearn.ignorance.http.RetrofitUtils;
import com.livelearn.ignorance.model.bean.video.PhotoLithographyDetailsModel;
import com.livelearn.ignorance.model.bean.video.PhotoLithographyTypeListModel;
import com.livelearn.ignorance.retrofitinterface.rxcache.CacheProvidersFactory;
import com.livelearn.ignorance.retrofitinterface.rxcache.PhotoLithographyCacheProviders;
import com.livelearn.ignorance.retrofitinterface.video.PhotoLithography;
import com.livelearn.ignorance.utils.RxUtils;

import io.rx_cache.DynamicKey;
import io.rx_cache.EvictDynamicKey;
import rx.Observable;
import rx.Observer;
import rx.Subscription;

/**
 * Created on 2017/6/22.
 */

public class PhotoLithographyAPIs extends RetrofitUtils {

    private static final PhotoLithographyCacheProviders PHOTO_LITHOGRAPHY_CACHE_PROVIDERS = CacheProvidersFactory.generateCacheProviders(CacheProvidersFactory.PHOTO_LITHOGRAPHY_CACHE_DIRECTORY, PhotoLithographyCacheProviders.class);
    private static volatile PhotoLithographyAPIs defaultInstance;

    private PhotoLithographyAPIs() {
    }

    /**
     * 饿汉式单例模式
     */
    public static PhotoLithographyAPIs getDefault() {
        setBaseUrl(BaseUrl.PHOTOLITHOGRAPHY_HOST);
        if (defaultInstance == null) {
            synchronized (PhotoLithographyAPIs.class) {
                if (defaultInstance == null) {
                    defaultInstance = new PhotoLithographyAPIs();
                }
            }
        }
        return defaultInstance;
    }

    //获取微影对应类别列表
    @SuppressWarnings("unchecked")
    public Subscription getPhotoLithographyTypeList(String title, String catalogId, int pageNum, Observer<PhotoLithographyTypeListModel> observer) {
        Observable observable = getRetrofit().create(PhotoLithography.class).getPhotoLithographyTypeList(catalogId, pageNum);
        Observable observableCache = PHOTO_LITHOGRAPHY_CACHE_PROVIDERS.getPhotoLithographyTypeList(observable, new DynamicKey(title + "&" + catalogId + "&" + pageNum), new EvictDynamicKey(false)).map(new HttpResultFuncCache<PhotoLithographyTypeListModel>());
        return RxUtils.setObserve(observableCache, observer);
    }

    //获取微影影片详情
    @SuppressWarnings("unchecked")
    public Subscription getPhotoLithographyDetailsByMediaId(String title, String mediaId,  Observer<PhotoLithographyDetailsModel> observer) {
        Observable observable = getRetrofit().create(PhotoLithography.class).getPhotoLithographyDetailsByMediaId(mediaId);
        Observable observableCache = PHOTO_LITHOGRAPHY_CACHE_PROVIDERS.getPhotoLithographyDetailsByMediaId(observable, new DynamicKey(title + "&" + mediaId ), new EvictDynamicKey(false)).map(new HttpResultFuncCache<PhotoLithographyDetailsModel>());
        return RxUtils.setObserve(observableCache, observer);
    }
}
