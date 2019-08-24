package com.vivy.docsearch.api;

import com.vivy.docsearch.api.response.DoctorSearchResponse;
import com.vivy.docsearch.api.response.OauthTokenResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * The APIService interface which will contain the semantics of all the REST calls.
 */
public interface ApiService {
    @GET("/api/users/me/doctors")
    Call<DoctorSearchResponse> searchDoctor(@Query("lastKey") String lastKey,
                                            @Query("search") String search,
                                            @Query("lat") String lat,
                                            @Query("lng") String lng);

    @Headers({"Content-Type: application/x-www-form-urlencoded",
            "Authorization: Basic aXBob25lOmlwaG9uZXdpbGxub3RiZXRoZXJlYW55bW9yZQ=="})
    @FormUrlEncoded
    @POST("/oauth/token?grant_type=password")
    Call<OauthTokenResponse> refreshToken(@Field("username") String username,
                                          @Field("password") String password);
}
