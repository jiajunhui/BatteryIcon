package com.xapp.jjh.xui.inter;

import android.view.View;

/**
 * ------------------------------------
 * Created by Taurus on 2016/8/11.
 * ------------------------------------
 */
public interface ITopBarInterface {
    /** return the default top bar view*/
    View getDefaultTopBarView();
    /** if you want custom top bar view , use this method*/
    View getCustomTopBarView();
}
