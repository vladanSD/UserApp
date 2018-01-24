package com.nemanja.userapp.data.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;


@Entity
public class Movie {

    @PrimaryKey
    @SerializedName("id")
    private int id;

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    private String posterPath;

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    private String overview;

    @ColumnInfo(name = "original_title")
    @SerializedName("original_title")
    private String title;

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    private String releasedDate;

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    private double voteAverage;

    public Movie() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(@NonNull String posterPath) {
        this.posterPath = posterPath;
    }

    @NonNull
    public String getOverview() {
        return overview;
    }

    public void setOverview(@NonNull String overview) {
        this.overview = overview;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    @NonNull
    public String getReleasedDate() {
        return releasedDate;
    }

    public void setReleasedDate(@NonNull String releasedDate) {
        this.releasedDate = releasedDate;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }
}
