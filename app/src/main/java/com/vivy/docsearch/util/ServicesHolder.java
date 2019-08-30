package com.vivy.docsearch.util;

import androidx.annotation.NonNull;

import com.vivy.docsearch.api.ApiService;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class to hold the api service
 */
@Singleton
public class ServicesHolder {
    private ApiService apiService;

    @Inject
    public ServicesHolder(){
        // Empty Constructor
    }

    @NonNull
    public ApiService getApiService() {
        return apiService;
    }

    public void setApiService(@NonNull ApiService myService) {
        this.apiService = myService;
    }
}
