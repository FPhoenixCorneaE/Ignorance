package com.livelearn.ignorance.test.easyrecyclerview.collapsing;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.test.easyrecyclerview.DataProvider;
import com.livelearn.ignorance.test.easyrecyclerview.header.TestBannerAdapter;
import com.livelearn.ignorance.test.easyrecyclerview.loadmore.TestPersonAdapter;

/**
 * Created on 2016/3/20.
 */
public class TestCollapsingActivity extends BaseActivity {
    EasyRecyclerView recyclerView;
    TestPersonAdapter adapter;
    private Handler handler = new Handler();

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_collapsing;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter = new TestPersonAdapter(this));
        adapter.setMore(R.layout.custom_view_footer_loadmore_easy_recycler_view, new RecyclerArrayAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.addAll(DataProvider.getPersonList(0));
                    }
                }, 1000);
            }
        });
        adapter.addAll(DataProvider.getPersonList(0));
        RollPagerView rollPagerView = (RollPagerView) findViewById(R.id.rollPagerView);
        rollPagerView.setHintView(new ColorPointHintView(this, Color.YELLOW, Color.GRAY));
        rollPagerView.setAdapter(new TestBannerAdapter(TestCollapsingActivity.this));
    }

    @Override
    public void setListeners() {

    }
}
