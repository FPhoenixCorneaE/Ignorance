package com.livelearn.ignorance.presenter.mine;

import com.apkfuns.logutils.LogUtils;
import com.livelearn.ignorance.base.RxPresenter;
import com.livelearn.ignorance.model.db.dbentity.DouBanBookCollection;
import com.livelearn.ignorance.model.db.dbhelper.DouBanBookCollectionDBHelper;
import com.livelearn.ignorance.presenter.contract.mine.DouBanBookCollectionContract;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created on 2017/7/19.
 */

public class DouBanBookCollectionPresenter extends RxPresenter implements DouBanBookCollectionContract.Presenter {

    private DouBanBookCollectionContract.View mView;

    public DouBanBookCollectionPresenter(DouBanBookCollectionContract.View mView) {
        this.mView = mView;
        this.mView.setPresenter(this);
    }

    /**
     * 获取所有久久图书收藏
     */
    @Override
    public void queryBookCollectionByPage(final int pageNum, final int pageSize) {
        addSubscribe(Observable
                .create(new Observable.OnSubscribe<List<DouBanBookCollection>>() {

                    @Override
                    public void call(Subscriber<? super List<DouBanBookCollection>> subscriber) {
                        subscriber.onNext(DouBanBookCollectionDBHelper.getInstance().queryBookCollectionByPage(pageNum, pageSize));
                        subscriber.onCompleted();
                    }
                })
                .subscribe(new Action1<List<DouBanBookCollection>>() {//onNext
                    @Override
                    public void call(List<DouBanBookCollection> douBanBookCollections) {
                        mView.onSuccess(douBanBookCollections);
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
