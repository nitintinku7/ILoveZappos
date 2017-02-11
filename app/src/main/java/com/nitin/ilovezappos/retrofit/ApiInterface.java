package com.nitin.ilovezappos.retrofit;

import com.nitin.ilovezappos.pojo.ProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Nitin on 06-02-2017.
 */

public interface ApiInterface {
    @GET("Search")
    Call<ProductResponse> getProducts(@Query("term") String term, @Query("key") String key);
}
