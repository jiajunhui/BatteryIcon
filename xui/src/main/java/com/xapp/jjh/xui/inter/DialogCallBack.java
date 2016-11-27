package com.xapp.jjh.xui.inter;

import android.app.Dialog;

/**
 * ------------------------------------
 * Created by Taurus on 2016/8/11.
 * ------------------------------------
 */
public abstract class DialogCallBack implements DialogListener{

    @Override
    public void onDialogShow() {

    }

    @Override
    public void onLeftClick(Dialog dialog) {
        if(dialog!=null){
            dialog.dismiss();
        }
    }

    @Override
    public abstract void onRightClick(Dialog dialog);

    @Override
    public void onDialogDismiss() {

    }
}
