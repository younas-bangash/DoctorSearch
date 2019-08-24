package com.vivy.docsearch.repository;

import androidx.annotation.NonNull;

import com.vivy.docsearch.api.ApiService;
import com.vivy.docsearch.api.ServiceRequest;
import com.vivy.docsearch.api.response.DoctorSearchResponse;
import com.vivy.docsearch.api.response.ResponseListener;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * Repository that is used for DoctorFragment
 */
public class DoctorListRepository {
    private ApiService apiService;

    @Inject
    public DoctorListRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public void loadDoctorList(ResponseListener<DoctorSearchResponse> listener, String lastKey,
                               String name, String lat, String lng) {
        new ServiceRequest<DoctorSearchResponse>(listener) {
            @NonNull
            @Override
            protected Call<DoctorSearchResponse> createCall() {
                return apiService.searchDoctor(lastKey, name, lat, lng);
            }
        };
    }
}
