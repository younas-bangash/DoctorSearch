package com.vivy.docsearch.api;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.stream.MalformedJsonException;
import com.vivy.docsearch.api.response.ErrorResponse;
import com.vivy.docsearch.api.response.ResponseListener;

import java.io.IOException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

/**
 * This class fetch response from the service always
 */

public abstract class ServiceRequest<V> {

    @NonNull
    @MainThread
    protected abstract Call<V> createCall();

    @MainThread
    protected ServiceRequest(ResponseListener<V> responseListener) {
        createCall().enqueue(new Callback<V>() {
            @Override
            public void onResponse(@NonNull Call<V> call, @NonNull Response<V> response) {
                if (response.isSuccessful()) {
                    responseListener.onSuccess(response.body());
                } else {
                    handleServiceError(response, responseListener);
                }
            }

            @Override
            public void onFailure(@NonNull Call<V> call, @NonNull Throwable t) {
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setMessage(getCustomErrorMessage(t));
                responseListener.onFailure(errorResponse);
            }
        });
    }

    private void handleServiceError(@NonNull Response<V> response, ResponseListener<V> listener) {
        ErrorResponse errorResponse;
        try {
            errorResponse = new Gson().fromJson(response.errorBody().charStream(), ErrorResponse.class);
        } catch (Exception ex) {
            errorResponse = new ErrorResponse();
            errorResponse.setMessage(getCustomErrorMessage(ex));
        }
        listener.onFailure(errorResponse);
    }

    private String getCustomErrorMessage(Throwable error) {
        if (error instanceof SocketTimeoutException) {
            return "SocketTimeoutException";
        } else if (error instanceof MalformedJsonException) {
            return "MalformedJsonException";
        } else if (error instanceof IOException) {
            return "IOException";
        } else if (error instanceof HttpException) {
            return (((HttpException) error).response().message());
        } else {
            return "unknownError";
        }
    }
}
