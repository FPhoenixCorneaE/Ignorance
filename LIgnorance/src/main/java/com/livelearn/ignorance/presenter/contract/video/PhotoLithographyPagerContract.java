package com.livelearn.ignorance.presenter.contract.video;

import com.livelearn.ignorance.base.BasePresenter;
import com.livelearn.ignorance.base.BaseView;
import com.livelearn.ignorance.model.bean.video.PhotoLithographyTypeListModel;

/**
 * Created on 2017/6/22.
 */

public class PhotoLithographyPagerContract {

    public interface View extends BaseView<Presenter, PhotoLithographyTypeListModel> {

    }

    public interface Presenter extends BasePresenter {
        void getPhotoLithographyTypeList(int pageNum);
    }
}
