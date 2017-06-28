package com.livelearn.ignorance.ui.fragment.book;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseFragment;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.eventbusevent.BookClassEvent;
import com.livelearn.ignorance.presenter.book.LongTimeBookPresenter;
import com.livelearn.ignorance.ui.view.book.LongTimeBookView;

import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * Created on 2017/5/8.
 */

public class LongTimeBookFragment extends BaseFragment {

    @BindView(R.id.v_long_time_book)
    LongTimeBookView vLongTimeBook;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_long_time_book;
    }

    @Override
    public void initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mPresenter = new LongTimeBookPresenter(this, vLongTimeBook);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void lazyFetchData() {

    }

    /**
     * tag有默认值，默认的tag,执行在UI线程
     * 自定义tag,当用户post事件时,只有指定了该tag的事件才会触发该函数,执行在UI线程
     * ThreadMode.MAIN 默认事件执行的线程
     * ThreadMode.POST post函数在哪个线程执行,该函数就执行在哪个线程
     * ThreadMode.ASYNC 执行在一个独立的线程
     */
    @Subscriber(tag = Constant.BOOK_CLASS, mode = ThreadMode.MAIN)
    private void updateCurrentItem(BookClassEvent bookClassEvent) {
        int position = bookClassEvent.getPosition();
        vLongTimeBook.setCurrentItem(position);
    }
}
