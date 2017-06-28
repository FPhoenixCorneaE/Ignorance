package com.livelearn.ignorance.test.easyrecyclerview.staggeredgrid;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.test.easyrecyclerview.DataProvider;
import com.livelearn.ignorance.test.easyrecyclerview.Utils;
import com.livelearn.ignorance.test.easyrecyclerview.header.TestBannerAdapter;
import com.livelearn.ignorance.widget.TitleBar;

/**
 * Created on 2016/6/7.
 */
public class TestStaggeredGridActivity extends BaseActivity {
    private EasyRecyclerView recyclerView;
    private TestImageAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_recyclerview;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        ((TitleBar) findViewById(R.id.tb_title)).setTitleText(className);

        recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter = new TestImageAdapter(this));
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                RollPagerView header = new RollPagerView(TestStaggeredGridActivity.this);
                header.setHintView(new ColorPointHintView(TestStaggeredGridActivity.this, Color.YELLOW, Color.GRAY));
                header.setHintPadding(0, 0, 0, (int) Utils.convertDpToPixel(8, TestStaggeredGridActivity.this));
                header.setPlayDelay(2000);
                header.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) Utils.convertDpToPixel(200, TestStaggeredGridActivity.this)));
                header.setAdapter(new TestBannerAdapter(TestStaggeredGridActivity.this));
                return header;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });
        SpaceDecoration itemDecoration = new SpaceDecoration((int) Utils.convertDpToPixel(8,this));
        itemDecoration.setPaddingEdgeSide(true);
        itemDecoration.setPaddingStart(true);
        itemDecoration.setPaddingHeaderFooter(false);
        recyclerView.addItemDecoration(itemDecoration);

        adapter.setNoMore(R.layout.custom_view_footer_nomore_easy_recycler_view);

        addData();
    }

    @Override
    public void setListeners() {
        adapter.setMore(R.layout.custom_view_footer_loadmore_easy_recycler_view, new RecyclerArrayAdapter.OnMoreListener() {
            @Override
            public void onMoreShow() {
                addData();
            }

            @Override
            public void onMoreClick() {

            }
        });

        recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.clear();
                        adapter.addAll(DataProvider.getPictures(0));
                    }
                },1000);
            }
        });
    }

    private void addData(){
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.addAll(DataProvider.getPictures(0));
            }
        },1000);
    }
}
