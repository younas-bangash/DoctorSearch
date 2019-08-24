package com.vivy.docsearch.api;

import androidx.annotation.NonNull;

import com.vivy.docsearch.api.response.OauthTokenResponse;
import com.vivy.docsearch.util.ServicesHolder;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Call;

/**
 * TokenAuthenticator class for services
 */
public class TokenAuthenticator implements Authenticator {
    private ServicesHolder servicesHolder;

    public TokenAuthenticator(ServicesHolder servicesHolder) {
        this.servicesHolder = servicesHolder;
    }

    @Override
    public synchronized Request authenticate(@NonNull Route route, @NonNull Response response) throws IOException {
        setAuthRequestUrl();
        if (responseCount(response) >= 2) {
            // If both the original call and the call with refreshed token failed,
            // it will probably keep failing, so don't try again.
            return null;
        }
        if (response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
            String newAccessToken;
            setAuthRequestUrl();
            Call<OauthTokenResponse> tokenRefreshCall = servicesHolder.getApiService().refreshToken("androidChallenge@vivy.com", "88888888");
            retrofit2.Response<OauthTokenResponse> oauthTokenResponse = tokenRefreshCall.execute();
            if (oauthTokenResponse != null && oauthTokenResponse.isSuccessful()) {
                newAccessToken = oauthTokenResponse.body().getAccessToken();
                setServicesBaseUrl();
                return response.request()
                        .newBuilder()
                        .header("Authorization", "Bearer " + newAccessToken)
                        .build();
            }
        }
        setServicesBaseUrl();
        return null;
    }

    private void setAuthRequestUrl() {
        servicesHolder.getRequestInterceptor().setInterceptor(ApiConstants.AUTH_URL);
    }

    private void setServicesBaseUrl() {
        servicesHolder.getRequestInterceptor().setInterceptor(ApiConstants.SERVICES_BASE_URL);
    }

    private int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) {
            result++;
        }
        return result;
    }
}
