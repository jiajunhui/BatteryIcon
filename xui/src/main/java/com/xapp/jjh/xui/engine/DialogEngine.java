package com.xapp.jjh.xui.engine;

import android.app.Activity;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import com.xapp.jjh.xui.inter.DialogListener;
import com.xapp.jjh.xui.lib.niftydialog.DialogInfo;
import com.xapp.jjh.xui.lib.niftydialog.NiftyDialogBuilder;

/**
 * Created by Taurus on 16/8/14.
 */
public class DialogEngine {

    private static final int DIALOG_ANIMATION_DURATION = 200;

    public static void showDialog(final Activity activity, DialogInfo dialogInfo, final DialogListener dialogCallBack ){
        if(dialogInfo==null || activity==null)
            return;
        if(TextUtils.isEmpty(dialogInfo.getMessage())){
            return;
        }
        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(activity);
        dialogBuilder
                .withTitle(null)
                .withMessage(dialogInfo.getMessage())
                .withDuration(DIALOG_ANIMATION_DURATION)
                .withEffect(dialogInfo.getEffect())
                .isCancelableOnTouchOutside(dialogInfo.isTouchCancel());
        String leftBtnText = dialogInfo.getLeftBtnText();
        String rightBtnText = dialogInfo.getRightBtnText();
        if(dialogInfo.isSingleBtn()){
            dialogBuilder.withSingleButtonText(leftBtnText);
            dialogBuilder.setButton1Click(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dialogCallBack!=null){
                        dialogCallBack.onLeftClick(dialogBuilder);
                    }
                    dialogBuilder.dismiss();
                }
            }).show();
            if(dialogCallBack!=null){
                dialogCallBack.onDialogShow();
            }
        }else{
            if(!TextUtils.isEmpty(leftBtnText)){
                dialogBuilder.withButton1Text(leftBtnText);
            }
            if(!TextUtils.isEmpty(rightBtnText)){
                dialogBuilder.withButton2Text(rightBtnText);
            }
            dialogBuilder.setButton1Click(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(dialogCallBack!=null){
                        dialogCallBack.onLeftClick(dialogBuilder);
                    }
                    dialogBuilder.dismiss();
                }
            }).setButton2Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(dialogCallBack!=null){
                            dialogCallBack.onRightClick(dialogBuilder);
                        }
                        dialogBuilder.dismiss();
                    }
                })
                .show();
            if(dialogCallBack!=null){
                dialogCallBack.onDialogShow();
            }
        }
        dialogBuilder.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if(dialogCallBack!=null){
                    dialogCallBack.onDialogDismiss();
                }
            }
        });
    }
}
