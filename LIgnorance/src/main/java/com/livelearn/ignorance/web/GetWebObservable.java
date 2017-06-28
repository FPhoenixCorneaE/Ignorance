package com.livelearn.ignorance.web;


import com.apkfuns.logutils.LogUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import rx.Observable;
import rx.Subscriber;

public class GetWebObservable {

    public static Observable getInstance(final String url) {
        return Observable.create(new Observable.OnSubscribe<Document>() {
            @Override
            public void call(Subscriber<? super Document> subscriber) {
                Document document = null;
                try {
                    document = Jsoup.connect(url).get();
                } catch (Exception e) {
                    LogUtils.e(e);
                    subscriber.onError(e);
                } finally {
                    subscriber.onNext(document);
                    subscriber.onCompleted();
                }
            }
        });
    }
}
