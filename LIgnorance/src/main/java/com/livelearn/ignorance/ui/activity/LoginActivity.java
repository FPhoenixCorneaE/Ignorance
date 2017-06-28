package com.livelearn.ignorance.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import com.apkfuns.logutils.LogUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.fresco.helper.ImageLoader;
import com.livelearn.ignorance.R;
import com.livelearn.ignorance.base.BaseActivity;
import com.livelearn.ignorance.callbacklistener.OnUploadImgCallback;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.exception.OSSException;
import com.livelearn.ignorance.presenter.LoginPresenter;
import com.livelearn.ignorance.utils.DesUtils;
import com.livelearn.ignorance.utils.GlideUtils;
import com.livelearn.ignorance.utils.IntentUtils;
import com.livelearn.ignorance.utils.PackageUtils;
import com.livelearn.ignorance.utils.ResourceUtils;
import com.livelearn.ignorance.utils.SharedPreferencesUtils;
import com.livelearn.ignorance.utils.ToastUtils;
import com.livelearn.ignorance.utils.aliyunupload.UploadSingleImageUtils;
import com.livelearn.ignorance.utils.regular.Validator;
import com.livelearn.ignorance.widget.jellytogglebutton.JellyToggleButton;
import com.livelearn.ignorance.widget.materiallogin.DefaultLoginView;
import com.livelearn.ignorance.widget.materiallogin.DefaultRegisterView;
import com.livelearn.ignorance.widget.materiallogin.MaterialLoginView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import am.widget.shapeimageview.ShapeImageView;
import butterknife.BindView;
import me.iwf.photopicker.PhotoPickUtils;

/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;

    @BindView(R.id.mlv_login)
    MaterialLoginView mlvLogin;

    private DefaultLoginView loginView;
    private DefaultRegisterView registerView;
    private ShapeImageView loginAvatar;
    private SimpleDraweeView registerAvatar;
    private JellyToggleButton jtbRemember;
    private JellyToggleButton jtbAutoLogin;
    private String userAvatarPath;
    private UploadSingleImageUtils uploadSingleImageUtils;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    public void initLayout(Bundle savedInstanceState) {
        //不能滑动返回
        setSwipeBackEnable(false);

        loginView = ((DefaultLoginView) mlvLogin.getLoginView());
        loginAvatar = loginView.getIvAvatar();
        jtbRemember = loginView.getJtbRemember();
        jtbAutoLogin = loginView.getJtbAutoLogin();

        String userAccountList = SharedPreferencesUtils.getString(Constant.USER_INFO, Constant.USER_ACCOUNT);
        String userAccount = null;
        if (userAccountList != null && !TextUtils.isEmpty(userAccountList)) {//至少保存了一个账号
            ArrayList<String> accountArray = new ArrayList<>();
            Collections.addAll(accountArray, userAccountList.split(","));
            userAccount = accountArray.get(0);//取第一个账号
        }
        //自动填充登录信息
        autoFillLoginInformation(userAccount);

        registerView = ((DefaultRegisterView) mlvLogin.getRegisterView());
        registerAvatar = registerView.getIVAvatar();
        ImageLoader.loadImage(registerAvatar, null);
    }

    @Override
    public boolean hasEnteredTheHomepage() {
        return false;
    }

    /**
     * 自动填充登录信息
     *
     * @param userAccount 账号
     */
    private void autoFillLoginInformation(String userAccount) {
        if (userAccount != null) {
            userAvatarPath = SharedPreferencesUtils.getString(userAccount, Constant.USER_AVATAR);
            boolean rememberPassword = SharedPreferencesUtils.getBoolean(userAccount, Constant.REMEMBER_PASSWORD);
            String password = SharedPreferencesUtils.getString(userAccount, Constant.USER_PASSWORD);
            boolean autoLogin = SharedPreferencesUtils.getBoolean(userAccount, Constant.AUTO_LOGIN);

            TextInputLayout loginUser = loginView.getLoginUser();
            if (loginUser.getEditText() != null) {
                loginUser.getEditText().setText(userAccount);
            }
            if (rememberPassword) {
                TextInputLayout loginPass = loginView.getLoginPass();
                if (loginPass.getEditText() != null) {
                    //密码解密
                    password = DesUtils.deString(password);
                    loginPass.getEditText().setText(password);
                }
                jtbRemember.setChecked(true);
            }
            if (autoLogin)
                jtbAutoLogin.setChecked(true);
        }
        GlideUtils.setupImage(mContext, loginAvatar, userAvatarPath, R.drawable.shape_circle_solidlightgray);
    }

    @Override
    public void setListeners() {
        //登录监听
        loginView.setListener(new DefaultLoginView.DefaultLoginViewListener() {

            @Override
            public void onLogin(TextInputLayout loginUser, TextInputLayout loginPass) {
                String userAccount = checkUserAccount(loginUser);
                if (userAccount == null) return;
                loginUser.setError("");

                String pass = checkPass(loginPass);
                if (pass == null) return;
                loginPass.setError("");

                boolean isRegistered = false;
                String userAccountList = SharedPreferencesUtils.getString(Constant.USER_INFO, Constant.USER_ACCOUNT);
                if (userAccountList != null && !TextUtils.isEmpty(userAccountList)) {
                    ArrayList<String> accountArray = new ArrayList<>();
                    Collections.addAll(accountArray, userAccountList.split(","));
                    for (String account : accountArray) {
                        if (account.equals(userAccount)) {
                            isRegistered = true;
                            break;
                        }
                    }
                }
                if (!isRegistered) {
                    ToastUtils.showToast("你还没注册呢，先去注册吧~~~");
                    return;
                }

                //保存加密的密码
                LoginPresenter.doLogin(mContext, userAccount, DesUtils.enString(pass), jtbRemember.isChecked(), jtbAutoLogin.isChecked());
                finish();
            }
        });

        //注册监听
        registerView.setListener(new DefaultRegisterView.DefaultRegisterViewListener() {
            @Override
            public void onRegister(TextInputLayout registerUser, TextInputLayout registerNickname, TextInputLayout registerPass, TextInputLayout registerPassRep) {
                String userAccount = checkUserAccount(registerUser);
                if (userAccount == null) return;
                registerUser.setError("");

                String userNickname = "";
                if (registerNickname.getEditText() != null) {
                    userNickname = registerNickname.getEditText().getText().toString().trim();
                }

                String pass = checkPass(registerPass);
                if (pass == null) return;
                registerPass.setError("");

                if (checkPassRep(registerPassRep, pass)) return;
                registerPassRep.setError("");

                saveUserInfo(userAccount, userNickname, pass);

                ToastUtils.showSuccessToast(ResourceUtils.getString(R.string.register_success));

                registerView.getCancelRegisterView().performClick();
                //自动填充登录信息
                autoFillLoginInformation(userAccount);
            }
        });

        //注册头像点击监听
        registerAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (uploadSingleImageUtils == null) {
                    uploadSingleImageUtils = new UploadSingleImageUtils(mContext, true);
                }
                uploadSingleImageUtils.showSheetDialog(registerView, new OnUploadImgCallback() {
                    @Override
                    public void onProgress(float percent, long currentSize, long totalSize) {

                    }

                    @Override
                    public void onFailure(String imagePath, OSSException ossException) {

                    }

                    @Override
                    public void onSuccess(String imagePath) {
                        userAvatarPath = imagePath;
                        ImageLoader.loadImage(registerAvatar, userAvatarPath);
                    }
                });
            }
        });
    }

    /**
     * 保存用户信息
     */
    private void saveUserInfo(String userAccount, String userNickname, String password) {
        String userAccountList = SharedPreferencesUtils.getString(Constant.USER_INFO, Constant.USER_ACCOUNT);
        ArrayList<String> accountArray = new ArrayList<>();
        if (userAccountList == null || TextUtils.isEmpty(userAccountList)) {
            accountArray.add(userAccount);
            userAccountList = Arrays.toString(accountArray.toArray());
            LogUtils.e(userAccountList);
        } else {
            Collections.addAll(accountArray, userAccountList.split(","));
            accountArray.add(0, userAccount);
        }
        userAccountList = Arrays.toString(accountArray.toArray());
        userAccountList = userAccountList.substring(1, userAccountList.length() - 1);
        SharedPreferencesUtils.put(Constant.USER_INFO, Constant.USER_ACCOUNT, userAccountList);
        SharedPreferencesUtils.put(userAccount, Constant.USER_AVATAR, userAvatarPath);
        SharedPreferencesUtils.put(userAccount, Constant.USER_ACCOUNT, userAccount);
        SharedPreferencesUtils.put(userAccount, Constant.USER_NICKNAME, userNickname);
        SharedPreferencesUtils.put(userAccount, Constant.USER_PASSWORD, DesUtils.enString(password));
        SharedPreferencesUtils.put(userAccount, Constant.REMEMBER_PASSWORD, true);
        SharedPreferencesUtils.put(userAccount, Constant.AUTO_LOGIN, true);
    }

    /**
     * 核对用户名
     */
    private String checkUserAccount(TextInputLayout registerUser) {
        if (registerUser.getEditText() == null) return null;
        String userName = registerUser.getEditText().getText().toString().trim();
        if (userName.isEmpty()) {
            registerUser.setError(ResourceUtils.getString(R.string.user_name_empty));
            return null;
        } else if (!Validator.isUserName(userName)) {
            registerUser.setError(ResourceUtils.getString(R.string.user_name_error));
            return null;
        }
        return userName;
    }

    /**
     * 核对密码
     */
    @Nullable
    private String checkPass(TextInputLayout registerPass) {
        if (registerPass.getEditText() == null) return null;
        String pass = registerPass.getEditText().getText().toString().trim();
        if (pass.isEmpty()) {
            registerPass.setError(ResourceUtils.getString(R.string.user_password_empty));
            return null;
        } else if (!Validator.isPassword(pass)) {
            registerPass.setError(ResourceUtils.getString(R.string.user_password_error));
            return null;
        }
        return pass;
    }

    /**
     * 核对确认密码
     */
    private boolean checkPassRep(TextInputLayout registerPassRep, String pass) {
        if (registerPassRep.getEditText() == null) return true;
        String passRep = registerPassRep.getEditText().getText().toString().trim();
        if (passRep.isEmpty()) {
            registerPassRep.setError(ResourceUtils.getString(R.string.user_password_empty));
            return true;
        } else if (!Validator.isPassword(passRep)) {
            registerPassRep.setError(ResourceUtils.getString(R.string.user_passwordRep_error));
            return true;
        } else if (!pass.equals(passRep)) {
            registerPassRep.setError(ResourceUtils.getString(R.string.user_password_different));
            return true;
        }
        return false;
    }

    /**
     * 检查授权
     */
    private void checkPermission(int requestCode) {
        boolean readStoragePermissionGranted = !PackageUtils.checkPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE);
        boolean cameraPermissionGranted = !PackageUtils.checkPermission(mContext, Manifest.permission.CAMERA);
        if (readStoragePermissionGranted || cameraPermissionGranted) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(mContext, Manifest.permission.READ_EXTERNAL_STORAGE)
                    || ActivityCompat.shouldShowRequestPermissionRationale(mContext, Manifest.permission.CAMERA)) {
                //第一次请求权限时，用户拒绝了，下一次：shouldShowRequestPermissionRationale()  返回 true，应该显示一些为什么需要这个权限的说明
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                //第二次请求权限时，用户拒绝了，并选择了“不在提醒”的选项时：shouldShowRequestPermissionRationale()  返回 false
                //设备的策略禁止当前应用获取这个权限的授权：shouldShowRequestPermissionRationale()  返回 false
                String[] permissions;
                if (readStoragePermissionGranted && cameraPermissionGranted) {
                    permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
                } else {
                    permissions = new String[]{
                            readStoragePermissionGranted ? Manifest.permission.READ_EXTERNAL_STORAGE
                                    : Manifest.permission.CAMERA
                    };
                }
                ActivityCompat.requestPermissions(mContext, permissions, requestCode);
            }
        } else {
            //Permission granted
            if (requestCode == 0) {
                PhotoPickUtils.cropFromCamera(mContext);
            } else if (requestCode == 1) {
                PhotoPickUtils.startGallerySingle(mContext, true, true);
            }
        }
    }

    @Override
    public boolean shouldShowRequestPermissionRationale(@NonNull String permission) {
        switch (permission) {
            case Manifest.permission.READ_EXTERNAL_STORAGE:
            case Manifest.permission.CAMERA:
                return false;
            default:
                return true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uploadSingleImageUtils.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        IntentUtils.startActivity(mContext, MainActivity.class);
        finish();
    }
}
