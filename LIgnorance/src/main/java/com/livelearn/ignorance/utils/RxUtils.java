package com.livelearn.ignorance.utils;

import android.text.TextUtils;

import com.livelearn.ignorance.exception.HttpException;
import com.livelearn.ignorance.http.HttpResponse;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxUtils {

    /**
     * 设置订阅
     */
    @SuppressWarnings("unchecked")
    public static <T> Subscription setSubscribe(Observable<T> observable, Subscriber<? super T> subscriber) {
        return observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                //回调到主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 插入观察者
     */
    public static <T> Subscription setObserve(Observable<T> observable, Observer<? super T> observer) {
        return observable.subscribeOn(Schedulers.io())
                //子线程访问网络
                .subscribeOn(Schedulers.newThread())
                //回调到主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    /**
     * 统一线程处理
     */
    public static <T> Observable.Transformer<T, T> rxSchedulerHelper() {//compose简化线程
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 统一返回结果处理
     */
    public static <T> Observable.Transformer<HttpResponse<T>, T> handleResult() {//compose判断结果
        return new Observable.Transformer<HttpResponse<T>, T>() {
            @Override
            public Observable<T> call(Observable<HttpResponse<T>> httpResponseObservable) {
                return httpResponseObservable.flatMap(new Func1<HttpResponse<T>, Observable<T>>() {
                    @Override
                    public Observable<T> call(HttpResponse<T> HttpResponse) {
                        if (HttpResponse.getCode() == 200) {
                            return createData(HttpResponse.getRet());
                        } else if (!TextUtils.isEmpty(HttpResponse.getMsg())) {
                            return Observable.error(new HttpException(HttpResponse.getMsg()));
                        } else {
                            return Observable.error(new HttpException(HttpException.ErrorType.SERVER_RETURNS_ERROR));
                        }
                    }
                });
            }
        };
    }

    /**
     * 生成Observable
     */
    private static <T> Observable<T> createData(final T t) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(t);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
