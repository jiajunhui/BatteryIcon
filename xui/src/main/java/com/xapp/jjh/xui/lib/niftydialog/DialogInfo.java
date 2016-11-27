package com.xapp.jjh.xui.lib.niftydialog;

import android.content.Context;

import com.xapp.jjh.xui.R;

import java.io.Serializable;

/**
 * Created by Taurus on 2015/12/15.
 * dialog设置信息
 */
public class DialogInfo implements Serializable{
    private String message;
    private String leftBtnText;
    private String rightBtnText;
    private Effectstype effect;
    /**
     * 当为单个按钮时，默认使用leftBtn,文字请设置在leftBtn上
     */
    private boolean singleBtn;
    private boolean touchCancel;
    public DialogInfo(){

    }

    private static DialogInfo instance = new DialogInfo();

    public static DialogInfo getDefaultDoubleButton(Context context, String message){
        instance = new DialogInfo();
        return instance.setEffect(Effectstype.Slidetop)
                .setTouchCancel(false)
                .setLeftBtnText(context.getString(R.string.ios_dialog_cancel))
                .setRightBtnText(context.getString(R.string.ios_dialog_confirm))
                .setMessage(message);
    }

    public static DialogInfo getDefaultShakeDoubleButton(Context context, String message){
        instance = new DialogInfo();
        return instance.setEffect(Effectstype.Shake)
                .setTouchCancel(false)
                .setLeftBtnText(context.getString(R.string.ios_dialog_cancel))
                .setRightBtnText(context.getString(R.string.ios_dialog_confirm))
                .setMessage(message);
    }

    public static DialogInfo getDefaultDoubleButton(String message,String cancelText, String okText){
        instance = new DialogInfo();
        return instance.setEffect(Effectstype.Slidetop)
                .setTouchCancel(false)
                .setLeftBtnText(cancelText)
                .setRightBtnText(okText)
                .setMessage(message);
    }

    public static DialogInfo getDefaultSingleButton(Context context, String message){
        instance = new DialogInfo();
        return instance.setEffect(Effectstype.Slidetop)
                .setTouchCancel(false)
                .setLeftBtnText(context.getString(R.string.ios_dialog_confirm))
                .setMessage(message);
    }

    public static DialogInfo getDefaultSingleButton(String message, String buttonText){
        instance = new DialogInfo();
        return instance.setEffect(Effectstype.Slidetop)
                .setTouchCancel(false)
                .setLeftBtnText(buttonText)
                .setMessage(message);
    }

    public DialogInfo(String message, String leftBtnText, String rightBtnText, Effectstype effect, boolean singleBtn, boolean touchCancel){
        this.message = message;
        this.leftBtnText = leftBtnText;
        this.rightBtnText = rightBtnText;
        this.effect = effect;
        this.singleBtn = singleBtn;
        this.touchCancel = touchCancel;
    }

    public String getMessage() {
        return message;
    }

    public DialogInfo setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getLeftBtnText() {
        return leftBtnText;
    }

    public DialogInfo setLeftBtnText(String leftBtnText) {
        this.leftBtnText = leftBtnText;
        return this;
    }

    public String getRightBtnText() {
        return rightBtnText;
    }

    public DialogInfo setRightBtnText(String rightBtnText) {
        this.rightBtnText = rightBtnText;
        return this;
    }

    public Effectstype getEffect() {
        return effect;
    }

    public DialogInfo setEffect(Effectstype effect) {
        this.effect = effect;
        return this;
    }

    public boolean isSingleBtn() {
        return singleBtn;
    }

    public DialogInfo setSingleBtn(boolean singleBtn) {
        this.singleBtn = singleBtn;
        return this;
    }

    public boolean isTouchCancel() {
        return touchCancel;
    }

    public DialogInfo setTouchCancel(boolean touchCancel) {
        this.touchCancel = touchCancel;
        return this;
    }
}
