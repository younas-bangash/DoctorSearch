package com.vivy.docsearch.api.response;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ErrorResponse
 */
public class ErrorResponseTest {
    private ErrorResponse errorResponse;

    @Before
    public void setUp() {
        errorResponse = new ErrorResponse();
    }

    @After
    public void tearDown() {
        errorResponse = null;
    }

    @Test
    public void timestampGetterSetter() {
        errorResponse.setTimestamp("25/8/2019 34:54:65");

        Assert.assertEquals("25/8/2019 34:54:65", errorResponse.getTimestamp());
    }

    @Test
    public void statusGetterSetter() {
        errorResponse.setStatus(201);

        Assert.assertEquals(201, errorResponse.getStatus());
    }

    @Test
    public void messageGetterSetter() {
        errorResponse.setMessage("Unknown Error");

        Assert.assertEquals("Unknown Error", errorResponse.getMessage());
    }

    @Test
    public void errorGetterSetter() {
        errorResponse.setError("Message Not Found");

        Assert.assertEquals("Message Not Found", errorResponse.getError());
    }
}