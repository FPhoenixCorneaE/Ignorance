package com.livelearn.ignorance.utils;

import android.content.Context;

import java.io.File;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import top.zibin.luban.Luban;

/**
 * 图片压缩操作
 */
public class CompressUtils {

    /**
     * 类似于微信朋友圈的压缩算法
     *
     * @param imgPath 本地图片路径
     */
    public static void lubanCompress(final Context context,
                                     final String imgPath,
                                     final Action1<Throwable> onError,
                                     final Action1<? super File> onNext) {
        //使用线程启动压缩
        new Thread(new Runnable() {
            @Override
            public void run() {
                File beforeFile = new File(imgPath);
                Luban.get(context)
                        .load(beforeFile)
                        .putGear(Luban.THIRD_GEAR)
                        .asObservable()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError(onError)
                        .onErrorResumeNext(new Func1<Throwable, Observable<? extends File>>() {
                            @Override
                            public Observable<? extends File> call(Throwable throwable) {
                                return Observable.empty();
                            }
                        })
                        .subscribe(onNext);
            }
        }).start();
    }
}
