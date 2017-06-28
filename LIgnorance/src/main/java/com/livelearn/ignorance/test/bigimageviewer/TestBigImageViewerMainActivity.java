package com.livelearn.ignorance.test.bigimageviewer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.utils.IntentUtils;
import com.livelearn.ignorance.widget.TitleBar;

import butterknife.BindView;
import butterknife.OnClick;

public class TestBigImageViewerMainActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.btn_fresco_loader)
    Button btnFrescoLoader;

    @BindView(R.id.btn_glide_loader)
    Button btnGlideLoader;

    @BindView(R.id.btn_long_image)
    Button btnLongImage;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_big_image_viewer_main;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);
    }

    @Override
    public void setListeners() {

    }

    @OnClick({R.id.btn_fresco_loader, R.id.btn_glide_loader, R.id.btn_long_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_fresco_loader:
                IntentUtils.startActivity(mContext, TestFrescoLoaderActivity.class);
                break;
            case R.id.btn_glide_loader:
                IntentUtils.startActivity(mContext, TestGlideLoaderActivity.class);
                break;
            case R.id.btn_long_image:
                IntentUtils.startActivity(mContext, TestLongImageActivity.class);
                break;
        }
    }
}
