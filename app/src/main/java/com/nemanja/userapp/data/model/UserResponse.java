package com.nemanja.userapp.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladan on 12/9/2017.
 */

public class UserResponse {

    @SerializedName("users")
    private List<User> users;

    public List<User> getRecipes(){
        if(users == null){
            return new ArrayList<>();
        }
        return users;
    }
}
