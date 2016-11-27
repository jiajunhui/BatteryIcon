package com.xapp.jjh.xui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import com.xapp.jjh.xui.R;
import com.xapp.jjh.xui.engine.LoadStateEngine;
import com.xapp.jjh.xui.inter.ILoadStateHandle;
import com.xapp.jjh.xui.inter.ILoadStateInterface;
import com.xapp.jjh.xui.inter.ILoadStateListener;
import com.xapp.jjh.xui.inter.PageState;

/**
 * Created by Taurus on 2015/12/29.
 */
public abstract class LoadingFragment extends ToolsFragment implements ILoadStateInterface,ILoadStateHandle,ILoadStateListener{

    private LoadStateEngine mLoadingStateEngine;

    @Override
    public View getLoadingView() {
        return LayoutInflater.from(mContext).inflate(R.layout.layout_default_loading,null,false);
    }

    @Override
    public View getErrorView() {
        return LayoutInflater.from(mContext).inflate(R.layout.layout_default_error_state,null,false);
    }

    @Override
    protected void afterLayoutInit() {
        super.afterLayoutInit();
        mLoadingStateEngine = new LoadStateEngine(mContext,mLoadingContainer,this,this);
    }

    @Override
    public void setPageState(PageState pageState) {
        if(mLoadingStateEngine!=null){
            mLoadingStateEngine.setPageState(pageState);
        }
    }

    @Override
    public void showLoadingDialog(String message) {
        if(mLoadingStateEngine!=null){
            mLoadingStateEngine.showLoadingDialog(message);
        }
    }

    @Override
    public void closeLoadingDialog() {
        if(mLoadingStateEngine!=null){
            mLoadingStateEngine.closeLoadingDialog();
        }
    }

    @Override
    public void retryLoad(){

    }

}
