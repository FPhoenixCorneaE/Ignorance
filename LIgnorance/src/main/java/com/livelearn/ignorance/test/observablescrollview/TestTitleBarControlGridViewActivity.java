package com.livelearn.ignorance.test.observablescrollview;

import android.widget.ArrayAdapter;

import com.github.ksoichiro.observablescrollview.ObservableGridView;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.test.observablescrollview.model.DummyData;

import butterknife.BindView;

public class TestTitleBarControlGridViewActivity extends TestTitleBarControlBaseActivity<ObservableGridView> {

    @BindView(R.id.osv_observable_scroll)
    ObservableGridView osvObservableScroll;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_test_title_bar_control_grid_view;
    }

    @Override
    protected ObservableGridView createScrollable() {
        osvObservableScroll.setAdapter(new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, DummyData.getDummyData()));
        return osvObservableScroll;
    }
}
