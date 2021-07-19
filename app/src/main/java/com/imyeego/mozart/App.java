package com.imyeego.mozart;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * @authur : liuzhao
 * @time : 1/25/21 4:48 PM
 * @Des :
 */
public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
//        appComponent = DaggerAppComponent.create();
        Fresco.initialize(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
