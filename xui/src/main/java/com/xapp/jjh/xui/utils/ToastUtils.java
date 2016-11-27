package com.xapp.jjh.xui.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by cyw on 2015/12/15.
 */
public class ToastUtils {

    private static Toast mToast;

    private static Toast getToastObj(Context context){
        return Toast.makeText(context,"",Toast.LENGTH_SHORT);
    }

    public static void showShortToast(Context context,int message){
        showShortToast(context, context.getString(message));
    }
    public static void showShortToast(Context context, CharSequence message){
        if(context == null)
            return;
        if(mToast==null){
            mToast = getToastObj(context);
        }
        mToast.setText(message);
        mToast.setDuration(Toast.LENGTH_SHORT);
        mToast.show();
    }

    public static void showLongToast(Context context,CharSequence message){
        if(context == null)
            return;
        if(mToast==null){
            mToast = getToastObj(context);
        }
        mToast.setText(message);
        mToast.setDuration(Toast.LENGTH_LONG);
        mToast.show();
    }

}
