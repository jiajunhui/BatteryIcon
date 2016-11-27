package com.xapp.jjh.xui.activity;

import android.view.LayoutInflater;
import android.view.View;
import com.xapp.jjh.xui.R;
import com.xapp.jjh.xui.bean.BaseMenuItem;
import com.xapp.jjh.xui.config.XUIConfig;
import com.xapp.jjh.xui.engine.TopBarViewEngine;
import com.xapp.jjh.xui.inter.ITopBarHandle;
import com.xapp.jjh.xui.inter.ITopBarInterface;
import com.xapp.jjh.xui.inter.MenuType;
import com.xapp.jjh.xui.inter.OnMenuItemClickListener;
import com.xapp.jjh.xui.inter.TopBarListener;

import java.util.List;

/**
 * ------------------------------------
 * Created by Taurus on 2016/8/11.
 * ------------------------------------
 */
public abstract class TopBarActivity extends LoadStateActivity implements ITopBarInterface,ITopBarHandle,TopBarListener{

    private TopBarViewEngine mTopBarViewEngine;

    @Override
    public void loadViewLayout() {
        this.mTopBarViewEngine = new TopBarViewEngine(getApplicationContext(),getContentView(mLayoutInflater,null),this,this);
        setUserContentView(mTopBarViewEngine.getLoadContainer());
        super.setContentView(mTopBarViewEngine);
        if(XUIConfig.getStatusBarColor()!=-1){
            setStatusBarColor(getResources().getColor(XUIConfig.getStatusBarColor()));
            if(XUIConfig.isUseStatusBarDarkMode()){
                setStatusLightMode(true);
            }
        }
    }

    @Override
    public View getDefaultTopBarView() {
        return LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_default_topbar,null,false);
    }

    @Override
    public View getCustomTopBarView() {
        return null;
    }

    @Override
    public View getTopBarView() {
        if(mTopBarViewEngine==null)
            return null;
        return mTopBarViewEngine.getTopBarView();
    }

    @Override
    public <T extends View> T findView(int id) {
        View view = super.findView(id);
        if(view == null){
            view = findTopBarViewById(id);
        }
        if(view == null)
            return null;
        return (T) view;
    }

    @Override
    public boolean isTopBarVisible() {
        if(mTopBarViewEngine==null)
            return false;
        return mTopBarViewEngine.isTopBarVisible();
    }

    @Override
    public void setShadowEnable(boolean enable) {
        if(mTopBarViewEngine!=null){
            mTopBarViewEngine.setShadowEnable(enable);
        }
    }

    @Override
    public void setTopBarColor(int color) {
        if(mTopBarViewEngine!=null){
            mTopBarViewEngine.setTopBarColor(color);
        }
    }

    @Override
    public void setTopBarVisible(boolean visible) {
        if(mTopBarViewEngine!=null){
            mTopBarViewEngine.setTopBarVisible(visible);
        }
    }

    @Override
    public void setShadowVisible(boolean state) {
        if(mTopBarViewEngine!=null){
            mTopBarViewEngine.setShadowVisible(state);
        }
    }

    @Override
    public View getNavigationView() {
        if(mTopBarViewEngine==null)
            return null;
        return mTopBarViewEngine.getNavigationView();
    }

    @Override
    public View getTitleView() {
        if(mTopBarViewEngine==null)
            return null;
        return mTopBarViewEngine.getTitleView();
    }

    @Override
    public View getMenuView() {
        if(mTopBarViewEngine == null){
            return null;
        }
        return mTopBarViewEngine.getMenuView();
    }

    @Override
    public <T extends View> T findTopBarViewById(int id) {
        if(mTopBarViewEngine==null)
            return null;
        return mTopBarViewEngine.findTopBarViewById(id);
    }

    @Override
    public void setTopBarTitle(String title) {
        if(mTopBarViewEngine!=null){
            mTopBarViewEngine.setTopBarTitle(title);
        }
    }

    @Override
    public void setTitleDrawable(int drawableType, int drawId) {
        if(mTopBarViewEngine!=null){
            mTopBarViewEngine.setTitleDrawable(drawableType, drawId);
        }
    }

    @Override
    public void setNavigationIcon(int resId) {
        if(mTopBarViewEngine!=null){
            mTopBarViewEngine.setNavigationIcon(resId);
        }
    }

    @Override
    public void setNavigationVisible(boolean visible) {
        if(mTopBarViewEngine!=null){
            mTopBarViewEngine.setNavigationVisible(visible);
        }
    }

    @Override
    public void setMenuEnable(boolean enable) {
        if(mTopBarViewEngine!=null){
            mTopBarViewEngine.setMenuEnable(enable);
        }
    }

    @Override
    public void setMenuType(MenuType type, int resId) {
        if(mTopBarViewEngine!=null){
            mTopBarViewEngine.setMenuType(type, resId);
        }
    }

    @Override
    public void showMenuList(List<? extends BaseMenuItem> list, OnMenuItemClickListener onMenuItemClickListener){
        if(mTopBarViewEngine!=null){
            mTopBarViewEngine.showMenuList(list, onMenuItemClickListener);
        }
    }

    @Override
    public void setMenuText(String menuText) {
        if(mTopBarViewEngine!=null){
            mTopBarViewEngine.setMenuText(menuText);
        }
    }

    @Override
    public void setMenuIcon(int icon) {
        if(mTopBarViewEngine!=null){
            mTopBarViewEngine.setMenuIcon(icon);
        }
    }

    @Override
    public void onNavigationClick() {
        backSpace();
    }

    @Override
    public void onTitleClick() {

    }

    @Override
    public void onMenuClick() {

    }

    @Override
    public void fullScreen() {
        super.fullScreen();
        setTopBarVisible(false);
        setShadowVisible(false);
    }

    @Override
    public void quitFullScreen() {
        super.quitFullScreen();
        setTopBarVisible(true);
        setShadowVisible(true);
    }
}
