package com.vivy.docsearch.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Translation
 */
public class TranslationTest {
    private Translation translation;

    @Before
    public void setUp() {
        translation = new Translation();
    }

    @After
    public void tearDown() {
        translation = null;
    }

    @Test
    public void getterSetterConfidentialityKey() {
        translation.setConfidentialityKey("key");

        Assert.assertEquals("key", translation.getConfidentialityKey());
    }

    @Test
    public void getterSetterConsentKey() {
        translation.setConsentKey("key");

        Assert.assertEquals("key", translation.getConsentKey());
    }

    @Test
    public void getterSetterRequestSubmittedKey() {
        translation.setRequestSubmittedKey("key");

        Assert.assertEquals("key", translation.getRequestSubmittedKey());
    }
}