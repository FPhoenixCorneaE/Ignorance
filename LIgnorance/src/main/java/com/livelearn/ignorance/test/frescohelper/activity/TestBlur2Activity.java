package com.livelearn.ignorance.test.frescohelper.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.facebook.fresco.helper.ImageLoader;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.widget.TitleBar;

/**
 * 用于演示对任意View的背景，进行高斯模糊效果的实现
 *
 * Created by android_ls on 16/11/11.
 */

public class TestBlur2Activity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_fresco_blur2;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        ((TitleBar) findViewById(R.id.tb_title)).setTitleText(className);

        String url = "http://a.hiphotos.baidu.com/image/pic/item/55e736d12f2eb938d3de795ad0628535e4dd6fe2.jpg";
        View view = findViewById(R.id.ll_bg);
        ImageLoader.loadImageBlur(view, url);
    }

    @Override
    public void setListeners() {

    }
}
