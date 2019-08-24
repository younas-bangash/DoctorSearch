package com.vivy.docsearch.di;

import android.app.Application;

import com.vivy.docsearch.DoctorSearchApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * The main application component which initializes all the dependent modules
 */
@Singleton
@Component(modules = {AppModule.class, AndroidInjectionModule.class, ActivityBuilderModule.class})
public interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(DoctorSearchApp app);
}