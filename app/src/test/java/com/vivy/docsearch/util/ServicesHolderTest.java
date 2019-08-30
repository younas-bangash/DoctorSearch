package com.vivy.docsearch.util;

import com.vivy.docsearch.api.ApiService;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Class to test ServicesHolder
 */
@RunWith(MockitoJUnitRunner.class)
public class ServicesHolderTest {
    @Mock
    public ApiService apiService;
    private ServicesHolder servicesHolder;

    @Before
    public void setUp() {
        servicesHolder = new ServicesHolder();
    }

    @After
    public void tearDown() {
        servicesHolder = null;
    }

    @Test
    public void getterSetterApiService() {
        servicesHolder.setApiService(apiService);

        Assert.assertNotNull(servicesHolder.getApiService());
    }
}