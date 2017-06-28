package com.livelearn.ignorance.base;

import android.app.Activity;
import android.content.Context;

import com.livelearn.ignorance.model.bean.UserInfo;

/**
 * Created on 2017/4/25.
 */

public interface IBaseView {

    Context getmContext();

    Activity getmActivity();

    UserInfo getmUserInfo();
}
