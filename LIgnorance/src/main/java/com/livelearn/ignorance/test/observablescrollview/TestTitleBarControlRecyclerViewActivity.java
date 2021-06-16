package com.livelearn.ignorance.test.observablescrollview;


import androidx.recyclerview.widget.LinearLayoutManager;

import com.github.ksoichiro.observablescrollview.ObservableRecyclerView;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.test.observablescrollview.adapter.TestSimpleRecyclerAdapter;
import com.livelearn.ignorance.test.observablescrollview.model.DummyData;

import butterknife.BindView;

public class TestTitleBarControlRecyclerViewActivity extends TestTitleBarControlBaseActivity<ObservableRecyclerView> {

    @BindView(R.id.osv_observable_scroll)
    ObservableRecyclerView osvObservableScroll;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_test_title_bar_control_recycler_view;
    }

    @Override
    protected ObservableRecyclerView createScrollable() {
        osvObservableScroll.setLayoutManager(new LinearLayoutManager(mContext));
        osvObservableScroll.setHasFixedSize(true);
        osvObservableScroll.setAdapter(new TestSimpleRecyclerAdapter(mContext, DummyData.getDummyData()));
        return osvObservableScroll;
    }
}
