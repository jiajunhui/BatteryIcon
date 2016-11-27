package com.xapp.jjh.xui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xapp.jjh.xui.application.FilterParams;
import com.xapp.jjh.xui.application.XUIApplication;
import com.xapp.jjh.xui.inter.IFilterInterface;

/**
 * ------------------------------------
 * Created by Taurus on 2016/8/11.
 * ------------------------------------
 */
public abstract class FilterActivity extends IBaseActivity implements IFilterInterface{

    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        FilterParams filterParams = onStartActivityForResult(intent, requestCode, options);
        if(filterParams==null){
            super.startActivityForResult(intent, requestCode, options);
        }else{
            super.startActivityForResult(filterParams.getIntent(), filterParams.getRequestCode(), filterParams.getOptions());
        }
    }

    @Override
    public FilterParams onStartActivityForResult(Intent intent, int requestCode, Bundle options) {
        XUIApplication application = XUIApplication.getInstance();
        if(application!=null){
            return application.onStartActivityForResult(intent, requestCode, options);
        }
        return null;
    }

}
