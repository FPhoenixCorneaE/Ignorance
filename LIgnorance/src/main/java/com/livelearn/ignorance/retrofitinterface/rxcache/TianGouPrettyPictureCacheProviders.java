package com.livelearn.ignorance.retrofitinterface.rxcache;

import com.livelearn.ignorance.model.bean.image.TianGouPrettyPictureModel;
import com.livelearn.ignorance.model.bean.image.TianGouPrettyPictureShowModel;

import java.util.concurrent.TimeUnit;

import io.rx_cache.DynamicKey;
import io.rx_cache.EvictDynamicKey;
import io.rx_cache.LifeCache;
import io.rx_cache.Reply;
import rx.Observable;

/**
 * Created on 2017/5/31.
 */

public interface TianGouPrettyPictureCacheProviders extends CacheProviders {

    //获取天狗美图列表
    @LifeCache(duration = 7, timeUnit = TimeUnit.DAYS)
    Observable<Reply<TianGouPrettyPictureModel>> getTianGouPrettyPictureList(Observable<TianGouPrettyPictureModel> oRepos, DynamicKey userName, EvictDynamicKey evictDynamicKey);

    //获取天狗美图展示
    @LifeCache(duration = 7, timeUnit = TimeUnit.DAYS)
    Observable<Reply<TianGouPrettyPictureShowModel>> getTianGouPrettyPictureShow(Observable<TianGouPrettyPictureShowModel> oRepos, DynamicKey userName, EvictDynamicKey evictDynamicKey);
}
