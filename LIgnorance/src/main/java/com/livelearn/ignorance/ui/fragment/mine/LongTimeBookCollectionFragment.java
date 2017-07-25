package com.livelearn.ignorance.ui.fragment.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseFragment;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.presenter.mine.LongTimeBookCollectionPresenter;
import com.livelearn.ignorance.ui.view.mine.LongTimeBookCollectionView;

import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * Created on 2017/7/18.
 */

public class LongTimeBookCollectionFragment extends BaseFragment {

    @BindView(R.id.v_long_time_book_collection)
    LongTimeBookCollectionView vLongTimeBookCollection;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_long_time_book_collection;
    }

    @Override
    public void initLayout(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mPresenter = new LongTimeBookCollectionPresenter(vLongTimeBookCollection);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void lazyFetchData() {

    }

    /**
     * 图书收藏
     *
     * @param isCollectedSuccess 收藏成功与否
     */
    @Subscriber(tag = Constant.BOOK_COLLECTION_LONG_TIME, mode = ThreadMode.MAIN)
    public void onCollection(boolean isCollectedSuccess) {
        if (isCollectedSuccess) {
            vLongTimeBookCollection.updateBookCollection();
        }
    }

    /**
     * 取消图书收藏
     *
     * @param isCancelCollectedSuccess 取消收藏成功与否
     */
    @Subscriber(tag = Constant.BOOK_COLLECTION_CANCEL_LONG_TIME, mode = ThreadMode.MAIN)
    public void onCancelCollection(boolean isCancelCollectedSuccess) {
        if (isCancelCollectedSuccess) {
            vLongTimeBookCollection.updateBookCollection();
        }
    }

    /**
     * 列表或者九宫格显示
     *
     * @param arrangementMode 排列方式
     */
    @Subscriber(tag = Constant.BOOK_COLLECTION_ARRANGEMENT_MODE, mode = ThreadMode.MAIN)
    public void onToggleArrangementMode(String arrangementMode) {
        vLongTimeBookCollection.toggleArrangementMode(arrangementMode);
    }
}
