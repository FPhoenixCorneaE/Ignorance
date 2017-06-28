package com.livelearn.ignorance.test.pulltorefreshwithloadmore.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.test.pulltorefreshwithloadmore.adapter.ListViewDataAdapter;
import com.livelearn.ignorance.test.pulltorefreshwithloadmore.model.Images;
import com.livelearn.ignorance.test.pulltorefreshwithloadmore.viewholder.ViewHolder;
import com.livelearn.ignorance.test.pulltorefreshwithloadmore.viewholder.ViewHolderBase;
import com.livelearn.ignorance.test.pulltorefreshwithloadmore.viewholder.ViewHolderCreator;
import com.livelearn.ignorance.utils.IntentUtils;
import com.livelearn.ignorance.widget.TitleBar;

import java.util.Arrays;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;


public class TestPtrWithGridViewActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.gv_grid)
    GridView gvGrid;

    @BindView(R.id.pcfl_pull_to_refresh)
    PtrClassicFrameLayout pcflPullToRefresh;

    private ListViewDataAdapter<String> mAdapter;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_ptr_with_grid_view;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);

        mAdapter = new ListViewDataAdapter<>(new ViewHolderCreator<String>() {
            @Override
            public ViewHolderBase<String> createViewHolder() {
                return new ViewHolder(mContext);
            }
        });
        gvGrid.setAdapter(mAdapter);

        pcflPullToRefresh.setLastUpdateTimeRelateObject(mContext);
        // the following are default settings
        pcflPullToRefresh.setResistance(1.7f); // you can also set foot and header separately
        pcflPullToRefresh.setRatioOfHeaderHeightToRefresh(1.2f);
        pcflPullToRefresh.setDurationToClose(1000);  // you can also set foot and header separately
        // default is false
        pcflPullToRefresh.setPullToRefresh(false);

        // default is true
        pcflPullToRefresh.setKeepHeaderWhenRefresh(true);
        pcflPullToRefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
//                pcflPullToRefresh.autoRefresh();
            }
        }, 100);
//        updateData();
        setupViews(pcflPullToRefresh);
    }

    protected void setupViews(final PtrClassicFrameLayout ptrFrame) {

    }

    @Override
    public void setListeners() {
        gvGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putString(Constant.IMAGE_URL, mAdapter.getDataList().get(i));
                IntentUtils.startActivity(mContext, TestPtrMaterialStyleActivity.class, bundle);
            }
        });
        pcflPullToRefresh.setPtrHandler(new PtrDefaultHandler2() {

            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                updateData();
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                updateData();
            }

            @Override
            public boolean checkCanDoLoadMore(PtrFrameLayout frame, View content, View footer) {
                return super.checkCanDoLoadMore(frame, gvGrid, footer);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return super.checkCanDoRefresh(frame, gvGrid, header);
            }
        });
    }

    private void updateData() {
        pcflPullToRefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                mAdapter.getDataList().clear();
                mAdapter.getDataList().addAll(Arrays.asList(Images.imageUrls).subList(0, 20));
                mAdapter.notifyDataSetChanged();
                if (pcflPullToRefresh != null)
                    pcflPullToRefresh.refreshComplete();
            }
        }, 1000);
    }
}
