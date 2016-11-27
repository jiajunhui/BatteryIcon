package com.xapp.jjh.xui.inter;

import android.view.View;

import com.xapp.jjh.xui.bean.BaseMenuItem;

import java.util.List;

/**
 * Created by Taurus on 16/8/12.
 */
public interface ITopBarHandle {
    /** return navigation view object*/
    View getNavigationView();
    /** return top bar title view object*/
    View getTitleView();
    /** return top bar menu view object , may be ImageView or TextView */
    View getMenuView();
    /** return the top bar view object*/
    View getTopBarView();
    /** return the top bar visible state*/
    boolean isTopBarVisible();
    /** set top bar shadow enable state*/
    void setShadowEnable(boolean enable);
    /** set color for top bar background color*/
    void setTopBarColor(int color);
    /** set top bar visible state*/
    void setTopBarVisible(boolean visible);
    /** find top bar view by view id*/
    <T extends View> T findTopBarViewById(int id);
    /** set top bar shadow visible state*/
    void setShadowVisible(boolean state);
    /** set custom navigation icon*/
    void setNavigationIcon(int resId);
    /** set navigation icon visible state*/
    void setNavigationVisible(boolean visible);
    /** set top bar center title text*/
    void setTopBarTitle(String title);
    /** set top bar center title drawable*/
    void setTitleDrawable(int drawableType,int drawId);
    /** is need menu function*/
    void setMenuEnable(boolean enable);
    /** set top bar menu type and value for resource id*/
    void setMenuType(MenuType type,int resId);
    /** show menu list*/
    void showMenuList(List<? extends BaseMenuItem> list,OnMenuItemClickListener onMenuItemClickListener);
    /** set top bar menu text when menu type is text*/
    void setMenuText(String menuText);
    /** set top bar menu icon when menu type is icon*/
    void setMenuIcon(int icon);
}
