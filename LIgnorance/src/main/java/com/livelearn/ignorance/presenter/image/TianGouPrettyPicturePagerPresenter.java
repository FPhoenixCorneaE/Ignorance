package com.livelearn.ignorance.presenter.image;

import android.widget.ImageView;

import com.apkfuns.logutils.LogUtils;
import com.livelearn.ignorance.base.RxPresenter;
import com.livelearn.ignorance.model.bean.image.TianGouPrettyPictureModel;
import com.livelearn.ignorance.model.bean.image.TianGouPrettyPictureShowModel;
import com.livelearn.ignorance.presenter.contract.image.TianGouPrettyPicturePagerContract;
import com.livelearn.ignorance.retrofitinterface.rxapi.TianGouAPIs;
import com.livelearn.ignorance.utils.ToastUtils;

import rx.Subscriber;
import rx.Subscription;

/**
 * Created on 2017/6/1.
 */

public class TianGouPrettyPicturePagerPresenter extends RxPresenter implements TianGouPrettyPicturePagerContract.Presenter {

    private TianGouPrettyPicturePagerContract.View mView;
    private int mType;

    public TianGouPrettyPicturePagerPresenter(TianGouPrettyPicturePagerContract.View mView, int type) {
        this.mView = mView;
        this.mType = type;
        mView.setPresenter(this);
    }

    @Override
    public void getTianGouPrettyPictureList(int page, int rows) {
        Subscription subscription = TianGouAPIs.getDefault().getTianGouPrettyPictureList(mType, page, rows, new Subscriber<TianGouPrettyPictureModel>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                String detailMessage = e.getMessage();
                mView.onFailure(detailMessage);
                LogUtils.e(detailMessage);
            }

            @Override
            public void onNext(TianGouPrettyPictureModel tianGouPrettyPictureModel) {
                mView.onSuccess(tianGouPrettyPictureModel);
            }
        });
        addSubscribe(subscription);
    }

    @Override
    public void getTianGouPrettyPictureShow(final ImageView imageView, int id, final int position) {
        addSubscribe(TianGouAPIs.getDefault().getTianGouPrettyPictureShow(id, new Subscriber<TianGouPrettyPictureShowModel>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showErrorToast("电波无法到达哟");
            }

            @Override
            public void onNext(TianGouPrettyPictureShowModel tianGouPrettyPictureShowModel) {
                mView.showPrettyPictureDetails(imageView, tianGouPrettyPictureShowModel, position);
            }
        }));
    }
}
