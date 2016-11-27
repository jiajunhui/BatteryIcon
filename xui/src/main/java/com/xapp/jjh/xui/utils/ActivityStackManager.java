package com.xapp.jjh.xui.utils;

import android.app.Activity;
import java.util.Stack;

/**
 * Created by Taurus on 15/12/1.
 * activity堆栈式管理
 */
public class ActivityStackManager {

    private static Stack<Activity> activityStack = new Stack<>();
    private static ActivityStackManager mInstance;

    private ActivityStackManager() {}

    /**
     * 单一实例
     */
    public static ActivityStackManager getInstance() {
        if (mInstance == null) {
            synchronized (ActivityStackManager.class) {
                if (mInstance == null) {
                    mInstance = new ActivityStackManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 从栈中移除activity
     */
    public void removeActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        while(!activityStack.empty()){
            Activity activity = activityStack.pop();
            if (null != activity) {
                activity.finish();
                activity = null;
            }
        }
        activityStack.clear();
    }

    public int getActivityNums(){
        if(activityStack==null)
            return 0;
        return activityStack.size();
    }

    /**
     * 获取指定的Activity
     *
     * @author kymjs
     */
    public Activity getActivity(Class<?> cls) {
        if (activityStack != null)
            for (Activity activity : activityStack) {
                if (activity.getClass().equals(cls)) {
                    return activity;
                }
            }
        return null;
    }

    /**
     * 获取指定活动页面的个数
     * @param cls
     * @return
     */
    public int getActivityCount(Class<?> cls) {
        int result = 0;
        if (activityStack != null)
            for (Activity activity : activityStack) {
                if (activity.getClass().equals(cls)) {
                    result++;
                }
            }
        return result;
    }

    /**
     * 退出应用程序
     */
    public void AppExit() {
        try {
            finishAllActivity();
            // 杀死该应用进程
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
        }
    }
}
