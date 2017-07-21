package com.livelearn.ignorance.presenter.contract.mine;

import com.livelearn.ignorance.base.BasePresenter;
import com.livelearn.ignorance.base.BaseView;
import com.livelearn.ignorance.model.db.dbentity.LongTimeBookCollection;

import java.util.List;

/**
 * Created on 2017/7/19.
 */

public interface LongTimeBookCollectionContract {

    interface View extends BaseView<Presenter, List<LongTimeBookCollection>> {

    }

    interface Presenter extends BasePresenter {

        void queryBookCollectionByPage(int pageNum, int pageSize);
    }
}
