package com.livelearn.ignorance.test.observablescrollview;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatImageView;

import com.github.ksoichiro.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.observablescrollview.ScrollState;
import com.github.ksoichiro.observablescrollview.ScrollUtils;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.utils.DisplayUtils;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.widget.TitleBar;
import com.nineoldandroids.view.ViewHelper;

import butterknife.BindView;

public class TestParallaxTitleBarScrollViewActivity extends BaseActivity implements ObservableScrollViewCallbacks {

    @BindView(R.id.iv_image)
    AppCompatImageView ivImage;

    @BindView(R.id.osv_observable_scroll)
    ObservableScrollView osvObservableScroll;

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    private int mParallaxImageHeight;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_parallax_title_bar_scroll_view;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);

        tbTitle.setTitleBarBackgroundResource(R.color.transparent);

        mParallaxImageHeight = DisplayUtils.dp2px(240F);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        onScrollChanged(osvObservableScroll.getCurrentScrollY(), false, false);
    }

    @Override
    public void setListeners() {
        osvObservableScroll.setScrollViewCallbacks(this);
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        int baseColor = ResourceUtils.getColor(R.color.title_bar);
        float alpha = Math.min(1, (float) scrollY / mParallaxImageHeight);
        tbTitle.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, baseColor));

        ViewHelper.setTranslationY(ivImage, scrollY / 2);
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }
}
