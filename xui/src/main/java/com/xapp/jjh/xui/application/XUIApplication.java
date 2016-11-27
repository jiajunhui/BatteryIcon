package com.xapp.jjh.xui.application;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.xapp.jjh.xui.inter.IFilterInterface;

/**
 * ------------------------------------
 * Created by Taurus on 2016/8/16.
 * ------------------------------------
 */
public class XUIApplication extends Application implements IFilterInterface,Application.ActivityLifecycleCallbacks{

    private final String TAG = "XUIApplication";
    private static XUIApplication instance;

    private ForegroundBackgroundListener foregroundBackgroundListener;
    private int prevCount;
    private int startedActivityCount;

    public static XUIApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onTerminate() {
        unregisterActivityLifecycleCallbacks(this);
        super.onTerminate();
    }

    public void setForegroundBackgroundListener(ForegroundBackgroundListener foregroundBackgroundListener){
        this.foregroundBackgroundListener  = foregroundBackgroundListener;
    }

    @Override
    public FilterParams onStartActivityForResult(Intent intent, int requestCode, Bundle options) {
        Log.d(TAG,"Intent TO : " + getComponentName(intent));
        return null;
    }

    public String getComponentName(Intent intent){
        ComponentName componentName = intent.getComponent();
        if(componentName==null)
            return "";
        return componentName.getClassName();
    }

    private void checkRunState() {
        if(startedActivityCount==0){
            Log.d(TAG,"--------------[[ Enter Background ]]--------------");
            if(foregroundBackgroundListener!=null){
                foregroundBackgroundListener.enterBackground();
            }
        }else if(startedActivityCount==1 && prevCount==0){
            Log.d(TAG,"--------------[[ Enter foreground ]]--------------");
            if(foregroundBackgroundListener!=null){
                foregroundBackgroundListener.enterForeground();
            }
        }
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Log.d(TAG,"ActivityCreated:" + activity.getLocalClassName());
    }

    @Override
    public void onActivityStarted(Activity activity) {
        prevCount = startedActivityCount;
        startedActivityCount++;
        checkRunState();
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        prevCount = startedActivityCount;
        startedActivityCount--;
        checkRunState();
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Log.d(TAG,"ActivityDestroyed:" + activity.getLocalClassName());
    }

    public interface ForegroundBackgroundListener{
        void enterForeground();
        void enterBackground();
    }
}
