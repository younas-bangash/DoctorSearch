package com.vivy.docsearch.api;

import com.vivy.docsearch.api.response.DoctorSearchResponse;
import com.vivy.docsearch.api.response.OauthTokenResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * The APIService interface which will contain the semantics of all the REST calls.
 */
public interface ApiService {
    @GET
    Call<DoctorSearchResponse> searchDoctor(@Url String baseUrl,
                                            @Query("lastKey") String lastKey,
                                            @Query("search") String search,
                                            @Query("lat") String lat,
                                            @Query("lng") String lng);

    @Headers({"Content-Type: application/x-www-form-urlencoded",
            "Authorization: Basic aXBob25lOmlwaG9uZXdpbGxub3RiZXRoZXJlYW55bW9yZQ=="})
    @FormUrlEncoded
    @POST
    Call<OauthTokenResponse> refreshToken(@Url String baseUrl,
                                          @Field("username") String username,
                                          @Field("password") String password);

    @GET
    Call<ResponseBody> getDoctorPicture(@Url String baseUrl, @Query("name") String docName);
}
