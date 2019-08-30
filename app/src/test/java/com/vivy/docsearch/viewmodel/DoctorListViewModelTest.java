package com.vivy.docsearch.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.vivy.docsearch.api.response.ErrorResponse;
import com.vivy.docsearch.repository.DoctorListRepository;
import com.vivy.docsearch.util.PaginationScrollListener;
import com.vivy.docsearch.util.ServicesHolder;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Test class for DoctorListViewModel
 */
@RunWith(MockitoJUnitRunner.class)
public class DoctorListViewModelTest {
    @Inject
    private ServicesHolder servicesHolder;
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    @Mock
    private Disposable disposable;
    @Mock
    private PaginationScrollListener paginationScrollListener;
    @Mock
    private DoctorListRepository repository;
    private DoctorListViewModel doctorListViewModel;

    @Before
    public void setUp(){
        doctorListViewModel = new DoctorListViewModel(repository, servicesHolder);
    }

    @After
    public void tearDown(){
        doctorListViewModel = null;
    }

    @Test
    public void getDoctorListAdapter() {
        Assert.assertNotNull(doctorListViewModel.getDoctorListAdapter());
    }

    @Test
    public void onFailure() {
        ErrorResponse response = new ErrorResponse();

        doctorListViewModel.onFailure(response);

        Assert.assertTrue(doctorListViewModel.serviceResponse.getValue());
        Assert.assertSame(response, doctorListViewModel.serviceError.getValue());
    }

    @Test
    public void loadNextPage() {
    }

    @Test
    public void getterSetterSearchViewDisposable() {
        doctorListViewModel.setSearchViewDisposable(disposable);

        Assert.assertNotNull(doctorListViewModel.getSearchViewDisposable());
    }

    @Test
    public void getterSetterPaginationScrollListener() {
        doctorListViewModel.setPaginationScrollListener(paginationScrollListener);

        Assert.assertNotNull(doctorListViewModel.getPaginationScrollListener());
    }
}