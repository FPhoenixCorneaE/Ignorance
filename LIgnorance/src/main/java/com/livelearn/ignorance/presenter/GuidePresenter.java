package com.livelearn.ignorance.presenter;


import androidx.annotation.NonNull;

import com.livelearn.ignorance.base.BaseFragment;
import com.livelearn.ignorance.base.RxPresenter;
import com.livelearn.ignorance.model.bean.image.GuideModel;
import com.livelearn.ignorance.presenter.contract.GuideContract;

/**
 * 引导
 */
public class GuidePresenter extends RxPresenter implements GuideContract.Presenter {

    private GuideContract.View mGuideView;

    public GuidePresenter(@NonNull GuideContract.View mGuideView, @NonNull BaseFragment fragment) {
        this.mGuideView = mGuideView;
        mGuideView.setPresenter(this, fragment);
    }

    @Override
    public void showGuideData(BaseFragment fragment) {
        mGuideView.onSuccess(new GuideModel().getGuideData(fragment));
    }
}
