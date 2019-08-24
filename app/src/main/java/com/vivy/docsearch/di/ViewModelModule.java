package com.vivy.docsearch.di;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.vivy.docsearch.viewmodel.DoctorListViewModel;
import com.vivy.docsearch.viewmodel.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Inject dependencies via constructor injection
 */
@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DoctorListViewModel.class)
    abstract ViewModel bindsDoctorListViewModel(DoctorListViewModel viewModel);

    @Binds
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);
}
