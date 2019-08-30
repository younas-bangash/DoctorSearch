package com.vivy.docsearch.di;

import com.vivy.docsearch.api.ApiConstants;
import com.vivy.docsearch.api.ApiService;
import com.vivy.docsearch.api.RequestInterceptor;
import com.vivy.docsearch.api.TokenAuthenticator;
import com.vivy.docsearch.util.ServicesHolder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The application module which provides app wide instances of various components
 */
@Module(includes = ViewModelModule.class)
public class AppModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(RequestInterceptor interceptor, ServicesHolder servicesHolder) {
        TokenAuthenticator authenticator = new TokenAuthenticator(servicesHolder);
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(ApiConstants.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.readTimeout(ApiConstants.READ_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.writeTimeout(ApiConstants.WRITE_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient.addInterceptor(interceptor);
        okHttpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        okHttpClient.authenticator(authenticator);
        return okHttpClient.build();
    }

    @Provides
    @Singleton
    ApiService provideRetrofit(OkHttpClient okHttpClient, ServicesHolder servicesHolder) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.SERVICES_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        servicesHolder.setApiService(apiService);
        return servicesHolder.getApiService();
    }

    @Provides
    @Singleton
    RequestInterceptor provideInterceptor() {
        return new RequestInterceptor();
    }
}
