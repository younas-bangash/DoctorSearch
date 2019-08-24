package com.vivy.docsearch.api.response;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * test class for OauthTokenResponse
 */
public class OauthTokenResponseTest {
    private OauthTokenResponse oauthTokenResponse;

    @Before
    public void setUp() {
        oauthTokenResponse = new OauthTokenResponse();
    }

    @After
    public void tearDown() {
        oauthTokenResponse = null;
    }

    @Test
    public void accessTokenGetterSetter() {
        oauthTokenResponse.setAccessToken("accessToken");

        Assert.assertEquals("accessToken", oauthTokenResponse.getAccessToken());
    }

    @Test
    public void tokenTypeGetterSetter() {
        oauthTokenResponse.setTokenType("tokenType");

        Assert.assertEquals("tokenType", oauthTokenResponse.getTokenType());
    }

    @Test
    public void refreshTokenGetterSetter() {
        oauthTokenResponse.setRefreshToken("refreshToken");

        Assert.assertEquals("refreshToken", oauthTokenResponse.getRefreshToken());
    }

    @Test
    public void expiresInGetterSetter() {
        oauthTokenResponse.setExpiresIn(15);

        Assert.assertEquals(15, oauthTokenResponse.getExpiresIn());
    }

    @Test
    public void scopeGetterSetter() {
        oauthTokenResponse.setScope("scope");

        Assert.assertEquals("scope", oauthTokenResponse.getScope());
    }

    @Test
    public void jtiGetterSetter() {
        oauthTokenResponse.setJti("jti");

        Assert.assertEquals("jti", oauthTokenResponse.getJti());
    }

    @Test
    public void phoneVerifiedGetterSetter() {
        oauthTokenResponse.setPhoneVerified(false);

        Assert.assertFalse(oauthTokenResponse.getPhoneVerified());

        oauthTokenResponse.setPhoneVerified(true);

        Assert.assertTrue(oauthTokenResponse.getPhoneVerified());
    }
}