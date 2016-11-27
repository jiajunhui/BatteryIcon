package com.xapp.jjh.xui.inter;

import android.content.Context;
import android.view.View;
import com.xapp.jjh.xui.lib.niftydialog.DialogInfo;

/**
 * ------------------------------------
 * Created by Taurus on 2016/8/11.
 * ------------------------------------
 */
public interface IToolsInterface {
    /** find view , No need for conversion*/
    <T extends View> T findView(int id);
    /** show toast , default is short toast*/
    void showToast(String message);
    /** show snackbar*/
    void showSnackBar(String message,String action,View.OnClickListener onClickListener);
    void showSnackBar(String message,int duration, String action,View.OnClickListener onClickListener);
    /** show dialog */
    void showDialog(String message,DialogListener dialogListener);
    void showDialog(DialogInfo dialogInfo, DialogListener dialogListener);
    void showDialogSingleButton(String message,DialogListener dialogListener);
    void showDialogSingleButton(String message,String buttonText, DialogListener dialogListener);
    /** Return to network connection status */
    boolean isNetworkConnected();
    /** Return to network connection status is wifi */
    boolean isWifi();
    /** full screen for activity*/
    void fullScreen();
    /** Keep the screen always bright*/
    void keepScreenOn();
    /** quit full screen*/
    void quitFullScreen();
    /** force close keyboard*/
    void closeKeyCode(Context context);
}
