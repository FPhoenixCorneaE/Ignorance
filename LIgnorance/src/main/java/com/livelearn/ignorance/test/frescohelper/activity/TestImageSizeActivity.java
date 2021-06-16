package com.livelearn.ignorance.test.frescohelper.activity;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.fresco.helper.ImageLoader;
import com.facebook.fresco.helper.utils.DensityUtil;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.widget.TitleBar;

/**
 * 用于演示加载大图
 *
 * Created by android_ls on 16/11/11.
 */

public class TestImageSizeActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_fresco_big;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        ((TitleBar) findViewById(R.id.tb_title)).setTitleText(className);

        String url = "http://feed.chujianapp.com/20161108/452ab5752287a99a1b5387e2cd849006.jpg@1080w";
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView)findViewById(R.id.sdv_1);
        // ImageLoader.loadImage(simpleDraweeView, url, new SingleImageControllerListener(simpleDraweeView));

        ViewGroup.LayoutParams vpl = simpleDraweeView.getLayoutParams();
        vpl.width = DensityUtil.getDisplayWidth(this) - DensityUtil.dipToPixels(this, 22);
        vpl.height = DensityUtil.getDisplayWidth(this) - DensityUtil.dipToPixels(this, 20);

        ImageLoader.loadImage(simpleDraweeView, url);

//        ImageLoader.loadImage(simpleDraweeView, url,
//                DensityUtil.getDisplayWidth(this) - DensityUtil.dipToPixels(this, 22),
//                DensityUtil.getDisplayWidth(this) - DensityUtil.dipToPixels(this, 20));

    }

    @Override
    public void setListeners() {

    }

}
