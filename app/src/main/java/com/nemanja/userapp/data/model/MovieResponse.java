package com.nemanja.userapp.data.model;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MovieResponse {

    @SerializedName("results")
    private List<Movie> movies;


    public List<Movie> getMovies() {
        if(movies == null){
            return new ArrayList<>();
        }
        return movies;
    }
}
