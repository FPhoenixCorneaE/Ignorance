package com.livelearn.ignorance.test.observablescrollview;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.ksoichiro.observablescrollview.ObservableWebView;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.widget.cbprogressBar.CBProgressBar;

import butterknife.BindView;

public class TestTitleBarControlWebViewActivity extends TestTitleBarControlBaseActivity<ObservableWebView> {

    @BindView(R.id.cbpb_loading)
    CBProgressBar cbpbLoading;

    @BindView(R.id.osv_observable_scroll)
    ObservableWebView osvObservableScroll;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_test_title_bar_control_web_view;
    }

    @Override
    protected ObservableWebView createScrollable() {
        cbpbLoading.setMax(100);

        osvObservableScroll.loadUrl("https://www.baidu.com/baidu?tn=monline_3_dg&ie=utf-8&wd=%E7%83%BD%E7%81%AB%E6%88%8F%E8%AF%B8%E4%BE%AF");

        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        osvObservableScroll.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }
        });

        //判断页面加载过程
        osvObservableScroll.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    // 网页加载完成
                    if (cbpbLoading != null)
                        cbpbLoading.setVisibility(View.GONE);
                } else {
                    // 加载中
                    if (cbpbLoading != null)
                        cbpbLoading.setProgress(newProgress);
                }

            }
        });

        WebSettings webSettings = osvObservableScroll.getSettings();
        //隐藏webview缩放按钮
        webSettings.setDisplayZoomControls(false);
        //设置可以支持缩放
        webSettings.setSupportZoom(true);
        //设置出现缩放工具
        webSettings.setBuiltInZoomControls(true);

        //设置加载进来的页面自适应手机屏幕
        webSettings.setUseWideViewPort(true);//是否设置webview推荐使用的窗口
        webSettings.setLoadWithOverviewMode(true);//是否设置webview加载的页面的模式

        //如果访问的页面中有Javascript，则webview必须设置支持Javascript
//        webSettings.setJavaScriptEnabled(true);

        //优先使用缓存
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //不使用缓存
//        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        return osvObservableScroll;
    }

    /**
     * 改写物理按键——返回的逻辑:
     * 如果希望浏览的网页后退而不是退出浏览器，需要WebView覆盖URL加载，
     * 让它自动生成历史访问记录，那样就可以通过前进或后退访问已访问过的站点。
     */
    @Override
    public void onBackPressed() {
        if (osvObservableScroll.canGoBack()) {
            osvObservableScroll.goBack();//返回上一页面
        } else {
            super.onBackPressed();
        }
    }
}
