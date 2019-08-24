package com.vivy.docsearch.ui;


import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.vivy.docsearch.BR;
import com.vivy.docsearch.DoctorSearchApp;
import com.vivy.docsearch.R;
import com.vivy.docsearch.api.response.ErrorResponse;
import com.vivy.docsearch.databinding.FragmentDoctorListBinding;
import com.vivy.docsearch.ui.base.BaseFragment;
import com.vivy.docsearch.util.PaginationScrollListener;
import com.vivy.docsearch.viewmodel.DoctorListViewModel;

/**
 * Fragment to display list of doctor
 */
public class DoctorListFragment extends BaseFragment<DoctorListViewModel> {

    @Override
    protected Class<DoctorListViewModel> getViewModel() {
        return DoctorListViewModel.class;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel.serviceError.observe(this, this::onErrorResponse);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ((FragmentDoctorListBinding) dataBinding).doctorListView.setLayoutManager(
                new LinearLayoutManager(DoctorSearchApp.getAppContext()));
        ((FragmentDoctorListBinding) dataBinding).doctorListView.setAdapter(viewModel.getDoctorListAdapter());
        setSupportedActionBar(((FragmentDoctorListBinding) dataBinding).layoutToolbar.appBar);
        setSearchBar((FragmentDoctorListBinding) dataBinding, "Search Doctor Here");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        ((FragmentDoctorListBinding) dataBinding).doctorListView.setLayoutManager(layoutManager);
        viewModel.setPaginationScrollListener(new PaginationScrollListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                viewModel.getPaginationScrollListener().setLoading(true);
                viewModel.loadNextPage(null);
            }
        });
        ((FragmentDoctorListBinding) dataBinding).doctorListView.addOnScrollListener(
                viewModel.getPaginationScrollListener());
        viewModel.loadFirstPage(null);
        return dataBinding.getRoot();
    }

    public void setSearchBar(FragmentDoctorListBinding dataBinding, String searchString) {
        SearchView searchView = dataBinding.layoutToolbar.svItems;
        ImageView searchIcon = searchView.findViewById(com.google.android.material.R.id.search_button);
        searchIcon.setColorFilter(Color.WHITE);
        SearchView.SearchAutoComplete searchAutoComplete = searchView.findViewById(com.google.android.material.R.id.search_src_text);
        searchAutoComplete.setHint(searchString);
        searchAutoComplete.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        searchAutoComplete.setFilters(new InputFilter[]{new InputFilter.LengthFilter(256)});
        viewModel.initSearchViewWithDebounce(searchView);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_doctor_list;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (viewModel.getSearchViewDisposable() != null) {
            viewModel.getSearchViewDisposable().dispose();
        }
    }

    private void onErrorResponse(ErrorResponse serviceError) {
        showInfoDialog(serviceError.getMessage(), getString(R.string.error_service_response));
    }
}
