package com.livelearn.ignorance.retrofitinterface.rxapi;

import com.livelearn.ignorance.common.BaseUrl;
import com.livelearn.ignorance.http.HttpResultFuncCache;
import com.livelearn.ignorance.http.RetrofitUtils;
import com.livelearn.ignorance.model.bean.book.douban.DouBanBookBean;
import com.livelearn.ignorance.model.bean.book.douban.DouBanBookDetailsBean;
import com.livelearn.ignorance.onsubscribe.DouBanBookBookReviewDetailsOnSubscribe;
import com.livelearn.ignorance.onsubscribe.DouBanBookDetailsOnSubscribe;
import com.livelearn.ignorance.retrofitinterface.book.DouBanBook;
import com.livelearn.ignorance.retrofitinterface.rxcache.CacheProvidersFactory;
import com.livelearn.ignorance.retrofitinterface.rxcache.DouBanBookCacheProviders;
import com.livelearn.ignorance.utils.RxUtils;

import java.util.List;

import io.rx_cache.DynamicKey;
import io.rx_cache.EvictDynamicKey;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * 豆瓣图书
 * Created on 2017/6/21.
 */

public class DouBanBookAPIs extends RetrofitUtils {

    private static final DouBanBookCacheProviders DOUBAN_BOOK_CACHE_PROVIDERS = CacheProvidersFactory.generateCacheProviders(CacheProvidersFactory.DOUBAN_BOOK_CACHE_DIRECTORY, DouBanBookCacheProviders.class);
    private static volatile DouBanBookAPIs defaultInstance;

    private DouBanBookAPIs() {
    }

    /**
     * 饿汉式单例模式
     */
    public static DouBanBookAPIs getDefault() {
        setBaseUrl(BaseUrl.DOUBAN_BOOK);
        if (defaultInstance == null) {
            synchronized (DouBanBookAPIs.class) {
                if (defaultInstance == null) {
                    defaultInstance = new DouBanBookAPIs();
                }
            }
        }
        return defaultInstance;
    }

    /**
     * 根据tag，start与count获取豆瓣图书
     */
    @SuppressWarnings("unchecked")
    public Subscription getBookByTag(String tag, int start, int count, Subscriber<DouBanBookBean> subscriber) {
        Observable observable = getRetrofit().create(DouBanBook.class).getBooksByTag(tag, start, count);
//        Observable observableCache = DOUBAN_BOOK_CACHE_PROVIDERS.getBookByTag(observable, new DynamicKey("豆瓣图书&" + tag + start), new EvictDynamicKey(false)).map(new HttpResultFuncCache<DouBanBookBean>());
        return RxUtils.setSubscribe(observable, subscriber);
    }

    /**
     * 根据id获取豆瓣图书详情
     */
    @SuppressWarnings("unchecked")
    public Subscription getBookDetailsById(String bookId, Subscriber<DouBanBookDetailsBean> subscriber) {
        Observable observable = Observable.create(new DouBanBookDetailsOnSubscribe(BaseUrl.DOUBAN_BOOK_DETAILS, bookId));
//        Observable observableCache = DOUBAN_BOOK_CACHE_PROVIDERS.getBookDetailsById(observable, new DynamicKey("豆瓣图书详情"), new EvictDynamicKey(false)).map(new HttpResultFuncCache<DouBanBookDetailsBean>());
        return RxUtils.setSubscribe(observable, subscriber);
    }

    /**
     * 获取豆瓣图书书评详情
     *
     * @param url 网址
     */
    public Subscription getBookReviewDetails(String url, Subscriber<DouBanBookDetailsBean.BookReviewBean> subscriber) {
        Observable observable = Observable.create(new DouBanBookBookReviewDetailsOnSubscribe(url));
        return RxUtils.setSubscribe(observable, subscriber);
    }

    /**
     * 获取豆瓣图书
     */
    @SuppressWarnings("unchecked")
    public Subscription getBookByQ(String q, int start, int count, Subscriber<List<DouBanBookBean>> subscriber) {
        Observable observable = getRetrofit().create(DouBanBook.class).getBooksByQ(q, start, count);
        Observable observableCache = DOUBAN_BOOK_CACHE_PROVIDERS.getBookByQ(observable, new DynamicKey("豆瓣图书2"), new EvictDynamicKey(false)).map(new HttpResultFuncCache<List<DouBanBookBean>>());
        return RxUtils.setSubscribe(observableCache, subscriber);
    }
}
