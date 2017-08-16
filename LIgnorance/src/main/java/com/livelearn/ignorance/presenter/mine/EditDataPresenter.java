package com.livelearn.ignorance.presenter.mine;

import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.base.RxPresenter;
import com.livelearn.ignorance.ui.view.mine.IEditDataView;

/**
 * Created on 2017/8/10.
 */

public class EditDataPresenter extends RxPresenter {

    private IEditDataView iEditDataView;
    private BaseActivity mContext;

    public EditDataPresenter(IEditDataView iEditDataView) {
        this.iEditDataView = iEditDataView;
        this.mContext = iEditDataView.getmContext();
    }
}
