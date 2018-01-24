package com.nemanja.userapp.data.service;

import com.nemanja.userapp.data.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {
    @GET("userService.txt")
    Call<UserResponse> getRecipes();
}
