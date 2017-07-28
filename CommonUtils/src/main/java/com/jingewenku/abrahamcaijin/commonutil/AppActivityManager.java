package com.jingewenku.abrahamcaijin.commonutil;

import android.app.Activity;

import java.util.Stack;

/**
 * 主要功能:Activity的管理
 *
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年05月03日 16:37
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */

public class AppActivityManager {

    private static Stack<Activity> activityStack;
    private static AppActivityManager instance;

    private AppActivityManager() {
    }

    public static AppActivityManager getAppManager() {
        if (instance == null) {
            instance = new AppActivityManager();
        }

        if (activityStack == null) {
            activityStack = new Stack<>();
        }

        return instance;
    }

    public static Activity getActivity(Class<?> cls) {
        if (activityStack != null)
            for (Activity activity : activityStack) {
                if (activity.getClass().equals(cls)) {
                    return activity;
                }
            }
        return null;
    }

    public void addActivity(Activity activity) {
        activityStack.add(activity);
    }

    public Activity currentActivity() {
        return activityStack.lastElement();
    }

    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    public void finishActivity(Activity activity) {
        if (activity != null && activityStack.contains(activity)) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    public void removeActivity(Activity activity) {
        if (activity != null && activityStack.contains(activity)) {
            activityStack.remove(activity);
        }
    }

    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
    }

    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                finishActivity(activityStack.get(i));
            }
        }
        activityStack.clear();
    }

    public void exitApp() {
        try {
            finishAllActivity();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
