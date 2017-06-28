package com.livelearn.ignorance.retrofitinterface.rxapi;

import com.apkfuns.logutils.LogUtils;
import com.livelearn.ignorance.common.BaseUrl;
import com.livelearn.ignorance.http.HttpResultFuncCache;
import com.livelearn.ignorance.http.RetrofitUtils;
import com.livelearn.ignorance.model.bean.book.BookDetailsModel;
import com.livelearn.ignorance.model.bean.book.BookListModel;
import com.livelearn.ignorance.model.bean.book.BookRecommendModel;
import com.livelearn.ignorance.model.bean.book.BookTypeModel;
import com.livelearn.ignorance.onsubscribe.LongTimeBookDetailsOnSubscribe;
import com.livelearn.ignorance.onsubscribe.LongTimeBookTypeListOnSubscribe;
import com.livelearn.ignorance.retrofitinterface.book.LongTimeBook;
import com.livelearn.ignorance.retrofitinterface.rxcache.CacheProvidersFactory;
import com.livelearn.ignorance.retrofitinterface.rxcache.LongTimeBookCacheProviders;
import com.livelearn.ignorance.utils.RxUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import io.rx_cache.DynamicKey;
import io.rx_cache.EvictDynamicKey;
import rx.Observable;
import rx.Observer;
import rx.Subscription;

/**
 * 久久图书
 * Created on 2017/6/21.
 */

public class LongTimeBookAPIs extends RetrofitUtils {

    private static final LongTimeBookCacheProviders LONGTIME_BOOK_CACHE_PROVIDERS = CacheProvidersFactory.generateCacheProviders(CacheProvidersFactory.LONGTIME_BOOK_CACHE_DIRECTORY, LongTimeBookCacheProviders.class);
    private static volatile LongTimeBookAPIs defaultInstance;

    private LongTimeBookAPIs() {
    }

    /**
     * 饿汉式单例模式
     */
    public static LongTimeBookAPIs getDefault() {
        setBaseUrl(BaseUrl.LONGTIME_BOOK);
        if (defaultInstance == null) {
            synchronized (LongTimeBookAPIs.class) {
                if (defaultInstance == null) {
                    defaultInstance = new LongTimeBookAPIs();
                }
            }
        }
        return defaultInstance;
    }

    //获取书籍推荐信息  banner  最新 最热
    @SuppressWarnings("unchecked")
    public Subscription getBookRecommend(Observer<BookRecommendModel> observer) {
        Observable observable = getRetrofit().create(LongTimeBook.class).getBookRecommend();
        Observable observableCache = LONGTIME_BOOK_CACHE_PROVIDERS.getBookRecommend(observable, new DynamicKey("书本推荐配置"), new EvictDynamicKey(false)).map(new HttpResultFuncCache<BookRecommendModel>());
        return RxUtils.setObserve(observableCache, observer);
    }

    //获取书籍类别
    @SuppressWarnings("unchecked")
    public Subscription getBookTypes(Observer<List<BookTypeModel>> observer) {
        Observable observable = getRetrofit().create(LongTimeBook.class).getBookTypes();
        Observable observableCache = LONGTIME_BOOK_CACHE_PROVIDERS.getBookTypes(observable, new DynamicKey("书本类别"), new EvictDynamicKey(false)).map(new HttpResultFuncCache<List<BookTypeModel>>());
        return RxUtils.setObserve(observableCache, observer);
    }

    //根据类型获取书籍集合
    @SuppressWarnings("unchecked")
    public Subscription getBookTypeList(BookTypeModel bookTypeModel, int startPage, Observer<List<BookListModel>> observer) {
        Observable observable = Observable.create(new LongTimeBookTypeListOnSubscribe<BookListModel>(bookTypeModel.getBookTypeUrl().replace("{Page}", String.valueOf(startPage))));
        Observable observableCache = LONGTIME_BOOK_CACHE_PROVIDERS.getBookTypeList(observable, new DynamicKey(bookTypeModel.getBookTypeName() + startPage), new EvictDynamicKey(false)).map(new HttpResultFuncCache<List<BookListModel>>());
        return RxUtils.setObserve(observableCache, observer);
    }

    //获得书籍的详情
    @SuppressWarnings("unchecked")
    public Subscription getBookDetails(String bookUrl, String bookName, Observer<BookDetailsModel> observer) {
        Observable observable = Observable.create(new LongTimeBookDetailsOnSubscribe(bookUrl));
        Observable observableCache = LONGTIME_BOOK_CACHE_PROVIDERS.getBookDetails(observable, new DynamicKey(bookName), new EvictDynamicKey(false)).map(new HttpResultFuncCache<BookDetailsModel>());
        return RxUtils.setObserve(observableCache, observer);
    }

    //获得搜索热门标签
    @SuppressWarnings("unchecked")
    public Subscription getSearchBookLabel(Observer<List<String>> observer) {
        Observable observable = getRetrofit().create(LongTimeBook.class).getSearchBookLabel();
        Observable observableCache = LONGTIME_BOOK_CACHE_PROVIDERS.getSearchBookLabel(observable, new DynamicKey("搜索热门标签"), new EvictDynamicKey(false)).map(new HttpResultFuncCache<List<String>>());
        return RxUtils.setObserve(observableCache, observer);
    }

    //根据关键字搜索书籍
    @SuppressWarnings("unchecked")
    public Subscription getSearchBookListByKeyword(String keyword, Observer<List<BookListModel>> observer) {
        try {
            //中文记得转码  不然会乱码  搜索不出想要的效果
            keyword = URLEncoder.encode(keyword, "utf-8");
        } catch (UnsupportedEncodingException e) {
            LogUtils.e(e);
        }
        Observable observable = Observable.create(new LongTimeBookTypeListOnSubscribe(BaseUrl.LONGTIME_BOOK_SEARCH.replace("{Key}", keyword)));
        Observable observableCache = LONGTIME_BOOK_CACHE_PROVIDERS.getBookTypeList(observable, new DynamicKey("关键字搜索书籍&" + keyword), new EvictDynamicKey(false)).map(new HttpResultFuncCache<List<BookListModel>>());
        return RxUtils.setObserve(observableCache, observer);
    }
}
