package com.vivy.docsearch.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vivy.docsearch.api.ApiConstants;
import com.vivy.docsearch.api.ServiceRequest;
import com.vivy.docsearch.api.response.ErrorResponse;
import com.vivy.docsearch.api.response.ResponseListener;
import com.vivy.docsearch.databinding.DoctorListItemBinding;
import com.vivy.docsearch.databinding.ItemDataLoadingBinding;
import com.vivy.docsearch.model.Doctor;
import com.vivy.docsearch.util.ServicesHolder;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * List adapter for doctor
 */
public class DoctorListAdapter extends RecyclerView.Adapter {
    private static final int TYPE_PROGRESS = 0;
    private static final int TYPE_ITEM = 1;
    private List<Doctor> doctorList;
    private boolean isLoadingAdded = false;
    private ServicesHolder servicesHolder;

    public DoctorListAdapter(List<Doctor> doctorList, ServicesHolder servicesHolder) {
        this.doctorList = doctorList;
        this.servicesHolder = servicesHolder;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == doctorList.size() - 1 && isLoadingAdded) ? TYPE_PROGRESS : TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
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
            DoctorItemViewHolder doctorItemViewHolder = ((DoctorItemViewHolder) holder);
            doctorItemViewHolder.imageView.setTag(position);
            Doctor doctor = doctorList.get(position);
            if(!doctor.isImageLoaded()){
                getDoctorPicture(doctor, doctorItemViewHolder, position);
            }
            doctorItemViewHolder.bindView(doctor);
        }
    }

    public void getDoctorPicture(Doctor doctor, DoctorItemViewHolder itemViewHolder, int pos) {

        new ServiceRequest<ResponseBody>(new ResponseListener<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                if (null != responseBody && null != responseBody.byteStream()) {
                    doctor.setImageLoaded(true);
                    Bitmap bmp = BitmapFactory.decodeStream(responseBody.byteStream());
                    int imageViewTag = (int) itemViewHolder.imageView.getTag();
                    if(pos == imageViewTag){
                        itemViewHolder.imageView.setBackgroundResource(0);
                        itemViewHolder.imageView.setImageBitmap(bmp);
                    }
                }
            }

            @Override
            public void onFailure(ErrorResponse errorResponse) {
                doctor.setImageLoaded(false);
            }
        }) {
            @NonNull
            @Override
            protected Call<ResponseBody> createCall() {
                return servicesHolder.getApiService().getDoctorPicture(ApiConstants.SERVICES_BASE_URL + "api/doctors/"
                        + doctor.getId() + "/keys/profilepictures", doctor.getName());
            }
        };
    }

    public class DataLoadingItemViewHolder extends RecyclerView.ViewHolder {
        public DataLoadingItemViewHolder(ItemDataLoadingBinding binding) {
            super(binding.getRoot());
        }
    }

    public class DoctorItemViewHolder extends RecyclerView.ViewHolder {
        private DoctorListItemBinding binding;
        public ImageView imageView;
        public DoctorItemViewHolder(DoctorListItemBinding binding) {
            super(binding.getRoot());
            this.imageView = binding.placeImage;
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
