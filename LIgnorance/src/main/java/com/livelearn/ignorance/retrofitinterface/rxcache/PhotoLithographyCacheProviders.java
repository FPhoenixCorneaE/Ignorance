package com.livelearn.ignorance.retrofitinterface.rxcache;

import com.livelearn.ignorance.model.bean.video.PhotoLithographyDetailsModel;
import com.livelearn.ignorance.model.bean.video.PhotoLithographyTypeListModel;

import java.util.concurrent.TimeUnit;

import io.rx_cache.DynamicKey;
import io.rx_cache.EvictDynamicKey;
import io.rx_cache.LifeCache;
import io.rx_cache.Reply;
import rx.Observable;

/**
 * Created on 2017/6/22.
 */

public interface PhotoLithographyCacheProviders extends CacheProviders {

    //获取微影对应类别列表  缓存时间7天
    @LifeCache(duration = 7, timeUnit = TimeUnit.DAYS)
    Observable<Reply<PhotoLithographyTypeListModel>> getPhotoLithographyTypeList(Observable<PhotoLithographyTypeListModel> oRepos, DynamicKey userName, EvictDynamicKey evictDynamicKey);

    //获取微影影片详情  缓存时间7天
    @LifeCache(duration = 7, timeUnit = TimeUnit.DAYS)
    Observable<Reply<PhotoLithographyDetailsModel>> getPhotoLithographyDetailsByMediaId(Observable<PhotoLithographyDetailsModel> oRepos, DynamicKey userName, EvictDynamicKey evictDynamicKey);
}
