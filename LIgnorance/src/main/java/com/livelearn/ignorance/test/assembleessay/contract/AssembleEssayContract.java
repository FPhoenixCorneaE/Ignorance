package com.livelearn.ignorance.test.assembleessay.contract;

import java.util.List;

/**
 * Created by rth on 16/11/15.
 */
public class AssembleEssayContract {

    public interface IAssembleEssayView {

        void onEssayLoaded(List<String> datas);
    }

    public interface IAssembleEssayPresenter<V extends IAssembleEssayView> {

        void loadEssay();

        void release();
    }
}
