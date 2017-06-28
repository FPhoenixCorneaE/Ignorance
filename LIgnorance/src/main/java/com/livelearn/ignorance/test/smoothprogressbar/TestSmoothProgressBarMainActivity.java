package com.livelearn.ignorance.test.smoothprogressbar;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.utils.IntentUtils;
import com.livelearn.ignorance.widget.TitleBar;

import butterknife.BindView;
import butterknife.OnClick;
import fr.castor.flex.smoothprogressbar.SmoothProgressBar;
import fr.castor.flex.smoothprogressbar.SmoothProgressBarUtils;
import fr.castor.flex.smoothprogressbar.SmoothProgressDrawable;


public class TestSmoothProgressBarMainActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.btn_make_your_own)
    Button btnMakeYourOwn;

    @BindView(R.id.pb_accelerate_interpolator)
    ProgressBar pbAccelerateInterpolator;

    @BindView(R.id.spb_google_now)
    SmoothProgressBar spbGoogleNow;

    @BindView(R.id.spb_gradient)
    SmoothProgressBar spbGradient;

    @BindView(R.id.spb_pocket)
    SmoothProgressBar spbPocket;

    @BindView(R.id.btn_start)
    Button btnStart;

    @BindView(R.id.btn_finish)
    Button btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_smooth_progress_bar_main;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);

        pbAccelerateInterpolator.setIndeterminateDrawable(new SmoothProgressDrawable.Builder(this).interpolator(new AccelerateInterpolator()).build());

        spbPocket.setSmoothProgressDrawableBackgroundDrawable(
                SmoothProgressBarUtils.generateDrawableWithColors(
                        getResources().getIntArray(R.array.SmoothProgressBar_PocketBackground_Colors),
                        ((SmoothProgressDrawable) spbPocket.getIndeterminateDrawable()).getStrokeWidth()));
    }

    @Override
    public void setListeners() {

    }

    @OnClick({R.id.btn_make_your_own, R.id.btn_start, R.id.btn_finish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_make_your_own:
                IntentUtils.startActivity(mContext, TestCustomSmoothProgressBarActivity.class);
                break;
            case R.id.btn_start:
                spbPocket.progressiveStart();
                break;
            case R.id.btn_finish:
                spbPocket.progressiveStop();
                break;
        }
    }
}
