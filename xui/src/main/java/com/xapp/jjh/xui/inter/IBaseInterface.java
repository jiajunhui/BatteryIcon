package com.xapp.jjh.xui.inter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * ------------------------------------
 * Created by Taurus on 2016/8/11.
 * ------------------------------------
 */
public interface IBaseInterface {
    /** the method ,you can use it for restore instance*/
    void initRestoreInstance(Bundle savedInstanceState);
    /** you can get intent params in this method*/
    void parseIntent();
    /** return you activity user content view*/
    View getContentView(LayoutInflater inflater, ViewGroup container);
    /** you can find some views in this method*/
    void findViewById();
    /** in this method you can init something*/
    void initData();
    /** you can set view listener in this method*/
    void setListener();
}
