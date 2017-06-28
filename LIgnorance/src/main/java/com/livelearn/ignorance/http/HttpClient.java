package com.livelearn.ignorance.http;

import com.livelearn.ignorance.retrofitinterface.article.ArticleList;
import com.livelearn.ignorance.retrofitinterface.zhihu.ZhihuDetail;
import com.livelearn.ignorance.retrofitinterface.zhihu.ZhihuList;
import com.livelearn.ignorance.utils.RxUtils;
import com.livelearn.ignorance.web.GetWebObservable;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

public class HttpClient extends RetrofitUtils {

    private HttpClient() {
    }

    /**
     * 在访问HttpMethods时创建单例
     * 静态内部类单例模式
     * 在HttpClient类中声明一个静态内部类，静态类里实现单例对象的实例化，
     * 这样既可以确保线程安全，也可保证单例对象唯一性，同时延迟单例的实例化，这是比较推荐的一种方式。
     */
    private static class SingletonHolder {
        private static final HttpClient INSTANCE = new HttpClient();
    }

    //获取单例
    public static HttpClient getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * jsoup解析斗鱼网页 获取游戏列表
     */
    @SuppressWarnings("unchecked")
    public void getVideoList(String url, Subscriber<Object> subscriber) {
        Observable observable = GetWebObservable.getInstance(getBaseUrl() + url)
                .map(new Func1<Document, Element>() {
                    @Override
                    public Element call(Document document) {
                        return document.body();
                    }
                });
        RxUtils.setSubscribe(observable, subscriber);
    }

    public void getArticleList(String typePath, int pageCount, int page, Subscriber<Object> subscriber) {
        Observable observable = getRetrofit().create(ArticleList.class).getArticleList(typePath, pageCount, page);
        RxUtils.setSubscribe(observable, subscriber);
    }

    public void getZhihuList(String path, Subscriber<Object> subscriber) {
        Observable observable = getRetrofit().create(ZhihuList.class).getZhihuList(path);
        RxUtils.setSubscribe(observable, subscriber);
    }

    public void getZhihuDetail(int path, Subscriber<Object> subscriber) {
        Observable observable = getRetrofit().create(ZhihuDetail.class).getZhihuDetail(path);
        RxUtils.setSubscribe(observable, subscriber);
    }
}
