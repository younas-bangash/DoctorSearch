package com.vivy.docsearch.repository;

import com.vivy.docsearch.api.ApiService;
import com.vivy.docsearch.api.response.DoctorSearchResponse;
import com.vivy.docsearch.api.response.ResponseListener;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static org.mockito.ArgumentMatchers.any;

/**
 * Test class for DoctorListRepository
 */
@RunWith(MockitoJUnitRunner.class)
public class DoctorListRepositoryTest {
    @Mock
    public Call<DoctorSearchResponse> doctorSearchResponseCall;
    @Mock
    public ApiService apiService;
    @Mock
    private ResponseListener<DoctorSearchResponse> responseListener;

    private DoctorListRepository doctorListRepository;

    @Before
    public void setUp() {
        doctorListRepository = new DoctorListRepository(apiService);
    }

    @After
    public void tearDown() {
        doctorListRepository = null;
    }

    @Test
    public void loadDoctorList() throws IOException {
        Mockito.when(apiService.searchDoctor(any(), any(), any(), any())).thenReturn(doctorSearchResponseCall);
        doctorListRepository.loadDoctorList(responseListener, any(), any(), any(), any());
        Response response = doctorSearchResponseCall.execute();

    }
}