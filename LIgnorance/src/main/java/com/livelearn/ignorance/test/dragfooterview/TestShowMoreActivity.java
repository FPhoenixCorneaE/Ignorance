package com.livelearn.ignorance.test.dragfooterview;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.test.dragfooterview.adapter.TestShowMoreAdapter;
import com.livelearn.ignorance.widget.TitleBar;

import butterknife.BindView;


/**
 * Created on 2016/11/13.
 */
public class TestShowMoreActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_show_more;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);
        setupRecyclerView();
    }

    @Override
    public void setListeners() {

    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        TestShowMoreAdapter adapter = new TestShowMoreAdapter(this);
        recyclerView.setAdapter(adapter);
    }
}
