package com.xapp.jjh.xui.activity;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.xapp.jjh.xui.R;
import com.xapp.jjh.xui.engine.LoadStateEngine;
import com.xapp.jjh.xui.inter.ILoadStateHandle;
import com.xapp.jjh.xui.inter.ILoadStateInterface;
import com.xapp.jjh.xui.inter.ILoadStateListener;
import com.xapp.jjh.xui.inter.PageState;

/**
 * ------------------------------------
 * Created by Taurus on 2016/8/11.
 * ------------------------------------
 */
public abstract class LoadStateActivity extends SwipeBackActivity implements ILoadStateInterface,ILoadStateHandle,ILoadStateListener {

    private ViewGroup.LayoutParams params = new ViewGroup.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
    private LoadStateEngine mLoadStateEngine;

    protected void setUserContentView(View view){
        initContentRoot(view);
    }

    @Override
    public void loadViewLayout() {
        super.setContentView(packageLayout(getContentView(mLayoutInflater,null)));
    }

    private View packageLayout(View userView){
        FrameLayout root = new FrameLayout(getApplicationContext());
        root.addView(userView);
        FrameLayout loadContainer = new FrameLayout(getApplicationContext());
        loadContainer.setLayoutParams(params);
        loadContainer.setBackgroundColor(Color.TRANSPARENT);
        root.addView(loadContainer);
        initContentRoot(loadContainer);
        return root;
    }

    private void initContentRoot(View contentRoot){
        if(contentRoot instanceof ViewGroup){
            RelativeLayout tipContainer = new RelativeLayout(getApplicationContext());
            tipContainer.setLayoutParams(params);
            tipContainer.setBackgroundColor(Color.TRANSPARENT);
            ((ViewGroup)contentRoot).addView(tipContainer);
            mLoadStateEngine = new LoadStateEngine(this,tipContainer,this,this);
        }else{
            throw new IllegalStateException("please set your content view root instance of ViewGroup !");
        }
    }

    @Override
    public View getLoadingView() {
        return LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_default_loading,null,false);
    }

    @Override
    public View getErrorView() {
        return LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_default_error_state,null,false);
    }

    @Override
    public void setPageState(PageState pageState) {
        if(mLoadStateEngine!=null){
            mLoadStateEngine.setPageState(pageState);
        }
    }

    @Override
    public void showLoadingDialog(String message) {
        if(mLoadStateEngine!=null){
            mLoadStateEngine.showLoadingDialog(message);
        }
    }

    @Override
    public void closeLoadingDialog() {
        if(mLoadStateEngine!=null){
            mLoadStateEngine.closeLoadingDialog();
        }
    }

    @Override
    public void retryLoad(){

    }

}
