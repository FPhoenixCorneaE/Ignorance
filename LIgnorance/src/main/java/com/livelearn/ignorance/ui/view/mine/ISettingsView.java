package com.livelearn.ignorance.ui.view.mine;

import android.app.Dialog;

import com.livelearn.ignorance.base.IBaseView;

/**
 * Created on 2017/4/25.
 */

public interface ISettingsView extends IBaseView{

    void setCache(String size, String unit);

    void onClearCacheSuccess(Dialog clearingDialog);

}
