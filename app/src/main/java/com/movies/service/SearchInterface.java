package com.movies.service;

import com.movies.model.Movie;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface SearchInterface {

    @Headers({"X-Naver-Client-Id: DHGDFgZOh27LKwu6keOU", "X-Naver-Client-Secret: 1VA5Yb_D_g"})

    @GET("movie.json")
    Call<Movie> getMovies(@Query("query") String title,
                          @Query("display") int displaySize,
                          @Query("start") int startPosition);
}
