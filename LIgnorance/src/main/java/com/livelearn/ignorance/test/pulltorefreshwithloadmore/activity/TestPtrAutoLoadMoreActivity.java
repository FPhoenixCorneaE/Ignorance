package com.livelearn.ignorance.test.pulltorefreshwithloadmore.activity;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;

public class TestPtrAutoLoadMoreActivity extends TestPtrWithGridViewActivity {

    @Override
    protected void setupViews(final PtrClassicFrameLayout ptrFrame) {
        tbTitle.setTitleText(className);
        ptrFrame.setLoadingMinTime(2000);
        ptrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                ptrFrame.autoLoadMore(true);
            }
        }, 150);
    }
}
