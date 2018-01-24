package com.nemanja.userapp.util;

import com.nemanja.userapp.data.model.Movie;

import java.util.List;


public interface OnCallbackMoviesRecieved {
    void returnList(List<Movie> list);
}
