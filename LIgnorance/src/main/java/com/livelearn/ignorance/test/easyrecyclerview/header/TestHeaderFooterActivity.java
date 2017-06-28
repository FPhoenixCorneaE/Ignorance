package com.livelearn.ignorance.test.easyrecyclerview.header;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.Util;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.test.easyrecyclerview.DataProvider;
import com.livelearn.ignorance.test.easyrecyclerview.Utils;
import com.livelearn.ignorance.test.easyrecyclerview.loadmore.TestPersonAdapter;
import com.livelearn.ignorance.widget.TitleBar;

/**
 * Created on 2016/1/6.
 */
public class TestHeaderFooterActivity extends BaseActivity {
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
        TestPersonAdapter adapter;
        recyclerView.setAdapter(adapter = new TestPersonAdapter(this));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerDecoration itemDecoration = new DividerDecoration(Color.GRAY, Util.dip2px(this, 0.5f), Util.dip2px(this, 72), 0);
        itemDecoration.setDrawLastItem(true);
        itemDecoration.setDrawHeaderFooter(true);
        recyclerView.addItemDecoration(itemDecoration);

        adapter.addAll(DataProvider.getPersonList(0));
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                RollPagerView header = new RollPagerView(TestHeaderFooterActivity.this);
                header.setHintView(new ColorPointHintView(TestHeaderFooterActivity.this, Color.YELLOW, Color.GRAY));
                header.setHintPadding(0, 0, 0, (int) Utils.convertDpToPixel(8, TestHeaderFooterActivity.this));
                header.setPlayDelay(2000);
                header.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) Utils.convertDpToPixel(200, TestHeaderFooterActivity.this)));
                header.setAdapter(new TestBannerAdapter(TestHeaderFooterActivity.this));
                return header;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });
        adapter.addFooter(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                TextView tv = new TextView(TestHeaderFooterActivity.this);
                tv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) Utils.convertDpToPixel(56, TestHeaderFooterActivity.this)));
                tv.setGravity(Gravity.CENTER);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                tv.setText("(-_-)/~~~死宅真是恶心");
                return tv;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });
    }

    @Override
    public void setListeners() {

    }
}
