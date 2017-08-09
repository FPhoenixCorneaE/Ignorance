package com.livelearn.ignorance.presenter.mine;

import android.app.Dialog;
import android.os.Handler;
import android.view.Gravity;

import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.animation.SlideExit.SlideBottomExit;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.NormalDialog;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.base.RxPresenter;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.ui.activity.LoginActivity;
import com.livelearn.ignorance.ui.view.mine.ISettingsView;
import com.livelearn.ignorance.utils.CleanCacheUtils;
import com.livelearn.ignorance.utils.DialogUtils;
import com.livelearn.ignorance.utils.GlideCacheUtils;
import com.livelearn.ignorance.utils.IntentUtils;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.utils.ToastUtils;

/**
 * 设置
 * Created on 2017/4/25.
 */

public class SettingsPresenter extends RxPresenter {

    private ISettingsView iSettingsView;
    private BaseActivity mContext;

    public SettingsPresenter(ISettingsView iSettingsView) {
        this.iSettingsView = iSettingsView;
        this.mContext = iSettingsView.getmContext();
    }

    /**
     * 显示清除缓存对话框
     */
    public void showClearCacheDialog() {
        new NormalDialog(mContext)
                .isTitleShow(false)//不显示标题
                .bgColor(ResourceUtils.getColor(R.color.color_pale))
                .cornerRadius(5)
                .content("确定清除缓存吗？")//对话框内容
                .contentGravity(Gravity.CENTER)
                .contentTextColor(ResourceUtils.getColor(R.color.color_dark_black))
                .contentTextSize(15f)
                .dividerColor(ResourceUtils.getColor(R.color.divider))
                .btnTextColor(ResourceUtils.getColor(R.color.color_gray), ResourceUtils.getColor(R.color.color_light_purple))
                .btnTextSize(18, 18)
                .btnPressColor(ResourceUtils.getColor(R.color.color_light_gray))
                .widthScale(0.75f)
                .showAnim(new BounceTopEnter())//显示动画
                .dismissAnim(new SlideBottomExit())//解散动画
                .setOnBtnClickL(null, new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        Dialog clearingDialog = DialogUtils.createLoadingDialog(mContext, "正在清除缓存", true);
                        clearCache(clearingDialog);
                    }
                })
                .show();
    }

    /**
     * 获取缓存
     */
    public void getCache() {
        String cacheSize = CleanCacheUtils.getTotalCacheSize();
        String size;
        String unit;
        if (cacheSize.contains("K")) {
            size = cacheSize.substring(0, cacheSize.indexOf("K"));
            unit = "KB";
        } else if (cacheSize.contains("M")) {
            size = cacheSize.substring(0, cacheSize.indexOf("M"));
            unit = "MB";
        } else if (cacheSize.contains("G")) {
            size = cacheSize.substring(0, cacheSize.indexOf("G"));
            unit = "GB";
        } else if (cacheSize.contains("T")) {
            size = cacheSize.substring(0, cacheSize.indexOf("T"));
            unit = "TB";
        } else {
            size = cacheSize.substring(0, cacheSize.indexOf("B"));
            unit = "B";
        }
        iSettingsView.setCache(size, unit);
    }

    /**
     * 清除缓存
     */
    private void clearCache(final Dialog clearingDialog) {
        clearingDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mContext != null)
                    CleanCacheUtils.clearAllCache();

                iSettingsView.onClearCacheSuccess(clearingDialog);
            }
        }, 500);
    }

    /**
     * 清除Glide缓存
     */
    public void clearGlideCache() {
        GlideCacheUtils.clearImageAllCache();
        ToastUtils.showToast("清除Glide缓存成功");
    }

    /**
     * 登出
     */
    public void logout() {
        new NormalDialog(mContext)
                .isTitleShow(false)//不显示标题
                .bgColor(ResourceUtils.getColor(R.color.color_pale))
                .cornerRadius(5)
                .content("确定退出当前账号吗？")//对话框内容
                .contentGravity(Gravity.CENTER)
                .contentTextColor(ResourceUtils.getColor(R.color.color_dark_red))
                .contentTextSize(15f)
                .dividerColor(ResourceUtils.getColor(R.color.divider))
                .btnTextColor(ResourceUtils.getColor(R.color.color_gray), ResourceUtils.getColor(R.color.color_light_red))
                .btnTextSize(18, 18)
                .btnPressColor(ResourceUtils.getColor(R.color.color_light_gray))
                .widthScale(0.75f)
                .showAnim(new BounceTopEnter())//显示动画
                .dismissAnim(new SlideBottomExit())//解散动画
                .setOnBtnClickL(null, new OnBtnClickL() {
                    @Override
                    public void onBtnClick() {
                        Constant.setUserInfo(null);
                        IntentUtils.startActivity(mContext, LoginActivity.class);
                        //这里要等解散动画结束后才能销毁
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (!mContext.isFinishing())
                                    mContext.finish();
                            }
                        }, 520);
                    }
                })
                .show();
    }
}
