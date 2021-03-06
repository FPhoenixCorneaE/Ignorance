package com.livelearn.ignorance.utils;

import android.Manifest;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;
import androidx.core.app.ActivityCompat;
import android.text.TextUtils;

import com.apkfuns.logutils.LogUtils;
import com.livelearn.ignorance.common.Constant;
import com.livelearn.ignorance.utils.animation.ActivityAnimator;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

/**
 * Intent操作
 */
public class IntentUtils {

    private static String[] animationList = {
            "appearBottomRight",
            "appearTopLeft",
            "disappearBottomRight",
            "disappearTopLeft",
            "fade",
            "flipHorizontal",
            "flipVertical",
            "jump",
            "pullDownPushTop",
            "pullLeftPushRight",
            "pullRightPushLeft",
            "pullTopPushDown",
            "rotateDown",
            "rotateUp",
            "scale",
            "unzoom",
            "zoom"
    };

    /**
     * 显示意图，不传参
     */
    public static void startActivity(Context context, Class<?> className) {
        startActivity(context, className, null);
    }

    /**
     * 显示意图,传参,带动画
     */
    public static void startActivity(Context context, Class<?> className, Bundle bundle) {
        String animation = animationList[new Random().nextInt(animationList.length)];
        startActivity(context, className, bundle, animation);
    }

    /**
     * 显示意图,传参,不带动画
     */
    public static void startActivityWithNoAnimation(Context context, Class<?> className, Bundle bundle) {
        startActivity(context, className, bundle, null);
    }

    /**
     * 显示意图,传参,切换动画
     */
    public static void startActivity(Context context, Class<?> className, Bundle bundle, int enterAnim, int exitAnim) {
        startActivityForResult(context, className, Integer.MIN_VALUE, bundle, enterAnim, exitAnim);
    }

    /**
     * 显示意图,传参,切换动画
     */
    public static void startActivity(Context context, Class<?> className, Bundle bundle, String animation) {
        startActivityForResult(context, className, Integer.MIN_VALUE, bundle, animation);
    }

    /**
     * 显示意图,附带请求码
     */
    public static void startActivityForResult(Context context, Class<?> className, int requestCode) {
        startActivityForResult(context, className, requestCode, null);
    }

    /**
     * 显示意图，附带请求码，传参,带动画
     */
    public static void startActivityForResult(Context context, Class<?> className, int requestCode, Bundle bundle) {
        String animation = animationList[new Random().nextInt(animationList.length)];
        startActivityForResult(context, className, requestCode, bundle, animation);
    }

    /**
     * 显示意图，附带请求码，传参,带动画
     */
    public static void startActivityForResultWithNoAnimation(Context context, Class<?> className, int requestCode, Bundle bundle) {
        startActivityForResult(context, className, requestCode, bundle, null);
    }

    /**
     * 显示意图，附带请求码，传参,切换动画
     */
    public static void startActivityForResult(Context context, Class<?> className, int requestCode, Bundle bundle, int enterAnim, int exitAnim) {
        if (context == null) return;

        if (context instanceof Activity) {
            Intent intent = new Intent();
            intent.setClass(context, className);
            if (bundle != null) intent.putExtras(bundle);

            if (Integer.MIN_VALUE == requestCode) {
                context.startActivity(intent);
            } else {
                ((Activity) context).startActivityForResult(intent, requestCode);
            }
            ((Activity) context).overridePendingTransition(enterAnim, exitAnim);
        }
    }

    /**
     * 显示意图，附带请求码，传参,切换动画
     */
    public static void startActivityForResult(Context context, Class<?> className, int requestCode, Bundle bundle, String animation) {
        if (context == null) return;

        if (context instanceof Activity) {
            Intent intent = new Intent();
            intent.setClass(context, className);
            if (bundle == null) bundle = new Bundle();
            bundle.putString(Constant.BACK_ANIMATION, animation);
            intent.putExtras(bundle);

            if (Integer.MIN_VALUE == requestCode) {
                context.startActivity(intent);
            } else {
                ((Activity) context).startActivityForResult(intent, requestCode);
            }
            //进入动画
            try {
                if (null != animation) {
                    LogUtils.i(animation);
                    ActivityAnimator anim = new ActivityAnimator();
                    anim.getClass().getMethod(animation, Activity.class).invoke(anim, context);
                }
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                LogUtils.e(e);
            }
        }
    }

    /**
     * 隐式意图，不传参
     */
    public static void startImplicitActivity(Context context, String action) {
        startImplicitActivity(context, action, null);
    }

    /**
     * 隐式意图，传参
     */
    public static void startImplicitActivity(Context context, String action, Bundle bundle) {
        if (context == null) return;

        Intent intent = new Intent(action);
        if (bundle != null) intent.putExtras(bundle);

        context.startActivity(intent);
    }

    /**
     * 隐示意图,附带请求码
     */
    public static void startImplicitActivityForResult(Context context, String action, int requestCode) {
        startImplicitActivityForResult(context, action, requestCode, null);
    }

    /**
     * 隐示意图，附带请求码，传参
     */
    public static void startImplicitActivityForResult(Context context, String action, int requestCode, Bundle bundle) {
        if (context == null) return;

        Intent intent = new Intent(action);
        if (bundle != null) intent.putExtras(bundle);

        ((Activity) context).startActivityForResult(intent, requestCode);
    }

    /**
     * 根据包名打开软件
     */
    public static void startAPP(Context context, String appPackageName) {
        if (null == context || null == appPackageName || appPackageName.isEmpty()) return;

        Intent intent = context.getPackageManager().getLaunchIntentForPackage(appPackageName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 启动服务
     *
     * @param context     上下文
     * @param serviceName 服务名字
     */
    public static void startService(Context context, Class<?> serviceName) {
        startService(context, serviceName, null);
    }

    /**
     * 启动服务
     *
     * @param context     上下文
     * @param serviceName 服务名字
     * @param bundle      捆绑的数据
     */
    public static void startService(Context context, Class<?> serviceName, Bundle bundle) {
        if (context == null) return;

        Intent intent = new Intent();
        intent.setClass(context, serviceName);
        if (bundle != null) intent.putExtras(bundle);

        context.startService(intent);
    }

    /**
     * Search a word in a browser
     */
    public static void search(Context context, String string) {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, string);
        context.startActivity(intent);
    }

    /**
     * Open url in a browser
     */
    public static void openUrl(Context context, String url) {
        if (context == null || url == null || url.isEmpty()) return;
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }

    /**
     * Open map in a map app
     */
    public static void openMap(Context context, String parh) {
        if (context == null || parh == null || parh.isEmpty()) return;
        Uri uri = Uri.parse(parh);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }

    /**
     * Open dial
     */
    public static void openDial(Context context) {
        if (context == null) return;
        Intent intent = new Intent(Intent.ACTION_CALL_BUTTON);
        context.startActivity(intent);
    }

    /**
     * Open dial with a number
     */
    public static void openDial(Context context, String number) {
        Uri uri = Uri.parse("tel:" + number);
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        context.startActivity(intent);
    }

    /**
     * Call up, requires Permission "android.permission.CALL_PHONE"
     */
    public static void call(Context context, String number) {
        Uri uri = Uri.parse("tel:" + number);
        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
            return;

        context.startActivity(intent);
    }

    /**
     * Send message
     */
    public static void sendMessage(Context context, String sendNo, String sendContent) {
        Uri uri = Uri.parse("smsto:" + sendNo);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", sendContent);
        context.startActivity(intent);
    }

    /**
     * Open contact person
     */
    public static void openContacts(Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW, ContactsContract.Contacts.CONTENT_URI);
        context.startActivity(intent);
    }

    /**
     * Open system settings
     */
    public static void openSettings(Context context) {
        openSettings(context, Settings.ACTION_SETTINGS);
    }

    /**
     * Open system settings
     *
     * @param action The action contains global system-level device preferences.
     */
    public static void openSettings(Context context, String action) {
        if (null == context) return;

        if (!TextUtils.isEmpty(action)) {
            Intent intent = new Intent(action);
            context.startActivity(intent);
        } else {
            openSettings(context);
        }
    }

    /**
     * Open camera
     */
    public static void openCamera(Context context) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        context.startActivity(intent);
    }

    /**
     * Take camera, this photo data will be returned in onActivityResult()
     */
    public static void takeCamera(Activity activity, int requestCode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * Choose photo, this photo data will be returned in onActivityResult()
     */
    public static void choosePhoto(Activity activity, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * Open App Detail page
     */
    public static void openAppDetail(String packageName, Context context) {
        Intent intent = new Intent();
        final int apiLevel = Build.VERSION.SDK_INT;
        if (apiLevel >= 9) {
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", packageName, null);
            intent.setData(uri);
        } else {
            final String appPkgName = (apiLevel == 8 ? "pkg" : "com.android.settings.ApplicationPkgName");
            intent.setAction(Intent.ACTION_VIEW);
            intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            intent.putExtra(appPkgName, packageName);
        }
        context.startActivity(intent);
    }
}
