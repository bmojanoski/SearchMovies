package com.example.moviesapkfinal.api;

import com.example.moviesapkfinal.api.MappingApi.MovieListResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("/")
    Call<MovieListResponse> getMovie(
            @Query("apikey") String id,
            @Query("plot") String plot,
            @Query("s") String s
    );

}
