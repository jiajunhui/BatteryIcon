package com.xapp.jjh.xui.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xapp.jjh.xui.R;
import com.xapp.jjh.xui.inter.IBaseInterface;
import com.xapp.jjh.xui.utils.ActivityStackManager;

/**
 * ------------------------------------
 * Created by Taurus on 2016/8/11.
 * ------------------------------------
 */
public abstract class IBaseActivity extends AppCompatActivity implements IBaseInterface,View.OnClickListener {

    protected int theme = R.style.AppBaseTheme;
    protected LayoutInflater mLayoutInflater;
    protected View mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initRestoreInstance(savedInstanceState);
        ActivityStackManager.getInstance().addActivity(this);
        parseIntent();
        beforeSetContentView();
        initInflater();
        loadViewLayout();
        findViewById();
        initData();
        setListener();
    }

    @Override
    public void initRestoreInstance(Bundle savedInstanceState) {

    }

    private void initInflater() {
        mLayoutInflater = LayoutInflater.from(new ContextThemeWrapper(getApplicationContext(), theme));
    }

    private void beforeSetContentView() {

    }

    public void loadViewLayout(){
        setContentView(getContentView(mLayoutInflater,null));
    }

    @Override
    public void setContentView(View view) {
        mRootView = view;
        super.setContentView(view);
    }

    @Override
    public abstract View getContentView(LayoutInflater layoutInflater, ViewGroup container);

    @Override
    public void parseIntent() {

    }

    @Override
    public abstract void findViewById();

    @Override
    public abstract void initData();

    @Override
    public void setListener() {

    }

    @Override
    public void onClick(View v) {

    }

    protected void backSpace() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityStackManager.getInstance().removeActivity(this);
    }
}
