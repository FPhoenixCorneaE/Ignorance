package com.livelearn.ignorance.ui.activity.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.model.bean.UserInfo;
import com.livelearn.ignorance.presenter.mine.PhotoWallPresenter;
import com.livelearn.ignorance.ui.view.mine.IPhotoWallView;
import com.livelearn.ignorance.utils.SharedPreferencesUtils;
import com.livelearn.ignorance.utils.aliyunupload.UploadMultipleImageUtils;
import com.livelearn.ignorance.widget.TitleBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 照片墙
 * Created on 2017/8/10.
 */

public class PhotoWallActivity extends BaseActivity implements IPhotoWallView {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.rv_photo_wall)
    EasyRecyclerView rvPhotoWall;

    @BindView(R.id.btn_upload_photo)
    Button btnUploadPhoto;

    @BindView(R.id.fl_upload_photo)
    FrameLayout flUploadPhoto;

    @BindView(R.id.rl_delete_photo)
    RelativeLayout rlDeletePhoto;

    @BindView(R.id.fl_delete_photo)
    FrameLayout flDeletePhoto;

    private PhotoWallPresenter mPhotoWallPresenter;
    private UploadMultipleImageUtils mUploadMultipleImageUtils;
    private boolean isEditPhotoWall;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_photo_wall;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setLeftIconTintColor(R.color.color_pale)
                .setLeftText("返回")
                .setLeftTextColorRes(R.color.color_pale)
                .setTitleText(String.format(Locale.getDefault(), "照片墙(%d张)", getPhotoWallList().size()))
                .setTitleTextColorRes(R.color.color_pale)
                .setRightText("编辑")
                .setRightTextColorRes(R.color.color_pale);

        mPhotoWallPresenter = new PhotoWallPresenter(this);
        //设置适配器
        mPhotoWallPresenter.setPhotoWallAdapter();

        mUploadMultipleImageUtils = new UploadMultipleImageUtils(mContext, null);
    }

    @Override
    public void setListeners() {
        mPhotoWallPresenter.setListeners();
        //编辑、取消
        tbTitle.setOnClickRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEditPhotoWall = !isEditPhotoWall;
                mPhotoWallPresenter.editPhotoWall(isEditPhotoWall);
            }
        });
        //上传成功
        mUploadMultipleImageUtils.setOnUploadSuccessListener(new UploadMultipleImageUtils.OnUploadSuccessListener() {
            @Override
            public void onUploadSuccess(int position, String imageUrl) {
                mPhotoWallPresenter.addPhoto(imageUrl);
            }
        });
    }

    @Override
    public BaseActivity getmContext() {
        return mContext;
    }

    @Override
    public UserInfo getmUserInfo() {
        return Constant.getUserInfo();
    }

    @Override
    public void savePhotoWallData(String photoWallString) {
        SharedPreferencesUtils.put(getmUserInfo().getUserAccount(), Constant.PHOTO_WALL_LIST, photoWallString);
    }

    @Override
    public String getSavedPhotoWallString() {
        return SharedPreferencesUtils.getString(getmUserInfo().getUserAccount(), Constant.PHOTO_WALL_LIST);
    }

    @Override
    public List<String> getPhotoWallList() {
        String savedPhotoWallString = getSavedPhotoWallString();
        ArrayList<String> photoWallList = new ArrayList<>();
        if (null != savedPhotoWallString && !savedPhotoWallString.isEmpty()) {
            Collections.addAll(photoWallList, savedPhotoWallString.split("\\|"));
        }
        return photoWallList;
    }

    @Override
    public TitleBar getTitleBar() {
        return tbTitle;
    }

    @Override
    public EasyRecyclerView getRecyclerView() {
        return rvPhotoWall;
    }

    @Override
    public View getDeletePhotoView() {
        return flDeletePhoto;
    }

    @Override
    public View getUploadPhotoView() {
        return flUploadPhoto;
    }

    @OnClick({R.id.btn_upload_photo, R.id.rl_delete_photo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_upload_photo:/*上传照片*/
                mUploadMultipleImageUtils.showPhotoWallSheetDialog(null);
                break;
            case R.id.rl_delete_photo:/*删除照片*/
                mPhotoWallPresenter.deletePhoto();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null != mUploadMultipleImageUtils) {
            mUploadMultipleImageUtils.onActivityResult(requestCode, resultCode, data);
        }
    }
}
