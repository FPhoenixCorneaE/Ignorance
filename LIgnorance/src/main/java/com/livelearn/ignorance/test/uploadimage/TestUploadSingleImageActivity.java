package com.livelearn.ignorance.test.uploadimage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.fresco.helper.ImageLoader;
import com.facebook.fresco.helper.Phoenix;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.callbacklistener.OnUploadImgCallback;
import com.livelearn.ignorance.exception.OSSException;
import com.livelearn.ignorance.utils.GlideUtils;
import com.livelearn.ignorance.utils.aliyunupload.UploadSingleImageUtils;
import com.livelearn.ignorance.widget.TitleBar;

import butterknife.BindView;
import butterknife.OnClick;

public class TestUploadSingleImageActivity extends BaseActivity {

    @BindView(R.id.ll_root)
    LinearLayout llRoot;

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.sdv_show_sheet_dialog_crop)
    SimpleDraweeView sdvShowSheetDialogCrop;

    @BindView(R.id.sdv_show_sheet_dialog_nocrop)
    SimpleDraweeView sdvShowSheetDialogNocrop;

    @BindView(R.id.sdv_open_camera)
    SimpleDraweeView sdvOpenCamera;

    @BindView(R.id.sdv_open_album)
    SimpleDraweeView sdvOpenAlbum;

    @BindView(R.id.iv_show_sheet_dialog_crop)
    ImageView ivShowSheetDialogCrop;

    @BindView(R.id.tv_show_sheet_dialog_crop)
    TextView tvShowSheetDialogCrop;

    @BindView(R.id.tv_show_sheet_dialog_nocrop)
    TextView tvShowSheetDialogNocrop;

    @BindView(R.id.tv_open_camera)
    TextView tvOpenCamera;

    @BindView(R.id.tv_open_album)
    TextView tvOpenAlbum;

    private UploadSingleImageUtils uploadSingleImageUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getLayoutResource() {
        Phoenix.init(mContext);
        return R.layout.activity_test_upload_single_image;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        ((TitleBar) findViewById(R.id.tb_title)).setTitleText(className);

        String url = "http://img02.tooopen.com/images/20141222/sy_77675876983.jpg";
        Phoenix.with(sdvShowSheetDialogCrop).load(url);

        String url2 = "http://img.hb.aicdn.com/d2024a8a998c8d3e4ba842e40223c23dfe1026c8bbf3-OudiPA_fw580";
        Phoenix.with(sdvShowSheetDialogNocrop).load(url2);

        String url3 = "http://b.hiphotos.baidu.com/zhidao/pic/item/77c6a7efce1b9d16249b0023f5deb48f8c546410.jpg";
        Phoenix.with(sdvOpenCamera).load(url3);

        Phoenix.with(sdvOpenAlbum).load(url);
    }

    @Override
    public void setListeners() {

    }

    @OnClick({R.id.sdv_show_sheet_dialog_crop, R.id.sdv_show_sheet_dialog_nocrop, R.id.sdv_open_camera, R.id.sdv_open_album})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sdv_show_sheet_dialog_crop:
                uploadSingleImageUtils = new UploadSingleImageUtils(mContext, true);
                uploadSingleImageUtils.showSheetDialog(llRoot, new OnUploadImgCallback() {
                    @Override
                    public void onProgress(float percent, long currentSize, long totalSize) {

                    }

                    @Override
                    public void onFailure(String imagePath, OSSException ossException) {

                    }

                    @Override
                    public void onSuccess(final String imagePath) {
                        tvShowSheetDialogCrop.setText(imagePath);
                        ImageLoader.loadImage(sdvShowSheetDialogCrop, imagePath);
                        GlideUtils.setupCircleImage(mContext, ivShowSheetDialogCrop, imagePath);
                    }
                });
                break;
            case R.id.sdv_show_sheet_dialog_nocrop:
                uploadSingleImageUtils = new UploadSingleImageUtils(mContext, false);
                uploadSingleImageUtils.showSheetDialog(llRoot, new OnUploadImgCallback() {
                    @Override
                    public void onProgress(float percent, long currentSize, long totalSize) {

                    }

                    @Override
                    public void onFailure(String imagePath, OSSException ossException) {

                    }

                    @Override
                    public void onSuccess(final String imagePath) {
                        tvShowSheetDialogNocrop.setText(imagePath);
                        ImageLoader.loadImage(sdvShowSheetDialogNocrop, imagePath);
                    }
                });
                break;
            case R.id.sdv_open_camera:
                uploadSingleImageUtils = new UploadSingleImageUtils(mContext, true);
                uploadSingleImageUtils.openCamera(new OnUploadImgCallback() {
                    @Override
                    public void onProgress(float percent, long currentSize, long totalSize) {

                    }

                    @Override
                    public void onFailure(String imagePath, OSSException ossException) {

                    }

                    @Override
                    public void onSuccess(final String imagePath) {
                        tvOpenCamera.setText(imagePath);
                        ImageLoader.loadImage(sdvOpenCamera, imagePath);
                    }
                });
                break;
            case R.id.sdv_open_album:
                uploadSingleImageUtils = new UploadSingleImageUtils(mContext, true);
                uploadSingleImageUtils.openAlbum(new OnUploadImgCallback() {
                    @Override
                    public void onProgress(float percent, long currentSize, long totalSize) {

                    }

                    @Override
                    public void onFailure(String imagePath, OSSException ossException) {

                    }

                    @Override
                    public void onSuccess(final String imagePath) {
                        tvOpenAlbum.setText(imagePath);
                        ImageLoader.loadImage(sdvOpenAlbum, imagePath);
                    }
                });
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uploadSingleImageUtils.onActivityResult(requestCode, resultCode, data);
    }
}
