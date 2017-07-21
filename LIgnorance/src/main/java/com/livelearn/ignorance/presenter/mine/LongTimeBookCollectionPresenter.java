package com.livelearn.ignorance.presenter.mine;

import com.apkfuns.logutils.LogUtils;
import com.livelearn.ignorance.base.RxPresenter;
import com.livelearn.ignorance.model.db.dbentity.LongTimeBookCollection;
import com.livelearn.ignorance.model.db.dbhelper.LongTimeBookCollectionDBHelper;
import com.livelearn.ignorance.presenter.contract.mine.LongTimeBookCollectionContract;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created on 2017/7/19.
 */

public class LongTimeBookCollectionPresenter extends RxPresenter implements LongTimeBookCollectionContract.Presenter {

    private LongTimeBookCollectionContract.View mView;

    public LongTimeBookCollectionPresenter(LongTimeBookCollectionContract.View mView) {
        this.mView = mView;
        this.mView.setPresenter(this);
    }

    /**
     * 获取所有久久图书收藏
     */
    @Override
    public void queryBookCollectionByPage(final int pageNum, final int pageSize) {
        addSubscribe(Observable
                .create(new Observable.OnSubscribe<List<LongTimeBookCollection>>() {

                    @Override
                    public void call(Subscriber<? super List<LongTimeBookCollection>> subscriber) {
                        subscriber.onNext(LongTimeBookCollectionDBHelper.getInstance().queryBookCollectionByPage(pageNum, pageSize));
                        subscriber.onCompleted();
                    }
                })
                .subscribe(new Action1<List<LongTimeBookCollection>>() {//onNext
                    @Override
                    public void call(List<LongTimeBookCollection> longTimeBookCollections) {
                        mView.onSuccess(longTimeBookCollections);
                    }
                }, new Action1<Throwable>() {//onError
                    @Override
                    public void call(Throwable throwable) {
                        String detailMessage = throwable.getMessage();
                        LogUtils.e(detailMessage);
                        mView.onFailure(detailMessage);
                    }
                }));
    }
}
