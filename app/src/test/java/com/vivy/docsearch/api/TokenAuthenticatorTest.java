package com.vivy.docsearch.api;

import com.vivy.docsearch.api.response.OauthTokenResponse;
import com.vivy.docsearch.util.ServicesHolder;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;

import static org.mockito.ArgumentMatchers.any;

/**
 * Test class for TokenAuthenticator
 */
@RunWith(MockitoJUnitRunner.class)
public class TokenAuthenticatorTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    @Mock
    public ApiService apiService;
    @Mock
    public Call<OauthTokenResponse> oauthTokenResponseCall;

    private retrofit2.Response<OauthTokenResponse> oauthTokenResponseResponse;
    private ServicesHolder servicesHolder;
    private OauthTokenResponse oauthTokenResponse;
    private TokenAuthenticator tokenAuthenticator;
    private RequestInterceptor requestInterceptor;
    private ResponseBody responseBody;

    @Before
    public void setUp() {
        requestInterceptor = new RequestInterceptor();
        oauthTokenResponse = new OauthTokenResponse();
        requestInterceptor.setInterceptor(ApiConstants.AUTH_URL);
        servicesHolder = new ServicesHolder();
        servicesHolder.setApiService(apiService);
        servicesHolder.setRequestInterceptor(requestInterceptor);
        tokenAuthenticator = new TokenAuthenticator(servicesHolder);
        responseBody = ResponseBody.create(MediaType.parse("text/plain; charset=utf-8"), "ABC");
        oauthTokenResponseResponse = retrofit2.Response.success(oauthTokenResponse,
                getResponse(responseBody, HttpURLConnection.HTTP_OK));
    }

    @After
    public void tearDown() {
        servicesHolder = null;
        tokenAuthenticator = null;
        requestInterceptor = null;
        responseBody = null;
        oauthTokenResponse = null;
    }

    @Test
    public void authenticate() throws IOException {

        Mockito.when(servicesHolder.getApiService().refreshToken(any(), any())).thenReturn(oauthTokenResponseCall);

        Request newRequest = tokenAuthenticator.authenticate(null, getResponse(responseBody,
                HttpURLConnection.HTTP_UNAUTHORIZED));

        Assert.assertNull(newRequest);

        Response response = new Response.Builder()
                .request(getRequest())
                .protocol(Protocol.HTTP_1_1)
                .code(HttpURLConnection.HTTP_OK)
                .message("Intercepted!")
                .body(responseBody)
                .priorResponse(getResponse(null, HttpURLConnection.HTTP_UNAUTHORIZED))
                .build();

        newRequest = tokenAuthenticator.authenticate(null, response);

        Assert.assertNull(newRequest);

        Mockito.when(oauthTokenResponseCall.execute()).thenReturn(oauthTokenResponseResponse);

        newRequest = tokenAuthenticator.authenticate(null, getResponse(responseBody, HttpURLConnection.HTTP_UNAUTHORIZED));

        Assert.assertNotNull(newRequest);

    }

    private Request getRequest() {
        return new Request.Builder()
                .url(ApiConstants.AUTH_URL)
                .build();
    }

    private Response getResponse(ResponseBody responseBody, int code) {
        return new Response.Builder()
                .request(getRequest())
                .protocol(Protocol.HTTP_1_1)
                .code(code)
                .message("Intercepted!")
                .body(responseBody)
                .build();
    }
}