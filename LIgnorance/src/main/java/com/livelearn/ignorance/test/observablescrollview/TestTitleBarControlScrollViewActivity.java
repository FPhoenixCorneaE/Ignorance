package com.livelearn.ignorance.test.observablescrollview;

import com.github.ksoichiro.observablescrollview.ObservableScrollView;
import com.livelearn.ignorance.R;

import butterknife.BindView;

public class TestTitleBarControlScrollViewActivity extends TestTitleBarControlBaseActivity<ObservableScrollView> {

    @BindView(R.id.osv_observable_scroll)
    ObservableScrollView osvObservableScroll;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_test_title_bar_control_scroll_view;
    }

    @Override
    protected ObservableScrollView createScrollable() {
        return osvObservableScroll;
    }
}
