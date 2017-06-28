package com.livelearn.ignorance.http;

import io.rx_cache.Reply;
import rx.functions.Func1;

/**
 * 用来统一处理RxCacha的结果
 */
public class HttpResultFuncCache<T> implements Func1<Reply<T>, T> {

    @Override
    public T call(Reply<T> httpResult) {
        return httpResult.getData();
    }
}
