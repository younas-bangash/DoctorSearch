package com.vivy.docsearch.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Test class for the Doctor data model
 */
public class DoctorTest {
    private Doctor doctor;

    @Before
    public void setUp() {
        doctor = new Doctor();
    }

    @After
    public void tearDown() {
        doctor = null;
    }

    @Test
    public void getterSetterAddress() {
        doctor.setAddress("address");

        Assert.assertEquals("address", doctor.getAddress());
    }

    @Test
    public void getterSetterName() {
        doctor.setName("name");

        Assert.assertEquals("name", doctor.getName());
    }

    @Test
    public void getterSetterEmail() {
        doctor.setEmail("email");

        Assert.assertEquals("email", doctor.getEmail());
    }

    @Test
    public void getterSetterHighlighted() {
        doctor.setHighlighted(true);

        Assert.assertTrue(doctor.getHighlighted());

        doctor.setHighlighted(false);

        Assert.assertFalse(doctor.getHighlighted());
    }

    @Test
    public void getterSetterId() {
        doctor.setId("id");

        Assert.assertEquals("id", doctor.getId());
    }

    @Test
    public void getterSetterIntegration() {
        doctor.setIntegration("integration");

        Assert.assertEquals("integration", doctor.getIntegration());
    }

    @Test
    public void getterSetterLat() {
        doctor.setLat(120.0);

        Assert.assertEquals(120, doctor.getLat(), 0);
    }

    @Test
    public void getterSetterLng() {
        doctor.setLng(120.0);

        Assert.assertEquals(120, doctor.getLng(), 0);
    }

    @Test
    public void getterSetterPhoneNumber() {
        doctor.setPhoneNumber("phoneNumber");

        Assert.assertEquals("phoneNumber", doctor.getPhoneNumber());
    }

    @Test
    public void getterSetterPhotoId() {
        doctor.setPhotoId("photoId");

        Assert.assertEquals("photoId", doctor.getPhotoId());
    }

    @Test
    public void getterSetterRating() {
        doctor.setRating(10.0);

        Assert.assertEquals(10, doctor.getRating(), 0);
    }

    @Test
    public void getterSetterReviewCount() {
        doctor.setReviewCount(10);

        Assert.assertEquals(10, doctor.getReviewCount());
    }

    @Test
    public void getterSetterSource() {
        doctor.setSource("source");

        Assert.assertEquals("source", doctor.getSource());
    }

    @Test
    public void getterSetterSpecialityIds() {
        doctor.setSpecialityIds(new ArrayList<>());

        Assert.assertEquals(0, doctor.getSpecialityIds().size());
    }

    @Test
    public void getterSetterTranslation() {
        doctor.setTranslation(new Translation());

        Assert.assertNotNull(doctor.getTranslation());
    }

    @Test
    public void getterSetterWebsite() {
        doctor.setWebsite("website");

        Assert.assertEquals("website", doctor.getWebsite());
    }
}