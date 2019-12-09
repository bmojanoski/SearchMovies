package com.example.moviesapkfinal.api.MappingApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MovieResponse {

    @SerializedName("Title")
    @Expose
    private String Title;

    @SerializedName("Year")
    @Expose
    private String Year;

    @SerializedName("imdbID")
    @Expose
    private String imdbID;

    @SerializedName("Type")
    @Expose
    private String Type;

    @SerializedName("Poster")
    @Expose
    private String Poster;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }
}
