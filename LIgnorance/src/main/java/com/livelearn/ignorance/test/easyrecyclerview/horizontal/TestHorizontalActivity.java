package com.livelearn.ignorance.test.easyrecyclerview.horizontal;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.test.easyrecyclerview.DataProvider;
import com.livelearn.ignorance.test.easyrecyclerview.Utils;
import com.livelearn.ignorance.widget.TitleBar;

/**
 * Created on 16/9/19.
 */

public class TestHorizontalActivity extends BaseActivity {
    private TestNarrowImageAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_horizontal;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        ((TitleBar) findViewById(R.id.tb_title)).setTitleText(className);

        EasyRecyclerView recyclerView = (EasyRecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter = new TestNarrowImageAdapter(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        recyclerView.addItemDecoration(new SpaceDecoration((int) Utils.convertDpToPixel(8,this)));
        adapter.setMore(R.layout.custom_view_footer_loadmore_horizontal_easy_recycler_view, new RecyclerArrayAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.addAll(DataProvider.getNarrowImage(0));
                    }
                },1000);
            }
        });
        recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                adapter.addAll(DataProvider.getNarrowImage(0));
            }
        });
        adapter.addAll(DataProvider.getNarrowImage(0));
    }

    @Override
    public void setListeners() {

    }

}
