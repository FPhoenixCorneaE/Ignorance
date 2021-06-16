package com.livelearn.ignorance.presenter.mine;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;

import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.base.RxPresenter;
import com.livelearn.ignorance.ui.activity.mine.PhotoWallActivity;
import com.livelearn.ignorance.ui.adapter.mine.PhotoWallAdapter;
import com.livelearn.ignorance.ui.view.mine.IEditDataView;
import com.livelearn.ignorance.utils.IntentUtils;

import java.util.List;
import java.util.Locale;

/**
 * Created on 2017/8/10.
 */

public class EditDataPresenter extends RxPresenter {

    private IEditDataView iEditDataView;
    private BaseActivity mContext;
    private PhotoWallAdapter mPhotoWallAdapter;

    public EditDataPresenter(IEditDataView iEditDataView) {
        this.iEditDataView = iEditDataView;
        this.mContext = iEditDataView.getmContext();
    }

    /**
     * 设置照片墙适配器
     */
    public void setPhotoWallAdapter() {
        iEditDataView.getRecyclerView().setLayoutManager(new GridLayoutManager(mContext, 3));
        mPhotoWallAdapter = new PhotoWallAdapter(mContext, iEditDataView.getPhotoWallList());
        iEditDataView.getRecyclerView().setAdapter(mPhotoWallAdapter);

        if (!mPhotoWallAdapter.getAllData().isEmpty()) {
            iEditDataView.getRecyclerView().setVisibility(View.VISIBLE);
            iEditDataView.getPhotoWallCountTextView().setVisibility(View.VISIBLE);
            iEditDataView.getPhotoWallCountTextView().setText(String.format(Locale.getDefault(), "%d张", mPhotoWallAdapter.getAllData().size()));
        }
    }

    /**
     * 设置照片墙item点击监听
     */
    public void setOnPhotoWallItemClickListeners() {
        mPhotoWallAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                IntentUtils.startActivity(mContext, PhotoWallActivity.class);
            }
        });
    }

    /**
     * 更新照片墙
     *
     * @param photoWallList 照片墙列表
     */
    public void updatePhotoWall(List<String> photoWallList) {
        if (photoWallList.isEmpty()) {
            iEditDataView.getRecyclerView().setVisibility(View.GONE);
            iEditDataView.getPhotoWallCountTextView().setVisibility(View.GONE);
        } else {
            iEditDataView.getRecyclerView().setVisibility(View.VISIBLE);
            iEditDataView.getPhotoWallCountTextView().setVisibility(View.VISIBLE);

            mPhotoWallAdapter.clear();
            if (photoWallList.size() > 3) {
                mPhotoWallAdapter.addAll(photoWallList.subList(0, 3));
            } else {
                mPhotoWallAdapter.addAll(photoWallList);
            }

            iEditDataView.getPhotoWallCountTextView().setText(String.format(Locale.getDefault(), "%d张", photoWallList.size()));
        }
    }
}
