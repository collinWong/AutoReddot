package com.colin.reddot;

import android.app.Application;

/**
 * Created by huangchuangliang on 2019/2/25.
 */
public class DemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ReddotList.init();
    }
}
