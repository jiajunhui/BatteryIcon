package com.xapp.jjh.xui.activity;

import android.os.Bundle;

import com.xapp.jjh.xui.config.XUIConfig;
import com.xapp.jjh.xui.lib.swipebackhelper.SwipeBackHelper;
import com.xapp.jjh.xui.lib.swipebackhelper.SwipeBackPage;
import com.xapp.jjh.xui.lib.swipebackhelper.SwipeListener;

/**
 * ------------------------------------
 * Created by Taurus on 2016/8/11.
 * ------------------------------------
 */
public abstract class SwipeBackActivity extends ToolsActivity implements SwipeListener{

    /**
     * 是否使用侧拉手势 (默认使用)
     */
    private boolean swipeEnable = XUIConfig.isUseSwipeBack();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SwipeBackHelper.onCreate(this);
        initSwipe();
    }

    /**
     * 初始化手势处理配置
     */
    private void initSwipe() {
        SwipeBackHelper.getCurrentPage(this).addListener(this)
                .setSwipeBackEnable(swipeEnable)
                .setSwipeSensitivity(0.5f)
                .setSwipeEdgePercent(XUIConfig.getSwipeEdgePercent())
                .setSwipeRelateEnable(true)
                .setSwipeSensitivity(1);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        SwipeBackHelper.onDestroy(this);
    }

    /**
     * 设置是否允许侧滑返回手势,必须在onCreate中调用
     * @param enable
     */
    protected void setSwipeBackEnable(boolean enable){
        this.swipeEnable = enable;
    }

    protected void updateSwipeEnable(boolean swipeEnable){
        this.swipeEnable = swipeEnable;
        SwipeBackPage swipeBackPage = SwipeBackHelper.findHelperByActivity(this);
        if(swipeBackPage!=null){
            swipeBackPage.setSwipeBackEnable(swipeEnable);
        }
    }

    public void onScroll(float percent,int px){}
    public void onEdgeTouch(){}
    /**
     * Invoke when scroll percent over the threshold for the first time
     */
    public void onScrollToClose(){}

}
