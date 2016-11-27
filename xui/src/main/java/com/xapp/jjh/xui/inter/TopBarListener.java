package com.xapp.jjh.xui.inter;

/**
 * Created by Taurus on 16/8/11.
 */
public interface TopBarListener {
    /** the method will executed , when the navigation clicked*/
    void onNavigationClick();
    /** the method will executed , when the top bar center title clicked*/
    void onTitleClick();
    /** the method will executed , when the top bar menu clicked*/
    void onMenuClick();
}
