package com.xapp.jjh.xui.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;

import java.util.List;

/**
 * Created by Taurus on 2016/1/7.
 */
public class CommonUtils {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int[] getScreenDp(Context context) {
        // 取得窗口属性
        DisplayMetrics dm = context.getResources().getDisplayMetrics();

        int[] dips = new int[2];
        // 窗口的宽度
        dips[0] = px2dip(context, dm.widthPixels);
        // 窗口高度
        dips[1] = px2dip(context, dm.heightPixels);
        return dips;
    }

    public static int getWidth(Context activity){
        return getScreenPx(activity)[0];
    }

    public static int getHeight(Context activity){
        return getScreenPx(activity)[1];
    }

    public static int[] getScreenPx(Context context) {
        // 取得窗口属性
        DisplayMetrics dm = context.getResources().getDisplayMetrics();

        int[] dips = new int[2];
        // 窗口的宽度
        dips[0] = dm.widthPixels;
        // 窗口高度
        dips[1] = dm.heightPixels;
        return dips;
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable();
        }
        return false;
    }

    /**
     * @return null may be returned if the specified process not found
     */
    public static String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }

    public static int canParseInt(String number){
        int result = Integer.MIN_VALUE;
        try{
            result = Integer.parseInt(number);
        }catch (NumberFormatException e){
            e.printStackTrace();
        }
        return result;
    }

}
