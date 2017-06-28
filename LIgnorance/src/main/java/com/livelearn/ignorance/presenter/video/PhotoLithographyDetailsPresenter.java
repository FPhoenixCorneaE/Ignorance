package com.livelearn.ignorance.presenter.video;

import com.apkfuns.logutils.LogUtils;
import com.livelearn.ignorance.base.RxPresenter;
import com.livelearn.ignorance.model.bean.video.PhotoLithographyDetailsModel;
import com.livelearn.ignorance.presenter.contract.video.PhotoLithographyDetailsContract;
import com.livelearn.ignorance.retrofitinterface.rxapi.PhotoLithographyAPIs;

import rx.Observer;

/**
 * Created on 2017/6/26.
 */

public class PhotoLithographyDetailsPresenter extends RxPresenter implements PhotoLithographyDetailsContract.Presenter {

    private PhotoLithographyDetailsContract.View mView;
    private String title;
    private String mediaId;

    public PhotoLithographyDetailsPresenter(PhotoLithographyDetailsContract.View mView, String title, String mediaId) {
        this.mView = mView;
        this.title = title;
        this.mediaId = mediaId;
        this.mView.setPresenter(this);
    }

    @Override
    public void getPhotoLithographyDetailsByMediaId() {
        //添加订阅
        addSubscribe(PhotoLithographyAPIs.getDefault().getPhotoLithographyDetailsByMediaId(title, mediaId, new Observer<PhotoLithographyDetailsModel>() {
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
            public void onNext(PhotoLithographyDetailsModel photoLithographyDetailsModel) {
                mView.onSuccess(photoLithographyDetailsModel);
            }
        }));
    }
}
