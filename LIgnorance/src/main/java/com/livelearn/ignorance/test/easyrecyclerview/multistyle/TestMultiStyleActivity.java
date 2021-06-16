package com.livelearn.ignorance.test.easyrecyclerview.multistyle;

import android.graphics.Color;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.jude.rollviewpager.Util;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.test.easyrecyclerview.DataProvider;
import com.livelearn.ignorance.widget.TitleBar;

/**
 * Created on 2016/1/6.
 */
public class TestMultiStyleActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_recyclerview;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        ((TitleBar) findViewById(R.id.tb_title)).setTitleText(className);

        EasyRecyclerView recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setProgressView(R.layout.custom_view_loading_easy_recycler_view);

        DividerDecoration itemDecoration = new DividerDecoration(Color.GRAY, Util.dip2px(this,0.5f), Util.dip2px(this,72),0);
        recyclerView.addItemDecoration(itemDecoration);

        TestPersonWithAdAdapter adapter = new TestPersonWithAdAdapter(this);
        adapter.addAll(DataProvider.getPersonWithAds(0));
        recyclerView.setAdapterWithProgress(adapter);
    }

    @Override
    public void setListeners() {

    }
}
