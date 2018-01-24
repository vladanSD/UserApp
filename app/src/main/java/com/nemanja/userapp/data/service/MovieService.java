package com.nemanja.userapp.data.service;

import com.nemanja.userapp.data.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface MovieService {

    @GET("movie/popular/")
    Call<MovieResponse> popularMovies();

    @GET("movie/top_rated/")
    @Headers("api_key: 1d963d7e34014ee2bafffa360b3a5398")
    Call<MovieResponse> topRatedMovies();
}
