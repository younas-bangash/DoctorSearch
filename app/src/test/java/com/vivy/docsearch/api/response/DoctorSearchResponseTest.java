package com.vivy.docsearch.api.response;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Unit tests for DoctorSearchResponse
 */
public class DoctorSearchResponseTest {
    private DoctorSearchResponse doctorSearchResponse;

    @Before
    public void setUp() {
        doctorSearchResponse = new DoctorSearchResponse();
    }

    @After
    public void tearDown() {
        doctorSearchResponse = null;
    }

    @Test
    public void doctorsGetterSetter() {
        doctorSearchResponse.setDoctors(new ArrayList<>());

        Assert.assertEquals(0, doctorSearchResponse.getDoctors().size());

        doctorSearchResponse.setDoctors(null);

        Assert.assertEquals(0, doctorSearchResponse.getDoctors().size());
    }

    @Test
    public void lastKeyGetterSetter() {
        doctorSearchResponse.setLastKey(null);

        Assert.assertNull(doctorSearchResponse.getLastKey());

        doctorSearchResponse.setLastKey("48320JFLJFALLJJ8409905");

        Assert.assertEquals("48320JFLJFALLJJ8409905", doctorSearchResponse.getLastKey());
    }
}