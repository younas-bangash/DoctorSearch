package com.vivy.docsearch.viewmodel;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.vivy.docsearch.api.response.DoctorSearchResponse;
import com.vivy.docsearch.api.response.ErrorResponse;
import com.vivy.docsearch.api.response.ResponseListener;
import com.vivy.docsearch.repository.DoctorListRepository;
import com.vivy.docsearch.ui.DoctorListAdapter;
import com.vivy.docsearch.util.PaginationScrollListener;
import com.vivy.docsearch.util.RxSearch;
import com.vivy.docsearch.util.ServicesHolder;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * ViewModel for the DoctorListFragment
 */
public class DoctorListViewModel extends ViewModel implements ResponseListener<DoctorSearchResponse> {
    private DoctorListAdapter doctorListAdapter;
    public final MutableLiveData<ErrorResponse> serviceError = new MutableLiveData<>();
    public final MutableLiveData<Boolean> serviceResponse = new MutableLiveData<>();
    private String nextPageItemKey;
    private PaginationScrollListener paginationScrollListener;
    private DoctorListRepository repository;
    private Disposable searchViewDisposable;

    @Inject
    public DoctorListViewModel(DoctorListRepository repository, ServicesHolder servicesHolder) {
        this.repository = repository;
        doctorListAdapter = new DoctorListAdapter(new ArrayList<>(), servicesHolder);
    }

    public DoctorListAdapter getDoctorListAdapter() {
        return doctorListAdapter;
    }

    @Override
    public void onSuccess(DoctorSearchResponse data) {
        serviceResponse.postValue(true);
        if (doctorListAdapter != null) {
            nextPageItemKey = data.getLastKey();
            doctorListAdapter.updateList(data.getDoctors());

            if (nextPageItemKey != null) {
                doctorListAdapter.addLoadingFooter();
                paginationScrollListener.setLoading(false);
            } else {
                paginationScrollListener.setLastPage(true);
                doctorListAdapter.removeLoadingFooter();
            }
        }
    }

    @Override
    public void onFailure(ErrorResponse errorResponse) {
        serviceResponse.postValue(true);
        serviceError.postValue(errorResponse);
    }

    public void loadFirstPage(String doctorName) {
        doctorListAdapter.clearDoctorList();
        repository.loadDoctorList(this, nextPageItemKey, doctorName, "52.534709", "13.3976972");
    }

    public void loadNextPage(String doctorName) {
        repository.loadDoctorList(this, nextPageItemKey, doctorName, "52.534709", "13.3976972");
    }

    public Disposable getSearchViewDisposable() {
        return searchViewDisposable;
    }

    public void setSearchViewDisposable(Disposable searchViewDisposable) {
        this.searchViewDisposable = searchViewDisposable;
    }

    public PaginationScrollListener getPaginationScrollListener() {
        return paginationScrollListener;
    }

    public void setPaginationScrollListener(PaginationScrollListener listener) {
        this.paginationScrollListener = listener;
    }

    public void initSearchViewWithDebounce(@NonNull SearchView searchView) {
        setSearchViewDisposable(RxSearch.fromSearchView(searchView)
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter(item -> item.length() > 2)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(query -> {
                    if (!(query.trim().isEmpty())) {
                        getDoctorListAdapter().clearDoctorList();
                        loadFirstPage(query);
                    }
                }));
    }
}
