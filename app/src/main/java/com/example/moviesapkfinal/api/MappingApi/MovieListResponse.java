package com.example.moviesapkfinal.api.MappingApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieListResponse {
    @SerializedName("Search")
    @Expose
    private List<MovieResponse> movieResponse;

    @SerializedName("totalResults")
    @Expose
    private String totalResults;

    @SerializedName("Response")
    @Expose
    private String Response;

    public List<MovieResponse> getMovieResponse() {
        return movieResponse;
    }

    public void setMovieResponse(List<MovieResponse> movieResponse) {
        this.movieResponse = movieResponse;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }
}
