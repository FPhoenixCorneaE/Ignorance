package com.livelearn.ignorance.test.indicatorbox;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.widget.TitleBar;
import com.livelearn.ignorance.widget.indicatorbox.ShrinkButton;

import butterknife.BindView;
import butterknife.OnClick;

public class TestShrinkButtonActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.sb_sign_in)
    ShrinkButton sbSignIn;

    @BindView(R.id.tv_pause)
    TextView tvPause;

    @BindView(R.id.tv_reset)
    TextView tvReset;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_shrink_button;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);
    }

    @Override
    public void setListeners() {

    }

    @OnClick({R.id.tv_pause, R.id.tv_reset})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_pause:
                sbSignIn.stop();
                break;
            case R.id.tv_reset:
                if (sbSignIn.getAnimationState() == ShrinkButton.STATE_PROGRESSING ||
                        sbSignIn.getAnimationState() == ShrinkButton.STATE_SHRINKED ||
                        sbSignIn.getAnimationState() == ShrinkButton.STATE_SHRINKING ||
                        sbSignIn.getAnimationState() == ShrinkButton.STATE_EXPANDING)
                    sbSignIn.reset();
                break;
        }
    }
}
