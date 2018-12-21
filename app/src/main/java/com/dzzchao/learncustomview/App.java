package com.dzzchao.learncustomview;

import android.app.Application;
import android.os.Debug;

import timber.log.Timber;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());
    }
}
