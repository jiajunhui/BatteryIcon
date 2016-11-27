package com.xapp.jjh.xui.engine;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.xapp.jjh.xui.inter.ILoadStateHandle;
import com.xapp.jjh.xui.inter.ILoadStateInterface;
import com.xapp.jjh.xui.inter.ILoadStateListener;
import com.xapp.jjh.xui.inter.PageState;
import com.xapp.jjh.xui.view.LoadingDialog;

/**
 * Created by Taurus on 16/8/14.
 */
public class LoadStateEngine implements ILoadStateHandle{

    private Context mContext;
    private View mContainer;
    private ILoadStateInterface mILoadStateInterface;
    private ILoadStateListener mILoadStateListener;
    private ViewGroup.LayoutParams params = new ViewGroup.LayoutParams
                (ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
    private LoadingDialog mLoadingDialog;

    public LoadStateEngine(Context context, View container, ILoadStateInterface iLoadStateInterface, ILoadStateListener iLoadStateListener){
        this.mContext = context;
        this.mContainer = container;
        this.mILoadStateInterface = iLoadStateInterface;
        this.mILoadStateListener = iLoadStateListener;
    }

    @Override
    public void setPageState(PageState pageState) {
        removeAllState();
        if(PageState.LOADING == pageState){
            setLoadingState(true);
        }else if(PageState.ERROR == pageState){
            setErrorState(true);
        }
    }

    private void setLoadingState(boolean visible) {
        if(visible && mContainer!=null && mContainer instanceof ViewGroup){
            final View loadingView = mILoadStateInterface.getLoadingView();
            loadingView.setLayoutParams(params);
            ((ViewGroup)mContainer).addView(loadingView);
            loadingView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

    private void setErrorState(boolean visible) {
        if(visible && mContainer!=null && mContainer instanceof ViewGroup){
            final View errorView = mILoadStateInterface.getErrorView();
            errorView.setLayoutParams(params);
            ((ViewGroup)mContainer).addView(errorView);
            errorView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mILoadStateListener!=null){
                        mILoadStateListener.retryLoad();
                    }
                }
            });
        }
    }

    @Override
    public void showLoadingDialog(String message) {
        if(mLoadingDialog==null){
            mLoadingDialog = new LoadingDialog(mContext);
        }
        mLoadingDialog.setText(message);
        mLoadingDialog.show();
    }

    @Override
    public void closeLoadingDialog() {
        if(mLoadingDialog!=null){
            mLoadingDialog.dismiss();
        }
    }

    private void removeAllState(){
        if(mContainer!=null && mContainer instanceof ViewGroup){
            ((ViewGroup)mContainer).removeAllViews();
        }
    }
}
