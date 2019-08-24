package com.vivy.docsearch.di;

import com.vivy.docsearch.ui.DoctorListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * This builder provides android injector service to fragments
 */
@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract DoctorListFragment contributeDoctorListFragment();
}
