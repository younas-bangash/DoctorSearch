package com.vivy.docsearch.api.response;

import androidx.annotation.NonNull;

import com.vivy.docsearch.model.Doctor;

import java.util.ArrayList;
import java.util.List;

/**
 * Response class for searching the doctor
 */
public class DoctorSearchResponse {
    private List<Doctor> doctors = new ArrayList<>();
    private String lastKey;

    @NonNull
    public List<Doctor> getDoctors() {
        return doctors != null ? doctors : new ArrayList<>();
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public String getLastKey() {
        return lastKey;
    }

    public void setLastKey(String lastKey) {
        this.lastKey = lastKey;
    }
}
