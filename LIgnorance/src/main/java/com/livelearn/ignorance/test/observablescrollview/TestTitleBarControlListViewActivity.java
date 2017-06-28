package com.livelearn.ignorance.test.observablescrollview;

import android.widget.ArrayAdapter;

import com.github.ksoichiro.observablescrollview.ObservableListView;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.test.observablescrollview.model.DummyData;

import butterknife.BindView;

public class TestTitleBarControlListViewActivity extends TestTitleBarControlBaseActivity<ObservableListView> {

    @BindView(R.id.osv_observable_scroll)
    ObservableListView osvObservableScroll;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_test_title_bar_control_list_view;
    }

    @Override
    protected ObservableListView createScrollable() {
        osvObservableScroll.setAdapter(new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, DummyData.getDummyData()));
        return osvObservableScroll;
    }
}
