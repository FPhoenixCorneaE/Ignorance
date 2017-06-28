package com.livelearn.ignorance.retrofitinterface.rxcache;

import com.livelearn.ignorance.model.bean.book.douban.DouBanBookDetailsBean;
import com.livelearn.ignorance.model.bean.book.douban.DouBanBookBean;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.rx_cache.DynamicKey;
import io.rx_cache.EvictDynamicKey;
import io.rx_cache.LifeCache;
import io.rx_cache.Reply;
import rx.Observable;

/**
 * DouBanBook缓存API接口
 * <p>
 * LifeCache设置缓存过期时间. 如果没有设置@LifeCache , 数据将被永久缓存理除非你使用了 EvictProvider, EvictDynamicKey or EvictDynamicKeyGroup .
 * EvictProvider可以明确地清理清理所有缓存数据.
 * EvictDynamicKey可以明确地清理指定的数据 DynamicKey.
 * EvictDynamicKeyGroup 允许明确地清理一组特定的数据. DynamicKeyGroup.
 * DynamicKey驱逐与一个特定的键使用EvictDynamicKey相关的数据。比如分页，排序或筛选要求
 * DynamicKeyGroup。驱逐一组与key关联的数据，使用EvictDynamicKeyGroup。比如分页，排序或筛选要求
 */
public interface DouBanBookCacheProviders extends CacheProviders {

    //根据tag，start与count获取豆瓣图书  缓存时间7天
    @LifeCache(duration = 7, timeUnit = TimeUnit.DAYS)
    Observable<Reply<DouBanBookBean>> getBookByTag(Observable<DouBanBookBean> oRepos, DynamicKey userName, EvictDynamicKey evictDynamicKey);

    //根据id获取豆瓣图书详情  缓存时间7天
    @LifeCache(duration = 7, timeUnit = TimeUnit.DAYS)
    Observable<Reply<DouBanBookDetailsBean>> getBookDetailsById(Observable<DouBanBookDetailsBean> oRepos, DynamicKey userName, EvictDynamicKey evictDynamicKey);

    //获取豆瓣图书  缓存时间7天
    @LifeCache(duration = 7, timeUnit = TimeUnit.DAYS)
    Observable<Reply<List<DouBanBookBean>>> getBookByQ(Observable<List<DouBanBookBean>> oRepos, DynamicKey userName, EvictDynamicKey evictDynamicKey);
}
