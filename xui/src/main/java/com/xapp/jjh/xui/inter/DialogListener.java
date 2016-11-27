package com.xapp.jjh.xui.inter;

import android.app.Dialog;

/**
 * ------------------------------------
 * Created by Taurus on 2016/8/11.
 * ------------------------------------
 */
public interface DialogListener {
    /** when dialog show , The method will be executed*/
    void onDialogShow();
    /** when left button clicked , The method will be executed*/
    void onLeftClick(Dialog dialog);
    /** when right button clicked , The method will be executed*/
    void onRightClick(Dialog dialog);
    /** when dialog dismiss , The method will be executed*/
    void onDialogDismiss();
}
