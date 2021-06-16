package com.livelearn.ignorance.test.frescohelper.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.fresco.helper.ImageLoader;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.widget.TitleBar;

/**
 * 用于演示高斯模糊的实现
 *
 * Created by android_ls on 16/11/11.
 */

public class TestGifActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_fresco_gif;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        ((TitleBar) findViewById(R.id.tb_title)).setTitleText(className);

        String url = "http://img4.178.com/acg1/201506/227753817857/227754566617.gif";

        SimpleDraweeView simpleDraweeView = (SimpleDraweeView)findViewById(R.id.sdv_1);
        ImageLoader.loadImage(simpleDraweeView, url);
    }

    @Override
    public void setListeners() {

    }

}
