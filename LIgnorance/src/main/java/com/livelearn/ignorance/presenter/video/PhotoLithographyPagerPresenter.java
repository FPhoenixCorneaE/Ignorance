package com.livelearn.ignorance.presenter.video;

import com.apkfuns.logutils.LogUtils;
import com.livelearn.ignorance.base.RxPresenter;
import com.livelearn.ignorance.model.bean.video.PhotoLithographyTypeListModel;
import com.livelearn.ignorance.presenter.contract.video.PhotoLithographyPagerContract;
import com.livelearn.ignorance.retrofitinterface.rxapi.PhotoLithographyAPIs;

import rx.Observer;

/**
 * Created on 2017/6/22.
 */

public class PhotoLithographyPagerPresenter extends RxPresenter implements PhotoLithographyPagerContract.Presenter {

    private PhotoLithographyPagerContract.View mView;
    private String title;
    private String catalogId;

    public PhotoLithographyPagerPresenter(PhotoLithographyPagerContract.View mView, String title, String catalogId) {
        this.mView = mView;
        this.title = title;
        this.catalogId = catalogId;
        this.mView.setPresenter(this);
    }

    @Override
    public void getPhotoLithographyTypeList(int pageNum) {
        addSubscribe(PhotoLithographyAPIs.getDefault().getPhotoLithographyTypeList(title, catalogId, pageNum, new Observer<PhotoLithographyTypeListModel>() {
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
            public void onNext(PhotoLithographyTypeListModel photoLithographyTypeListModel) {
                mView.onSuccess(photoLithographyTypeListModel);
            }
        }));
    }
}
