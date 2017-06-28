package com.livelearn.ignorance.test.bigimageviewer;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import com.github.piasy.biv.BigImageViewer;
import com.github.piasy.biv.indicator.ProgressPieIndicator;
import com.github.piasy.biv.loader.glide.GlideImageLoader;
import com.github.piasy.biv.view.BigImageView;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.widget.TitleBar;

import butterknife.BindView;
import butterknife.OnClick;

public class TestGlideLoaderActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.biv_big_image)
    BigImageView bivBigImage;

    @BindView(R.id.btn_load)
    Button btnLoad;

    @Override
    public int getLayoutResource() {
        BigImageViewer.initialize(GlideImageLoader.with(getApplicationContext()));
        return R.layout.activity_test_big_image_viewer;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);
    }

    @Override
    public void setListeners() {

    }

    @OnClick(R.id.btn_load)
    public void onViewClicked() {
        bivBigImage.setProgressIndicator(new ProgressPieIndicator());
        bivBigImage.showImage(
                Uri.parse("http://img1.imgtn.bdimg.com/it/u=1520386803,778399414&fm=21&gp=0.jpg"),
                Uri.parse("http://youimg1.c-ctrip.com/target/tg/773/732/734/7ca19416b8cd423f8f6ef2d08366b7dc.jpg")
        );
    }
}
