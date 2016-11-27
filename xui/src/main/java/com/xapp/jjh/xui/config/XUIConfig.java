package com.xapp.jjh.xui.config;

import com.xapp.jjh.xui.R;

/**
 * ------------------------------------
 * Created by Taurus on 2016/8/15.
 * ------------------------------------
 */
public class XUIConfig {
    /** if you want set custom top bar height , please set it the dimens id*/
    private static  int topBarHeight = ConstantDefault.DIMEN_TOP_BAR_HEIGHT;
    /** top bar background color , Please note the color difference with the text*/
    private static  int topBarBgColor = ConstantDefault.COLOR_TOP_BAR_BACKGROUND;
    /** default not setting status bar color id, if you want set, please init this parameter*/
    private static  int statusBarColor = ConstantDefault.COLOR_STATUS_BAR;
    /** the value is navigation icon mipmap id*/
    private static  int topBarNavigationIcon = ConstantDefault.MIPMAP_TOP_BAR_NAVIGATION_ICON;
    /** the value is navigation icon width dimens id*/
    private static  int topBarNavigationIconWidth = ConstantDefault.DIMEN_NAVIGATION_ICON_WIDTH;
    /** the value is top bar title text size , the unit is sp */
    private static  int topBarTitleTextSize = ConstantDefault.SP_TOP_BAR_TITLE_TEXT_SIZE;
    /** the value is top bar title text color id*/
    private static  int topBarTitleTextColor = ConstantDefault.COLOR_TOP_BAR_TITLE_TEXT;
    /** the value is top bar menu text size , the unit is sp */
    private static  int topBarMenuTextSize = ConstantDefault.SP_TOP_BAR_MENU_TEXT_SIZE;
    /** the value is top bar menu text color id*/
    private static  int topBarMenuTextColor = ConstantDefault.COLOR_TOP_BAR_MENU_TEXT;
    /** the value is menu icon width dimens id*/
    private static  int topBarMenuIconWidth = ConstantDefault.DIMEN_MENU_ICON_WIDTH;

    private static boolean isUseStatusBarDarkMode = false;

    private static boolean isUseSwipeBack = true;

    private static float swipeEdgePercent = ConstantDefault.PERCENT_SWIPE_EDGE;

    public static int getTopBarHeight() {
        return topBarHeight;
    }

    public static void setTopBarHeight(int topBarHeightDimen) {
        XUIConfig.topBarHeight = topBarHeightDimen;
        XUIConfig.topBarNavigationIconWidth = topBarHeightDimen;
        XUIConfig.topBarMenuIconWidth = topBarHeightDimen;
    }

    public static int getTopBarBgColor() {
        return topBarBgColor;
    }

    public static void setTopBarBgColor(int topBarBgColor) {
        XUIConfig.topBarBgColor = topBarBgColor;
    }

    public static int getStatusBarColor() {
        return statusBarColor;
    }

    public static void setStatusBarColor(int statusBarColor) {
        XUIConfig.statusBarColor = statusBarColor;
    }

    public static int getTopBarNavigationIcon() {
        return topBarNavigationIcon;
    }

    public static void setTopBarNavigationIcon(int topBarNavigationIcon) {
        XUIConfig.topBarNavigationIcon = topBarNavigationIcon;
    }

    public static int getTopBarNavigationIconWidth() {
        return topBarNavigationIconWidth;
    }

    public static void setTopBarNavigationIconWidth(int topBarNavigationIconWidth) {
        XUIConfig.topBarNavigationIconWidth = topBarNavigationIconWidth;
    }

    public static int getTopBarTitleTextSize() {
        return topBarTitleTextSize;
    }

    public static void setTopBarTitleTextSize(int topBarTitleTextSize) {
        XUIConfig.topBarTitleTextSize = topBarTitleTextSize;
    }

    public static int getTopBarTitleTextColor() {
        return topBarTitleTextColor;
    }

    public static void setTopBarTitleTextColor(int topBarTitleTextColor) {
        XUIConfig.topBarTitleTextColor = topBarTitleTextColor;
    }

    public static int getTopBarMenuTextSize() {
        return topBarMenuTextSize;
    }

    public static void setTopBarMenuTextSize(int topBarMenuTextSize) {
        XUIConfig.topBarMenuTextSize = topBarMenuTextSize;
    }

    public static int getTopBarMenuTextColor() {
        return topBarMenuTextColor;
    }

    public static void setTopBarMenuTextColor(int topBarMenuTextColor) {
        XUIConfig.topBarMenuTextColor = topBarMenuTextColor;
    }

    public static int getTopBarMenuIconWidth() {
        return topBarMenuIconWidth;
    }

    public static void setTopBarMenuIconWidth(int topBarMenuIconWidth) {
        XUIConfig.topBarMenuIconWidth = topBarMenuIconWidth;
    }

    public static boolean isUseStatusBarDarkMode() {
        return isUseStatusBarDarkMode;
    }

    public static void setUseStatusBarDarkMode(boolean isUseStatusBarDarkMode) {
        XUIConfig.isUseStatusBarDarkMode = isUseStatusBarDarkMode;
    }

    public static boolean isUseSwipeBack() {
        return isUseSwipeBack;
    }

    public static void setUseSwipeBack(boolean isUseSwipeBack) {
        XUIConfig.isUseSwipeBack = isUseSwipeBack;
    }

    public static float getSwipeEdgePercent() {
        return swipeEdgePercent<=0?ConstantDefault.PERCENT_SWIPE_EDGE:swipeEdgePercent;
    }

    public static void setSwipeEdgePercent(float swipeEdgePercent) {
        XUIConfig.swipeEdgePercent = swipeEdgePercent;
    }

    public static void setXUIRedStyle(){
        setTopBarNavigationIcon(R.mipmap.icon_back_white);
        setTopBarTitleTextColor(R.color.white);
        setTopBarMenuTextColor(R.color.white);
        setTopBarMenuTextSize(12);
        setStatusBarColor(R.color.toolbar_background_status_bar);
        setTopBarBgColor(R.color.toolbar_background);
        setTopBarHeight(R.dimen.toolbar_height);
        setTopBarMenuIconWidth(R.dimen.toolbar_height);
        setTopBarNavigationIconWidth(R.dimen.toolbar_height);
        setTopBarTitleTextSize(15);
    }

    public static void setXUIBlueStyle(){
        setTopBarNavigationIcon(R.mipmap.icon_back_white);
        setTopBarTitleTextColor(R.color.white);
        setTopBarMenuTextColor(R.color.white);
        setTopBarMenuTextSize(12);
        setStatusBarColor(R.color.blue02);
        setTopBarBgColor(R.color.blue01);
        setTopBarHeight(R.dimen.toolbar_height);
        setTopBarMenuIconWidth(R.dimen.toolbar_height);
        setTopBarNavigationIconWidth(R.dimen.toolbar_height);
        setTopBarTitleTextSize(15);
    }

    public static void setXUIWhiteStyle(){
        setUseStatusBarDarkMode(true);
        setTopBarNavigationIcon(R.mipmap.icon_back_dark);
        setTopBarTitleTextColor(R.color.light_black);
        setTopBarMenuTextColor(R.color.light_black);
        setTopBarMenuTextSize(12);
        setStatusBarColor(R.color.white);
        setTopBarBgColor(R.color.white);
        setTopBarHeight(R.dimen.toolbar_height);
        setTopBarMenuIconWidth(R.dimen.toolbar_height);
        setTopBarNavigationIconWidth(R.dimen.toolbar_height);
        setTopBarTitleTextSize(15);
    }
}
