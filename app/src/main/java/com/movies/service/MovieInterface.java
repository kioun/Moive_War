package com.movies.service;

import com.movies.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieInterface {

    @GET("/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?")
    Call<Result> getBoxOffice(@Query("key") String key,
                              @Query("targetDt") String tartgetDt);
}
