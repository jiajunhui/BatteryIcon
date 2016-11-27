package com.xapp.jjh.xui.inter;

import android.view.View;

/**
 * ------------------------------------
 * Created by Taurus on 2016/8/11.
 * ------------------------------------
 */
public interface ILoadStateInterface {
    /** you can return loading style what you want custom */
    View getLoadingView();
    /** you can return error style what you want custom */
    View getErrorView();
}
