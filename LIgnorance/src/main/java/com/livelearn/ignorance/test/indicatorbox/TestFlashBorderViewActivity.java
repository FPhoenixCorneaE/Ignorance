package com.livelearn.ignorance.test.indicatorbox;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.widget.TitleBar;
import com.livelearn.ignorance.widget.indicatorbox.FlashBorderView;

import butterknife.BindView;
import butterknife.OnClick;

public class TestFlashBorderViewActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.fbv_flash_border)
    FlashBorderView fbvFlashBorder;

    private ValueAnimator valueAnimator;
    private ObjectAnimator shrinkInAnimator;
    private ObjectAnimator expandOutAnimator;
    private TextView textView;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_flash_border_view;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);

        valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(1000);

        textView = new TextView(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER_VERTICAL;
        textView.setTextSize(16);
        textView.setTextColor(ResourceUtils.getColor(R.color.color_dark_black));
        textView.setPadding(0, 3, 0, 3);
        textView.setGravity(Gravity.CENTER);
        fbvFlashBorder.addView(textView, lp);

        shrinkInAnimator = ObjectAnimator.ofFloat(textView, "scaleY", 1.0f, 0);
        shrinkInAnimator.setDuration(500);
        expandOutAnimator = ObjectAnimator.ofFloat(textView, "scaleY", 0, 1.0f);
        expandOutAnimator.setDuration(500);
    }

    @Override
    public void setListeners() {
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                fbvFlashBorder.setmFraction((float) animation.getAnimatedValue());
                fbvFlashBorder.invalidate();   //from UI thread
                fbvFlashBorder.postInvalidate();   //from non-UI thread
            }
        });

        shrinkInAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                textView.setText("闪现边框开始闪现");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                textView.setText("闪现边框闪现结束");
                expandOutAnimator.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @OnClick(R.id.fbv_flash_border)
    public void onClick() {
        valueAnimator.start();
        shrinkInAnimator.start();
    }
}
