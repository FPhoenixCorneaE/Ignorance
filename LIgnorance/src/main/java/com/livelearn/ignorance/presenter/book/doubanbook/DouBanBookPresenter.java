package com.livelearn.ignorance.presenter.book.doubanbook;

import android.support.v4.app.Fragment;

import com.livelearn.ignorance.base.RxPresenter;
import com.livelearn.ignorance.presenter.contract.book.doubanbook.DouBanBookContract;

/**
 * Created on 2017/5/8.
 */

public class DouBanBookPresenter extends RxPresenter implements DouBanBookContract.Presenter {

    public DouBanBookPresenter(Fragment fragment,DouBanBookContract.View mView) {
        mView.setPresenter(fragment,this);
    }
}
