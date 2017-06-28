package com.livelearn.ignorance.presenter;

import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.model.bean.UserInfo;
import com.livelearn.ignorance.ui.activity.MainActivity;
import com.livelearn.ignorance.utils.DeviceUtils;
import com.livelearn.ignorance.utils.IntentUtils;
import com.livelearn.ignorance.utils.SharedPreferencesUtils;

/**
 * 登录
 */
public class LoginPresenter {

    public static void doLogin(BaseActivity context, String userAccount, String userPassword, boolean rememberPassword, boolean autoLogin) {
        String userAvatarPath = SharedPreferencesUtils.getString(userAccount, Constant.USER_AVATAR);
        String userNickname = SharedPreferencesUtils.getString(userAccount, Constant.USER_NICKNAME);

        UserInfo userInfo = new UserInfo();
        userInfo.setUserAccount(userAccount);
        userInfo.setUserAvatarPath(userAvatarPath);
        userInfo.setUserNickname(userNickname);
        userInfo.setPhoneModel(android.os.Build.MODEL);
        userInfo.setMacAddress(DeviceUtils.getLocalMacAddress());
        userInfo.setHostAddress(DeviceUtils.getHostAddress());
        userInfo.setAndroidId(DeviceUtils.getAndroidId(context));

        Constant.setUserInfo(userInfo);

        SharedPreferencesUtils.put(userAccount, Constant.LOGIN_STATE, true);
        SharedPreferencesUtils.put(userAccount, Constant.AUTO_LOGIN, autoLogin);
        SharedPreferencesUtils.put(userAccount, Constant.REMEMBER_PASSWORD, rememberPassword);
        SharedPreferencesUtils.put(userAccount, Constant.USER_PASSWORD, rememberPassword ? userPassword : "");

        IntentUtils.startActivity(context, MainActivity.class);
    }
}
