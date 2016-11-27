package com.xapp.jjh.xui.view;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.xapp.jjh.xui.R;

/**
 * ------------------------------------
 * Created by Taurus on 2016/8/11.
 * ------------------------------------
 */
public class LoadingDialog extends AlertDialog {
	private TextView tips_loading_msg;
	private String message = null;

	public LoadingDialog(Context context) {
		super(context, R.style.AlertDialogStyle);
		this.message = "加载中...";
		this.setCancelable(false);
		this.setCanceledOnTouchOutside(false);
	}

	public LoadingDialog(Context context, String message) {
		super(context);
		this.message = message;
		this.setCancelable(false);
		this.setCanceledOnTouchOutside(false);
	}

	public LoadingDialog(Context context, int theme, String message) {
		super(context, theme);
		this.message = message;
		this.setCancelable(false);
		this.setCanceledOnTouchOutside(false);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.layout_loading_dialog);
		tips_loading_msg = (TextView) findViewById(R.id.tips_loading_msg);
		tips_loading_msg.setText(this.message);
	}

	@Override
	public void show() {
		if (tips_loading_msg != null) tips_loading_msg.setText(this.message);
		super.show();
	}

	public void setText(String message) {
		this.message = message;
	}

	public void setText(int resId) {
		setText(getContext().getResources().getString(resId));
	}
}
