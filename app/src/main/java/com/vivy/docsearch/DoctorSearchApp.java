package com.vivy.docsearch;

import android.app.Activity;
import android.app.Application;

import com.vivy.docsearch.di.DaggerAppComponent;

import javax.inject.Inject;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Application class for the app
 */
public class DoctorSearchApp extends Application implements HasActivityInjector {
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingInjector;
    private static DoctorSearchApp sInstance;

    public static DoctorSearchApp getAppContext() {
        return sInstance;
    }

    private static synchronized void setInstance(DoctorSearchApp app) {
        sInstance = app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
        setInstance(this);
    }

    private void initializeComponent() {
        DaggerAppComponent.builder().application(this).build().inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingInjector;
    }
}
