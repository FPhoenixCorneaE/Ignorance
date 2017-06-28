package com.livelearn.ignorance.presenter.contract;

import android.support.annotation.NonNull;

import com.livelearn.ignorance.base.BaseFragment;
import com.livelearn.ignorance.base.BasePresenter;
import com.livelearn.ignorance.base.BaseView;
import com.livelearn.ignorance.model.bean.image.GuideModel;

public interface GuideContract {

    interface View extends BaseView<Presenter,GuideModel> {

        void setPresenter(@NonNull Presenter presenter,@NonNull BaseFragment fragment);

    }

    interface Presenter extends BasePresenter {

        void showGuideData(BaseFragment fragment);

    }
}
