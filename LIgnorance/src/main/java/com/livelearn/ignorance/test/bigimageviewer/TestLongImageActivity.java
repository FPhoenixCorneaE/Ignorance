package com.livelearn.ignorance.test.bigimageviewer;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.apkfuns.logutils.LogUtils;
import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;
import com.github.piasy.biv.BigImageViewer;
import com.github.piasy.biv.indicator.ProgressPieIndicator;
import com.github.piasy.biv.loader.fresco.FrescoImageLoader;
import com.github.piasy.biv.view.BigImageView;
import com.github.piasy.biv.view.ImageSaveCallback;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.utils.ToastUtils;
import com.livelearn.ignorance.widget.TitleBar;

import butterknife.BindView;
import butterknife.OnClick;

public class TestLongImageActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.biv_big_image)
    BigImageView bivBigImage;

    @BindView(R.id.btn_load)
    Button btnLoad;

    @Override
    public int getLayoutResource() {
        BigImageViewer.initialize(FrescoImageLoader.with(getApplicationContext()));
        return R.layout.activity_test_big_image_viewer;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);

        bivBigImage.setProgressIndicator(new ProgressPieIndicator());
    }

    @Override
    public void setListeners() {
        bivBigImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NormalListDialog listDialog = new NormalListDialog(mContext, new String[]{"保存图片", "图片地址"});
                listDialog.isTitleShow(false)
                        .widthScale(0.6f)
                        .show();
                listDialog.setOnOperItemClickL(new OnOperItemClickL() {
                    @Override
                    public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0:
                                bivBigImage.saveImageIntoGallery();
                                break;
                            case 1:
                                ToastUtils.showToast(bivBigImage.currentImageFile());
                                break;
                        }
                    }
                });
            }
        });
        bivBigImage.setImageSaveCallback(new ImageSaveCallback() {
            @Override
            public void onSuccess(String uri) {
                ToastUtils.showToast("Save Successfully");
            }

            @Override
            public void onFail(Throwable t) {
                LogUtils.e(t);
                ToastUtils.showToast("Save Failure");
            }
        });
    }

    @OnClick(R.id.btn_load)
    public void onViewClicked() {
        bivBigImage.showImage(
                Uri.parse("http://ww1.sinaimg.cn/mw690/005Fj2RDgw1f9mvl4pivvj30c82ougw3.jpg"));
    }
}
