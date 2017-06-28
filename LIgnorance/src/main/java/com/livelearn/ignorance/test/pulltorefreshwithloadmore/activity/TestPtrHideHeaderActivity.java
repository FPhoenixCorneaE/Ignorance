package com.livelearn.ignorance.test.pulltorefreshwithloadmore.activity;

import android.os.Bundle;
import android.view.View;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.widget.TitleBar;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

public class TestPtrHideHeaderActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.pcfl_pull_to_refresh)
    PtrClassicFrameLayout pcflPullToRefresh;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_ptr_hide_header;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);

        pcflPullToRefresh.setLastUpdateTimeRelateObject(this);

        // default is true
        pcflPullToRefresh.setKeepHeaderWhenRefresh(false);
    }

    @Override
    public void setListeners() {
        pcflPullToRefresh.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                tbTitle.showLoadingView();
                if (frame != null)
                    frame.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            tbTitle.hideLoadingView();
                            pcflPullToRefresh.refreshComplete();
                        }
                    }, 1500);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return true;
            }
        });
    }
}
