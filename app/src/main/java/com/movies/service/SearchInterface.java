package com.movies.service;

import com.movies.model.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface SearchInterface {

    @Headers({"X-Naver-Client-Id: apikey", "X-Naver-Client-Secret: apikey"})

    @GET("movie.json")
    Call<Movie> getMovies(@Query("query") String title,
                          @Query("display") int displaySize,
                          @Query("start") int startPosition);
}
