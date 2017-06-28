package com.livelearn.ignorance.test.pulltorefreshwithloadmore.activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.widget.TitleBar;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class TestPtrWithWebViewActivity extends BaseActivity {

    @BindView(R.id.tb_title)
    TitleBar tbTitle;

    @BindView(R.id.wb_web_page)
    WebView wbWebPage;

    @BindView(R.id.pcfl_pull_to_refresh)
    PtrClassicFrameLayout pcflPullToRefresh;

    @BindView(R.id.tv_progress)
    TextView tvProgress;

    @BindView(R.id.ll_progress)
    LinearLayout llProgress;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_test_ptr_with_web_view;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        tbTitle.setTitleText(className);

        wbWebPage.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                if (!isFinishing()) {
                    pcflPullToRefresh.refreshComplete();
                    llProgress.setVisibility(View.GONE);
                    wbWebPage.setVisibility(View.VISIBLE);
                }
            }
        });

        wbWebPage.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // 设置进行进度
                getWindow().setFeatureInt(Window.FEATURE_PROGRESS, newProgress * 100);
                if (tvProgress != null) {
                    tvProgress.setText("正在加载,已完成" + newProgress + "%...");
                    tvProgress.postInvalidate(); // 刷新UI
                }
            }
        });

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
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, wbWebPage, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                updateData();
            }
        });
    }

    private void updateData() {
//        wbWebPage.loadUrl("http://www.liaohuqiu.net/");
        wbWebPage.loadUrl("https://www.baidu.com/");
    }
}
