package com.livelearn.ignorance.presenter.book.doubanbook;

import android.app.Dialog;

import com.apkfuns.logutils.LogUtils;
import com.livelearn.ignorance.base.RxPresenter;
import com.livelearn.ignorance.model.bean.book.douban.DouBanBookDetailsBean;
import com.livelearn.ignorance.presenter.contract.book.doubanbook.DouBanBookDetailsContract;
import com.livelearn.ignorance.retrofitinterface.rxapi.DouBanBookAPIs;
import com.livelearn.ignorance.utils.CacheUtils;
import com.livelearn.ignorance.utils.DialogUtils;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created on 2017/5/10.
 */

public class DouBanBookDetailsPresenter extends RxPresenter implements DouBanBookDetailsContract.Presenter {

    private DouBanBookDetailsContract.View mBookDetailsView;
    private Dialog loadingDialog;

    public DouBanBookDetailsPresenter(DouBanBookDetailsContract.View mBookDetailsView) {
        this.mBookDetailsView = mBookDetailsView;
        this.mBookDetailsView.setPresenter(this);
    }

    @Override
    public void getDouBanBookDetailsByBookId(final String bookId, final String bookName) {
        //先读取缓存
        DouBanBookDetailsBean mCacheData = CacheUtils.readBean(CacheUtils.CACHE_BOOK, CacheUtils.CACHE_BOOK_DETAILS, bookName);
        Observable.just(mCacheData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DouBanBookDetailsBean>() {
                    @Override
                    public void call(DouBanBookDetailsBean bookDetailsBean) {
                        if (bookDetailsBean != null) {
                            mBookDetailsView.onSuccess(bookDetailsBean);
                        } else {
                            //缓存为空，则获取服务器数据
                            getBookDetailsFromInternet(bookId, bookName);
                        }
                    }
                });
    }

    /**
     * 从网络获取图书详情
     *
     * @param bookId   图书id
     * @param bookName 图书名称
     */
    public void getBookDetailsFromInternet(String bookId, final String bookName) {
        Subscription subscription = DouBanBookAPIs.getDefault().getBookDetailsById(bookId, new Subscriber<DouBanBookDetailsBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                String detailMessage = e.getMessage();
                mBookDetailsView.onFailure(detailMessage);
                LogUtils.e(detailMessage);
            }

            @Override
            public void onNext(DouBanBookDetailsBean douBanBookDetailsBean) {
                mBookDetailsView.onSuccess(douBanBookDetailsBean);

                //保存缓存数据
                Observable.just(douBanBookDetailsBean)
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(Schedulers.io())
                        .subscribe(new Action1<DouBanBookDetailsBean>() {
                            @Override
                            public void call(DouBanBookDetailsBean bookDetailsBean) {
                                CacheUtils.saveBean(bookDetailsBean, CacheUtils.CACHE_BOOK, CacheUtils.CACHE_BOOK_DETAILS, bookName);
                            }
                        });
            }
        });
        addSubscribe(subscription);
    }

    @Override
    public void getBookReviewDetails(final String url, final String bookNameReviewTitle) {
        if (loadingDialog == null) {
            loadingDialog = DialogUtils.createLoadingIndicatorDialog(mBookDetailsView.getContext(), false, "正在努力加载中");
            loadingDialog.setCanceledOnTouchOutside(false);
        }
        loadingDialog.show();
        //先读取缓存
        DouBanBookDetailsBean.BookReviewBean mCacheData = CacheUtils.readBean(CacheUtils.CACHE_BOOK, CacheUtils.CACHE_BOOK_REVIEW_DETAILS, bookNameReviewTitle);
        Observable.just(mCacheData)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<DouBanBookDetailsBean.BookReviewBean>() {
                    @Override
                    public void call(DouBanBookDetailsBean.BookReviewBean bookReviewBean) {
                        if (bookReviewBean != null) {
                            loadingDialog.dismiss();
                            mBookDetailsView.showBookReviewDetails(bookReviewBean.getBookReviewTitle(), bookReviewBean.getBookReviewFullContent());
                        } else {
                            //缓存为空，则获取服务器数据
                            getBookReviewDetailsFromInternet(url, bookNameReviewTitle);
                        }
                    }
                });
    }

    /**
     * 从网络获取书评详情
     *
     * @param url                 网址
     * @param bookNameReviewTitle 图书名称加书评标题
     */
    private void getBookReviewDetailsFromInternet(String url, final String bookNameReviewTitle) {
        Subscription subscription = DouBanBookAPIs.getDefault().getBookReviewDetails(url, new Subscriber<DouBanBookDetailsBean.BookReviewBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                loadingDialog.dismiss();
                mBookDetailsView.showBookReviewDetails(bookNameReviewTitle, "");
            }

            @Override
            public void onNext(DouBanBookDetailsBean.BookReviewBean bookReviewBean) {
                loadingDialog.dismiss();
                mBookDetailsView.showBookReviewDetails(bookReviewBean.getBookReviewTitle(), bookReviewBean.getBookReviewFullContent());

                //保存缓存数据
                Observable.just(bookReviewBean)
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(Schedulers.io())
                        .subscribe(new Action1<DouBanBookDetailsBean.BookReviewBean>() {
                            @Override
                            public void call(DouBanBookDetailsBean.BookReviewBean bookReviewBean) {
                                CacheUtils.saveBean(bookReviewBean, CacheUtils.CACHE_BOOK, CacheUtils.CACHE_BOOK_REVIEW_DETAILS, bookNameReviewTitle);
                            }
                        });
            }
        });
        addSubscribe(subscription);
    }
}
