package com.livelearn.ignorance.base;

public interface BasePresenter<V extends VacantView> {

    void attachView(V view);

    void detachView();

}
