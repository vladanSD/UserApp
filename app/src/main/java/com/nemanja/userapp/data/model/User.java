package com.nemanja.userapp.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Vladan on 12/9/2017.
 */
@Entity
public class User {

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("number")
    @Expose
    private int number;
    @SerializedName("name")
    @Expose
    private String name;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
