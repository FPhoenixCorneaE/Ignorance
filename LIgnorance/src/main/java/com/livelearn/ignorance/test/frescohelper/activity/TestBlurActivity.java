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
 * 用于演示高斯模糊的实现
 *
 * Created by android_ls on 16/11/11.
 */

public class TestBlurActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_fresco_blur;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        ((TitleBar) findViewById(R.id.tb_title)).setTitleText(className);

        String url = "http://a.hiphotos.baidu.com/image/pic/item/55e736d12f2eb938d3de795ad0628535e4dd6fe2.jpg";

        SimpleDraweeView simpleDraweeView = (SimpleDraweeView)findViewById(R.id.sdv_1);
        simpleDraweeView.setAspectRatio(0.7f);
        ViewGroup.LayoutParams lvp = simpleDraweeView.getLayoutParams();
        lvp.width = DensityUtil.getDisplayWidth(this);

        ImageLoader.loadImageBlur(simpleDraweeView, url,
                DensityUtil.getDisplayWidth(this), DensityUtil.getDisplayHeight(this));
    }

    @Override
    public void setListeners() {

    }

}
