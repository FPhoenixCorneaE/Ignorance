package com.livelearn.ignorance.presenter.contract.mine;

import com.livelearn.ignorance.base.BasePresenter;
import com.livelearn.ignorance.base.BaseView;
import com.livelearn.ignorance.model.db.dbentity.DouBanBookCollection;

import java.util.List;

/**
 * Created on 2017/7/19.
 */

public interface DouBanBookCollectionContract {

    interface View extends BaseView<Presenter, List<DouBanBookCollection>> {

    }

    interface Presenter extends BasePresenter {

        void queryBookCollectionByPage(int pageNum, int pageSize);
    }
}
