package com.vivy.docsearch.ui;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vivy.docsearch.databinding.DoctorListItemBinding;
import com.vivy.docsearch.databinding.ItemDataLoadingBinding;
import com.vivy.docsearch.model.Doctor;

import java.util.List;

/**
 * List adapter for doctor
 */
public class DoctorListAdapter extends RecyclerView.Adapter {
    private static final int TYPE_PROGRESS = 0;
    private static final int TYPE_ITEM = 1;
    private List<Doctor> doctorList;
    private boolean isLoadingAdded = false;

    public DoctorListAdapter(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == doctorList.size() - 1 && isLoadingAdded) ? TYPE_PROGRESS : TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_PROGRESS) {
            ItemDataLoadingBinding dataLoadingBinding = ItemDataLoadingBinding.inflate(layoutInflater,
                    parent, false);
            return new DataLoadingItemViewHolder(dataLoadingBinding);

        } else {
            DoctorListItemBinding itemBinding = DoctorListItemBinding.inflate(layoutInflater, parent,
                    false);
            return new DoctorItemViewHolder(itemBinding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DoctorItemViewHolder) {
            ((DoctorItemViewHolder) holder).bindView(doctorList.get(position));
        }
    }

    public class DataLoadingItemViewHolder extends RecyclerView.ViewHolder {
        public DataLoadingItemViewHolder(ItemDataLoadingBinding binding) {
            super(binding.getRoot());
        }
    }

    public class DoctorItemViewHolder extends RecyclerView.ViewHolder {
        private DoctorListItemBinding binding;

        public DoctorItemViewHolder(DoctorListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindView(Doctor item) {
            if (null != item && !TextUtils.isEmpty(item.getId()))
                binding.setItem(item);
        }
    }

    public void clearDoctorList() {
        isLoadingAdded = false;
        this.doctorList.clear();
        notifyDataSetChanged();
    }

    public void updateList(List<Doctor> doctorList) {
        this.doctorList.addAll(doctorList);
        notifyDataSetChanged();
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        doctorList.add(new Doctor());
        notifyItemInserted(doctorList.size() - 1);
        notifyDataSetChanged();
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;

        if (doctorList == null || doctorList.isEmpty()) {
            return;
        }

        int position = doctorList.size() - 1;
        Doctor result = doctorList.get(position);
        if (result != null) {
            doctorList.remove(position);
            notifyItemRemoved(position);
        }
        notifyDataSetChanged();
    }
}
