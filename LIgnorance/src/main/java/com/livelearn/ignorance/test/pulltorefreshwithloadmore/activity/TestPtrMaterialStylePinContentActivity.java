package com.livelearn.ignorance.test.pulltorefreshwithloadmore.activity;

import android.os.Bundle;

public class TestPtrMaterialStylePinContentActivity extends TestPtrMaterialStyleActivity {

    @Override
    public void initLayout(Bundle savedInstanceState) {
        super.initLayout(savedInstanceState);
        // close at once
        pflPullToRefresh.setDurationToClose(100);
        pflPullToRefresh.setPinContent(true);
    }
}
