package com.livelearn.ignorance.presenter.contract.book.doubanbook;

import com.livelearn.ignorance.base.BasePresenter;
import com.livelearn.ignorance.base.BaseView;
import com.livelearn.ignorance.model.bean.book.douban.DouBanBookBean;

/**
 * Created on 2017/1/20.
 */

public interface DouBanBookPagerContract {

    interface View extends BaseView<Presenter, DouBanBookBean> {

        void onCompleted();

        //下拉刷新
        void pullRefresh();

        //上拉加载
        void pullOnLoading();

        void setListeners();

    }

    interface Presenter extends BasePresenter {

        //刷新数据
        void refreshData(int start, int count);

        //加载数据
        void loadingData(int start, int count);

    }
}
