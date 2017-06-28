package com.livelearn.ignorance.presenter.contract.image;

import android.widget.ImageView;

import com.livelearn.ignorance.base.BasePresenter;
import com.livelearn.ignorance.base.BaseView;
import com.livelearn.ignorance.model.bean.image.TianGouPrettyPictureModel;
import com.livelearn.ignorance.model.bean.image.TianGouPrettyPictureShowModel;

/**
 * Created on 2017/6/1.
 */

public interface TianGouPrettyPicturePagerContract {

    interface View extends BaseView<Presenter, TianGouPrettyPictureModel> {

        void noData();

        void loadCompleteAllData();

        void showPrettyPictureDetails(ImageView imageView, TianGouPrettyPictureShowModel data, int position);
    }

    interface Presenter extends BasePresenter {
        void getTianGouPrettyPictureList(int page, int rows);

        void getTianGouPrettyPictureShow(ImageView imageView, int id, int position);
    }
}
