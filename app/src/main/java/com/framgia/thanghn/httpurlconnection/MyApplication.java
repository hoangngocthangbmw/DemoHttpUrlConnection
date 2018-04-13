package com.framgia.thanghn.httpurlconnection;

import android.app.Application;
import android.content.Context;

/**
 * Created by thang on 4/13/2018.
 */

public class MyApplication extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}