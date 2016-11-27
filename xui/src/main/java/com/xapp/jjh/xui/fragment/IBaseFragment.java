package com.xapp.jjh.xui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.xapp.jjh.xui.inter.IBaseInterface;

/**
 * Created by Taurus on 15/11/22.
 * 应用内所有Fragment的基类
 */
public abstract class IBaseFragment extends Fragment implements View.OnClickListener ,IBaseInterface{

    protected Context mContext;
    public FrameLayout mLoadingContainer;
    protected View mUserView;
    private ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
            , ViewGroup.LayoutParams.MATCH_PARENT);

    @Override
    public void onAttach(Context activity) {
        mContext = activity;
        super.onAttach(activity);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        setListener();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = packageLayout(getContentView(inflater,container));
        initRestoreInstance(savedInstanceState);
        afterLayoutInit();
        findViewById();
        return root;
    }

    @Override
    public void initRestoreInstance(Bundle savedInstanceState) {

    }

    protected void afterLayoutInit() {

    }

    private View packageLayout(View userView){
        mUserView = userView;
        FrameLayout root = new FrameLayout(mContext);
        root.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        root.addView(userView,new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mLoadingContainer = new FrameLayout(mContext);
        mLoadingContainer.setLayoutParams(params);
        mLoadingContainer.setBackgroundColor(Color.TRANSPARENT);
        root.addView(mLoadingContainer);
        return root;
    }

    @Override
    public void onClick(View v) {

    }

    public void selected(Object object){

    }

    public void select(){

    }

    @Override
    public void parseIntent() {

    }

    @Override
    public abstract View getContentView(LayoutInflater inflater, ViewGroup container);

    @Override
    public abstract void findViewById();

    @Override
    public abstract void initData();

    @Override
    public abstract void setListener();

}
