package com.xapp.jiajunhui.batteryicon;

import com.xapp.jjh.xui.application.XUIApplication;
import com.xapp.jjh.xui.config.XUIConfig;

/**
 * Created by Taurus on 16/11/27.
 */

public class MApplication extends XUIApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        XUIConfig.setXUIWhiteStyle();
    }
}
