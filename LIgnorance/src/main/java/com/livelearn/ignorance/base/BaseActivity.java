package com.livelearn.ignorance.base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.apkfuns.logutils.LogUtils;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.model.bean.UserInfo;
import com.livelearn.ignorance.utils.AppUtils;
import com.livelearn.ignorance.utils.IntentUtils;
import com.livelearn.ignorance.utils.NetworkUtils;
import com.livelearn.ignorance.utils.SharedPreferencesUtils;
import com.livelearn.ignorance.utils.ToastUtils;
import com.livelearn.ignorance.utils.animation.ActivityAnimator;
import com.livelearn.ignorance.widget.swipeback.EdgeSwipeBackActivity;
import com.yalantis.ucrop.util.StatusBarCompat;

import org.simple.eventbus.EventBus;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends BasePresenter> extends EdgeSwipeBackActivity implements VacantView, SlidingPaneLayout.PanelSlideListener {

    //开启这个flag后，你就可以正常使用Selector这样的DrawableContainers了。
    //同时，你还开启了类似android:drawableLeft这样的compound drawable的使用权限，
    //以及RadioButton的使用权限，以及ImageView’s src属性。
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    private static final String WIFI_ACTION = "android.net.wifi.WIFI_STATE_CHANGED";
    public String TAG = this.getClass().getCanonicalName();
    public String className = this.getClass().getSimpleName();
    /**
     * 是否连网
     */
    public boolean isConnected = false;
    protected P mPresenter;
    protected Unbinder unbinder;
    public BaseActivity mContext;
    public UserInfo mUserInfo;
    public boolean hasLogin;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //这里的顺序不能变
        mContext = this;
        //沉浸式状态栏
        StatusBarCompat.compat(mContext);
        //设置布局资源
        setContentView(getLayoutResource());

        //当Fragment根布局 没有设定background属性时,默认使用此背景
        setDefaultFragmentBackground(R.color.background);
        //绑定黄油刀
        unbinder = ButterKnife.bind(mContext);
        //注册EventBus
        EventBus.getDefault().register(this);

        LogUtils.e(TAG);

        isConnected = NetworkUtils.isNetworkAvailable();

        if (hasEnteredTheHomepage()) {
            mUserInfo = Constant.getUserInfo();
            if (hasLogin() && mUserInfo == null) {
                IntentUtils.startAPP(mContext, AppUtils.getPackageName(mContext));
                return;
            }
        }
        //注册网络广播接收器
        regReceiver();
        //初始化布局
        initLayout(savedInstanceState);
        //初始化滑动返回
        if (!hasHorizontalScrollChildView()) {
            initSwipeBackFinish();
        }
        //设置监听
        setListeners();

        if (mPresenter != null) {
            mPresenter.attachView(mContext);
        }
    }

    /**
     * 是否已进入首页
     */
    public boolean hasEnteredTheHomepage() {
        return true;
    }

    /**
     * 是否已登录
     */
    public boolean hasLogin() {
        if (mUserInfo != null) {
            hasLogin = SharedPreferencesUtils.getBoolean(mUserInfo.getUserAccount(), Constant.LOGIN_STATE);
        }
        return hasLogin;
    }

    public abstract int getLayoutResource();

    public abstract void initLayout(Bundle savedInstanceState);

    public abstract void setListeners();

    /**
     * 初始化滑动返回
     */
    private void initSwipeBackFinish() {
        if (isSupportSwipeBack()) {
            SlidingPaneLayout slidingPaneLayout = new SlidingPaneLayout(this);
            //通过反射改变mOverhangSize的值为0，这个mOverhangSize值为菜单到右边屏幕的最短距离，默认
            //是32dp，现在给它改成0
            try {
                //属性
                Field f_overHang = SlidingPaneLayout.class.getDeclaredField("mOverhangSize");
                f_overHang.setAccessible(true);
                f_overHang.set(slidingPaneLayout, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            slidingPaneLayout.setPanelSlideListener(this);
            slidingPaneLayout.setSliderFadeColor(Color.TRANSPARENT);

            View leftView = new View(this);
            leftView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            slidingPaneLayout.addView(leftView, 0);
            ViewGroup decor = (ViewGroup) getWindow().getDecorView();
            ViewGroup decorChild = (ViewGroup) decor.getChildAt(0);
            decorChild.setBackgroundColor(Color.WHITE);
            decor.removeView(decorChild);
            decor.addView(slidingPaneLayout);
            slidingPaneLayout.addView(decorChild, 1);
        }
    }

    /**
     * 是否支持滑动返回
     */
    protected boolean isSupportSwipeBack() {
        return getSwipeBackLayout().isEnableGesture();
    }

    /**
     * //TODO 这里因为还没找到更好的解决办法，先暂时这样解决
     * 是否包含水平滚动的子控件
     * <p>
     * 包含则滑动边缘返回，不包含则可以全屏滑动返回
     */
    public boolean hasHorizontalScrollChildView() {
        return false;
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
        //退出动画,如果界面切换动画无效果则可以去手机系统设置-->开发者选项-->过渡动画比例，设置比例
        try {
            ActivityAnimator anim = new ActivityAnimator();
            anim.getClass().getMethod(getIntent().getStringExtra(Constant.BACK_ANIMATION), Activity.class).invoke(anim, mContext);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | NullPointerException e) {
            LogUtils.e(e.toString());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (netListener != null) {
            unregisterReceiver(netListener);
        }
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
        EventBus.getDefault().unregister(this);
    }

    /**
     * 注册广播，监听网络状态
     */
    private void regReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(WIFI_ACTION);
        registerReceiver(netListener, filter);
    }

    BroadcastReceiver netListener = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (!TextUtils.isEmpty(action) && action.equals(WIFI_ACTION)) {
                isConnected = NetworkUtils.isNetworkAvailable();
                if (!isConnected) {
                    //TODO 此处应该提示用户去打开WLAN或者移动网络
                    ToastUtils.showToast("你已经进入了没有网络的异次元");
                }
            }
        }
    };

    @Override
    public void onPanelSlide(View panel, float slideOffset) {

    }

    @Override
    public void onPanelOpened(View panel) {
        finish();
        this.overridePendingTransition(0, R.anim.slide_out_right);
    }

    @Override
    public void onPanelClosed(View panel) {

    }
}
