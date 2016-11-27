package com.xapp.jjh.xui.inter;

import android.content.Intent;
import android.os.Bundle;

import com.xapp.jjh.xui.application.FilterParams;

/**
 * ------------------------------------
 * Created by Taurus on 2016/8/16.
 * ------------------------------------
 */
public interface IFilterInterface {
    FilterParams onStartActivityForResult(Intent intent, int requestCode, Bundle options);
}
