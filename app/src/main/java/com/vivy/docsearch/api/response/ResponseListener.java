package com.vivy.docsearch.api.response;

/**
 * ResponseListner for the services
 */
public interface ResponseListener<T> {
    void onSuccess(T data);

    void onFailure(ErrorResponse errorResponse);
}
