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
 * 用于演示显示本地资源的图片
 *
 * Created by android_ls on 16/11/11.
 */

public class TestMeiziActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_fresco_meizi;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        ((TitleBar) findViewById(R.id.tb_title)).setTitleText(className);

        SimpleDraweeView simpleDraweeView = (SimpleDraweeView)findViewById(R.id.sdv_1);
        ViewGroup.LayoutParams lvp = simpleDraweeView.getLayoutParams();
        lvp.width = DensityUtil.getDisplayWidth(this);
        simpleDraweeView.setAspectRatio(0.6f); // 设置宽高比

        ImageLoader.loadDrawable(simpleDraweeView, R.mipmap.pic_meizi,
                DensityUtil.getDisplayWidth(this), DensityUtil.getDisplayHeight(this));
    }

    @Override
    public void setListeners() {

    }

}
