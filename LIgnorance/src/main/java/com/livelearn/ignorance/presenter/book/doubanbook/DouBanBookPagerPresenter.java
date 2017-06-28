package com.livelearn.ignorance.presenter.book.doubanbook;

import android.support.annotation.NonNull;

import com.apkfuns.logutils.LogUtils;
import com.livelearn.ignorance.base.RxPresenter;
import com.livelearn.ignorance.model.bean.book.douban.DouBanBookBean;
import com.livelearn.ignorance.presenter.contract.book.doubanbook.DouBanBookPagerContract;
import com.livelearn.ignorance.retrofitinterface.rxapi.DouBanBookAPIs;
import com.livelearn.ignorance.utils.CacheUtils;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created on 2017/1/20.
 */

public class DouBanBookPagerPresenter extends RxPresenter implements DouBanBookPagerContract.Presenter {

    private DouBanBookPagerContract.View mBookPagerView;
    private String mBookCategory;

    public DouBanBookPagerPresenter(@NonNull DouBanBookPagerContract.View mBookPagerView, String bookCategory) {
        this.mBookPagerView = mBookPagerView;
        this.mBookCategory = bookCategory;
        mBookPagerView.setPresenter(this);
    }

    @Override
    public void refreshData(final int start, final int count) {
        //先读取缓存
        DouBanBookBean mCacheData = CacheUtils.readBean(CacheUtils.CACHE_BOOK, mBookCategory, String.valueOf(start));
        Observable.just(mCacheData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DouBanBookBean>() {
                    @Override
                    public void call(DouBanBookBean booksBeen) {
                        if (booksBeen != null) {
                            mBookPagerView.onSuccess(booksBeen);
                        } else {
                            //缓存为空，则获取服务器数据
                            getNewData(start, count);
                        }
                    }
                });
    }

    private void getNewData(final int start, int count) {
        Subscription subscription = DouBanBookAPIs.getDefault().getBookByTag(mBookCategory, start, count, new Subscriber<DouBanBookBean>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                String detailMessage = e.getMessage();
                mBookPagerView.onFailure(detailMessage);
                LogUtils.e(detailMessage);
            }

            @Override
            public void onNext(DouBanBookBean douBanBookBean) {
                mBookPagerView.onSuccess(douBanBookBean);

                //保存缓存数据
                Observable.just(douBanBookBean)
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(Schedulers.io())
                        .subscribe(new Action1<DouBanBookBean>() {
                            @Override
                            public void call(DouBanBookBean booksBeen) {
                                CacheUtils.saveBean(booksBeen, CacheUtils.CACHE_BOOK, mBookCategory, String.valueOf(start));
                            }
                        });
            }
        });
        addSubscribe(subscription);
    }

    @Override
    public void loadingData(final int start, final int count) {
        //先读取缓存
        DouBanBookBean mCacheData = CacheUtils.readBean(CacheUtils.CACHE_BOOK, mBookCategory, String.valueOf(start));
        Observable.just(mCacheData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DouBanBookBean>() {
                    @Override
                    public void call(DouBanBookBean booksBeen) {
                        if (booksBeen != null) {
                            mBookPagerView.onSuccess(booksBeen);
                        } else {
                            //缓存为空，则获取服务器数据
                            getMoreData(start, count);
                        }
                    }
                });
    }

    private void getMoreData(final int start, int count) {
        Subscription subscription = DouBanBookAPIs.getDefault().getBookByTag(mBookCategory, start, count, new Subscriber<DouBanBookBean>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                String detailMessage = e.getMessage();
                mBookPagerView.onFailure(detailMessage);
                LogUtils.e(detailMessage);
            }

            @Override
            public void onNext(DouBanBookBean douBanBookBean) {
                mBookPagerView.onSuccess(douBanBookBean);

                //保存缓存数据
                Observable.just(douBanBookBean)
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(Schedulers.io())
                        .subscribe(new Action1<DouBanBookBean>() {
                            @Override
                            public void call(DouBanBookBean booksBeen) {
                                CacheUtils.saveBean(booksBeen, CacheUtils.CACHE_BOOK, mBookCategory, String.valueOf(start));
                            }
                        });
            }
        });
        addSubscribe(subscription);
    }
}
