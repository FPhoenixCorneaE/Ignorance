package com.livelearn.ignorance.test.pulltorefreshwithloadmore.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.utils.DisplayUtils;
import com.livelearn.ignorance.utils.GlideUtils;
import com.livelearn.ignorance.widget.TitleBar;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

public class TestPtrStoreHouseStyleUsingStringActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.iv_image)
    ImageView ivImage;

    @BindView(R.id.pfl_pull_to_refresh)
    PtrFrameLayout pflPullToRefresh;

    private String[] mStringList = {"Alibaba", "Baidu", "Tencent"};
    private StoreHouseHeader header;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_ptr_material_style;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);

        String picUrl = "http://img5.duitang.com/uploads/item/201406/28/20140628122218_fLQyP.thumb.jpeg";
        GlideUtils.setupImage(mContext, ivImage, picUrl);
        header = new StoreHouseHeader(mContext);
        header.setPadding(0, DisplayUtils.dp2px(15f), 0, 0);
        /**
         * using a string, support: A-Z 0-9 - .
         * you can add more letters by {@link in.srain.cube.views.ptr.header.StoreHousePath#addChar}
         */
        header.initWithString(mStringList[0]);

        pflPullToRefresh.setDurationToCloseHeader(3000);
        pflPullToRefresh.setHeaderView(header);
        pflPullToRefresh.addPtrUIHandler(header);
        pflPullToRefresh.postDelayed(new Runnable() {
            @Override
            public void run() {
                pflPullToRefresh.autoRefresh(false);
            }
        }, 100);
    }

    @Override
    public void setListeners() {
        pflPullToRefresh.addPtrUIHandler(new PtrUIHandler() {
            private int mLoadTime = 0;

            @Override
            public void onUIReset(PtrFrameLayout frame) {
                mLoadTime++;
                header.initWithString(mStringList[mLoadTime % mStringList.length]);
            }

            @Override
            public void onUIRefreshPrepare(PtrFrameLayout frame) {

            }

            @Override
            public void onUIRefreshBegin(PtrFrameLayout frame) {

            }

            @Override
            public void onUIRefreshComplete(PtrFrameLayout frame, boolean isHeader) {

            }

            @Override
            public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

            }
        });

        pflPullToRefresh.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return true;
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pflPullToRefresh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (pflPullToRefresh != null)
                            pflPullToRefresh.refreshComplete();
                    }
                }, 2000);
            }
        });
    }
}
