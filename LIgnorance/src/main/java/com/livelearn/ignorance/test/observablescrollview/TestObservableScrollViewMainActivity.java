package com.livelearn.ignorance.test.observablescrollview;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.jude.easyrecyclerview.decoration.DividerDecoration;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.test.observablescrollview.viewholder.TestObservableScrollViewMainViewHolder;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.widget.TitleBar;

import java.util.Arrays;
import java.util.Collection;

import butterknife.BindView;

public class TestObservableScrollViewMainActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.rv_test)
    EasyRecyclerView rvTest;

    private RecyclerArrayAdapter<String> mAdapter;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_observable_scroll_view_main;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);

        rvTest.setLayoutManager(new LinearLayoutManager(mContext));
        DividerDecoration dividerDecoration = new DividerDecoration(ResourceUtils.getColor(R.color.divider), 1);
        rvTest.addItemDecoration(dividerDecoration);
        rvTest.setAdapterWithProgress(mAdapter = new RecyclerArrayAdapter<String>(mContext) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                return new TestObservableScrollViewMainViewHolder(parent);
            }
        });
        mAdapter.addAll(getData());
    }

    private Collection<? extends String> getData() {
        String[] dataArray = ResourceUtils.getStringArray(R.array.TestObservableScrollViewItem);
        return Arrays.asList(dataArray);
    }

    @Override
    public void setListeners() {

    }
}
