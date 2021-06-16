package com.livelearn.ignorance.presenter.mine;

import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;

import com.facebook.fresco.helper.photo.PictureBrowse;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.base.RxPresenter;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.ui.adapter.mine.PhotoWallAdapter;
import com.livelearn.ignorance.ui.view.mine.IPhotoWallView;
import com.livelearn.ignorance.utils.DisplayUtils;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created on 2017/8/16.
 */

public class PhotoWallPresenter extends RxPresenter {

    private IPhotoWallView iPhotoWallView;
    private BaseActivity mContext;
    private GridLayoutManager mLayoutManager;
    private PhotoWallAdapter mPhotoWallAdapter;

    public PhotoWallPresenter(IPhotoWallView iPhotoWallView) {
        this.iPhotoWallView = iPhotoWallView;
        this.mContext = iPhotoWallView.getmContext();
    }

    /**
     * 设置照片墙适配器
     */
    public void setPhotoWallAdapter() {
        iPhotoWallView.getRecyclerView().setLayoutManager(mLayoutManager = new GridLayoutManager(mContext, 3));
        //item间距
        SpaceDecoration itemDecoration = new SpaceDecoration(DisplayUtils.dp2px(3F));
        itemDecoration.setPaddingEdgeSide(false);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        iPhotoWallView.getRecyclerView().addItemDecoration(itemDecoration);

        mPhotoWallAdapter = new PhotoWallAdapter(mContext, iPhotoWallView.getPhotoWallList());
        iPhotoWallView.getRecyclerView().setAdapterWithProgress(mPhotoWallAdapter);

        //空的布局
        iPhotoWallView.getRecyclerView().setEmptyView(R.layout.custom_view_empty_photo_wall);
        //加载中布局
        iPhotoWallView.getRecyclerView().setProgressView(R.layout.custom_view_loading_rotating_chrysanthemum);
        //手动显示空布局
        if (iPhotoWallView.getPhotoWallList().isEmpty()) {
            iPhotoWallView.getRecyclerView().showEmpty();
        }
    }

    /**
     * 添加照片
     *
     * @param imageUrl 图片地址
     */
    public void addPhoto(String imageUrl) {
        mPhotoWallAdapter.add(imageUrl);
        iPhotoWallView.getTitleBar().setTitleText(String.format(Locale.getDefault(), "照片墙(%d张)", mPhotoWallAdapter.getAllData().size()));

        String savedPhotoWallString = iPhotoWallView.getSavedPhotoWallString();
        StringBuilder photoWallBuilder;
        if (null != savedPhotoWallString && !savedPhotoWallString.isEmpty()) {
            photoWallBuilder = new StringBuilder(savedPhotoWallString);
            photoWallBuilder.append("|");
        } else {
            photoWallBuilder = new StringBuilder();
        }
        photoWallBuilder.append(imageUrl);
        iPhotoWallView.savePhotoWallData(photoWallBuilder.toString());

        //通知编辑资料页面更新
        EventBus.getDefault().post(mPhotoWallAdapter.getAllData(), Constant.PHOTO_WALL_LIST);
    }

    /**
     * 编辑照片墙
     *
     * @param isEditPhotoWall true为编辑，false为取消编辑
     */
    public void editPhotoWall(boolean isEditPhotoWall) {
        if (isEditPhotoWall) {
            iPhotoWallView.getTitleBar().setRightText("取消");
            iPhotoWallView.getDeletePhotoView().setVisibility(View.VISIBLE);
            iPhotoWallView.getUploadPhotoView().setVisibility(View.GONE);
        } else {
            iPhotoWallView.getTitleBar().setRightText("编辑");
            iPhotoWallView.getUploadPhotoView().setVisibility(View.VISIBLE);
            iPhotoWallView.getDeletePhotoView().setVisibility(View.GONE);
        }
        mPhotoWallAdapter.setEnableEditPhotoWall(isEditPhotoWall);
    }

    /**
     * 删除照片
     */
    public void deletePhoto() {
        ArrayList<String> selectedPhotoWall = mPhotoWallAdapter.getSelectedPhotoWall();
        if (selectedPhotoWall.isEmpty()) {
            iPhotoWallView.getTitleBar().setRightText("编辑");
            iPhotoWallView.getUploadPhotoView().setVisibility(View.VISIBLE);
            iPhotoWallView.getDeletePhotoView().setVisibility(View.GONE);

            mPhotoWallAdapter.setEnableEditPhotoWall(false);
            return;
        }
        for (String photoUrl : selectedPhotoWall) {
            mPhotoWallAdapter.remove(photoUrl);
        }

        iPhotoWallView.getTitleBar().setRightText("编辑");
        iPhotoWallView.getUploadPhotoView().setVisibility(View.VISIBLE);
        iPhotoWallView.getDeletePhotoView().setVisibility(View.GONE);
        iPhotoWallView.getTitleBar().setTitleText(String.format(Locale.getDefault(), "照片墙(%d张)", mPhotoWallAdapter.getAllData().size()));

        mPhotoWallAdapter.setEnableEditPhotoWall(false);
        mPhotoWallAdapter.notifyDataSetChanged();

        if (!mPhotoWallAdapter.getAllData().isEmpty()) {
            StringBuilder photoWallBuilder = new StringBuilder(mPhotoWallAdapter.getAllData().get(0));
            for (int i = 1; i < mPhotoWallAdapter.getAllData().size(); i++) {
                photoWallBuilder.append("|").append(mPhotoWallAdapter.getAllData().get(i));
            }
            iPhotoWallView.savePhotoWallData(photoWallBuilder.toString());
        } else {
            iPhotoWallView.savePhotoWallData("");
        }

        //通知编辑资料页面更新
        EventBus.getDefault().post(mPhotoWallAdapter.getAllData(), Constant.PHOTO_WALL_LIST);
    }

    /**
     * 设置照片墙item点击监听
     */
    public void setListeners() {
        mPhotoWallAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                PictureBrowse.newBuilder(mContext)
                        .setLayoutManager(mLayoutManager)
                        .setCurrentPosition(position)
                        .setPhotoStringList(iPhotoWallView.getPhotoWallList())
                        //动画效果
                        .enabledAnimation(true)
                        .build()
                        .start();
            }
        });
    }
}
