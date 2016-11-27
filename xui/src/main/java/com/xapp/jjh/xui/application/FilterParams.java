package com.xapp.jjh.xui.application;

import android.content.Intent;
import android.os.Bundle;

/**
 * ------------------------------------
 * Created by Taurus on 2016/8/16.
 * ------------------------------------
 */
public class FilterParams {
    private Intent intent;
    private int requestCode;
    private Bundle options;

    public FilterParams() {
    }

    public FilterParams(Intent intent, int requestCode, Bundle options) {
        this.intent = intent;
        this.requestCode = requestCode;
        this.options = options;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public Bundle getOptions() {
        return options;
    }

    public void setOptions(Bundle options) {
        this.options = options;
    }
}
