package com.cheney.mvvm_demo;

import android.content.Context;

/**
 * 全局context
 */
public class AppInstance {
    private static AppInstance instance;
    private Context appContext;

    public static AppInstance getInstance() {
        if(instance == null) {
            synchronized (AppInstance.class) {
                if(instance == null) {
                    instance = new AppInstance();
                    return instance;
                }
            }
        }

        return instance;
    }
    private AppInstance() {

    }
    public void setAppContext(Context context) {
        appContext = context;
    }
    public Context getAppContext() {
        return appContext;
    }
}
