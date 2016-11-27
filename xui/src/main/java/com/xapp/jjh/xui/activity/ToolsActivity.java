package com.xapp.jjh.xui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.view.ContextThemeWrapper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import com.xapp.jjh.xui.engine.DialogEngine;
import com.xapp.jjh.xui.inter.DialogListener;
import com.xapp.jjh.xui.inter.IToolsInterface;
import com.xapp.jjh.xui.lib.niftydialog.DialogInfo;
import com.xapp.jjh.xui.utils.ExtendDeviceUtil;
import com.xapp.jjh.xui.utils.ToastUtils;

/**
 * ------------------------------------
 * Created by Taurus on 2016/8/11.
 * ------------------------------------
 */
public abstract class ToolsActivity extends FilterActivity implements IToolsInterface{

    private boolean isFullScreen;

    public <T extends View> T findView(int id){
        return (T) findViewById(id);
    }

    protected View inflate(int res){
        return inflate(res, 0);
    }

    protected View inflate(int res, int theme) {
        if(theme==0){
            return LayoutInflater.from(this).inflate(res, null);
        }
        return LayoutInflater.from(new ContextThemeWrapper(this, theme)).inflate(res, null);
    }

    @SuppressLint("NewApi")
    protected void setStatusBarColor(int color){
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT){
            Window window = getWindow();
            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            // finally change the color
            window.setStatusBarColor(color);
        }
    }

    @Override
    public void showToast(String message) {
        ToastUtils.showShortToast(getApplicationContext(),message);
    }

    @Override
    public void showSnackBar(String message, String action, View.OnClickListener onClickListener) {
        showSnackBar(message, Snackbar.LENGTH_SHORT, action, onClickListener);
    }

    @Override
    public void showSnackBar(String message, int duration, String action, View.OnClickListener onClickListener) {
        if(mRootView!=null && !TextUtils.isEmpty(message)){
            if(TextUtils.isEmpty(action)){
                Snackbar.make(mRootView,message,duration).show();
            }else {
                Snackbar.make(mRootView,message,duration).setAction(action,onClickListener).show();
            }
        }
    }

    @Override
    public void showDialog(String message, DialogListener dialogListener) {
        DialogEngine.showDialog(this, DialogInfo.getDefaultDoubleButton(getApplicationContext(),message),dialogListener);
    }

    @Override
    public void showDialogSingleButton(String message, DialogListener dialogListener) {
        DialogEngine.showDialog(this,DialogInfo.getDefaultSingleButton(getApplicationContext(),message),dialogListener);
    }

    @Override
    public void showDialogSingleButton(String message, String buttonText, DialogListener dialogListener) {
        DialogEngine.showDialog(this,DialogInfo.getDefaultSingleButton(message,buttonText),dialogListener);
    }

    @Override
    public void showDialog(DialogInfo dialogInfo, DialogListener dialogListener) {
        DialogEngine.showDialog(this,dialogInfo,dialogListener);
    }

    @Override
    public boolean isNetworkConnected() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable();
        }
        return false;
    }

    @Override
    public boolean isWifi() {
        ConnectivityManager connectivityManager = (ConnectivityManager)this.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null
                && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    public void fullScreen() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        isFullScreen = true;
    }

    public void quitFullScreen(){
        final WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags &= (~WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setAttributes(attrs);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        isFullScreen = false;
    }

    protected boolean isFullScreen(){
        return isFullScreen;
    }

    public void keepScreenOn(){
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    protected void backSpace() {
        closeKeyCode(this);
        super.backSpace();
    }

    @Override
    public void closeKeyCode(Context ct) {
        View view = ((Activity) ct).getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputManger = (InputMethodManager) ct.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void setSystemDarkStatus(boolean bDark) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            View decorView = getWindow().getDecorView();
            if(decorView != null){
                int vis = decorView.getSystemUiVisibility();
                if(bDark){
                    vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                } else{
                    vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                }
                decorView.setSystemUiVisibility(vis);
            }
        }
    }

    protected void setStatusLightMode(boolean dark){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            setSystemDarkStatus(dark);
        }else{
            ExtendDeviceUtil.FlymeSetStatusBarLightMode(getWindow(),dark);
            ExtendDeviceUtil.MIUISetStatusBarLightMode(getWindow(),dark);
        }
    }

}
