package com.livelearn.ignorance.presenter.contract.video;

import com.livelearn.ignorance.base.BasePresenter;
import com.livelearn.ignorance.base.BaseView;
import com.livelearn.ignorance.model.bean.video.PhotoLithographyDetailsModel;

/**
 * Created on 2017/6/26.
 */

public interface PhotoLithographyDetailsContract {

    interface View extends BaseView<Presenter, PhotoLithographyDetailsModel> {

        void onEmpty();
    }

    interface Presenter extends BasePresenter {

        void getPhotoLithographyDetailsByMediaId();
    }
}
