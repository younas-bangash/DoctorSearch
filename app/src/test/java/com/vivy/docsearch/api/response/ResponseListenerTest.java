package com.vivy.docsearch.api.response;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * ResponseListener test class
 */
public class ResponseListenerTest {
    private ResponseListener<DoctorSearchResponse> responseListener;

    @After
    public void tearDown() {
        responseListener = null;
    }

    @Test
    public void onSuccess() {
         responseListener = new ResponseListener<DoctorSearchResponse>() {
            @Override
            public void onSuccess(DoctorSearchResponse data) {
                Assert.assertTrue(true);
            }

            @Override
            public void onFailure(ErrorResponse errorResponse) {
                fail();
            }
        };

        responseListener.onSuccess(new DoctorSearchResponse());
    }

    @Test
    public void onFailure() {
         responseListener = new ResponseListener<DoctorSearchResponse>() {
            @Override
            public void onSuccess(DoctorSearchResponse data) {
                fail();
            }

            @Override
            public void onFailure(ErrorResponse errorResponse) {
                Assert.assertTrue(true);
            }
        };

        responseListener.onFailure(new ErrorResponse());
    }
}