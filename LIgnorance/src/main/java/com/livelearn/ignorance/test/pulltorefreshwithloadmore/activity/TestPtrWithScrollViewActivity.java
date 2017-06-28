package com.livelearn.ignorance.test.pulltorefreshwithloadmore.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.widget.TitleBar;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class TestPtrWithScrollViewActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.sv_scroll)
    ScrollView svScroll;

    @BindView(R.id.pcfl_pull_to_refresh)
    PtrClassicFrameLayout pcflPullToRefresh;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_ptr_with_scroll_view;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);

        pcflPullToRefresh.setLastUpdateTimeRelateObject(mContext);
        // the following are default settings
        pcflPullToRefresh.setResistance(1.7f);
        pcflPullToRefresh.setRatioOfHeaderHeightToRefresh(1.2f);
        pcflPullToRefresh.setDurationToClose(200);
        pcflPullToRefresh.setDurationToCloseHeader(1000);
        // default is false
        pcflPullToRefresh.setPullToRefresh(false);
        // default is true
        pcflPullToRefresh.setKeepHeaderWhenRefresh(true);
        pcflPullToRefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                pcflPullToRefresh.autoRefresh();
            }
        }, 100);
    }

    @Override
    public void setListeners() {
        pcflPullToRefresh.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, svScroll, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pcflPullToRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (pcflPullToRefresh != null)
                            pcflPullToRefresh.refreshComplete();
                    }
                }, 100);
            }
        });
    }
}
