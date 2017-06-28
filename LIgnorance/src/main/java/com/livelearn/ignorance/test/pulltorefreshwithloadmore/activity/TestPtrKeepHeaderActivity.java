package com.livelearn.ignorance.test.pulltorefreshwithloadmore.activity;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;

public class TestPtrKeepHeaderActivity extends TestPtrWithTextViewActivity {

    @Override
    protected void setupViews(PtrClassicFrameLayout ptrFrame) {
        ptrFrame.setKeepHeaderWhenRefresh(true);
    }
}
