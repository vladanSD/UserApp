package com.nemanja.userapp.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.nemanja.userapp.data.db.AppDatabase;
import com.nemanja.userapp.data.model.Movie;
import com.nemanja.userapp.data.model.MovieResponse;
import com.nemanja.userapp.data.model.User;
import com.nemanja.userapp.data.model.UserResponse;
import com.nemanja.userapp.data.service.MovieService;
import com.nemanja.userapp.data.service.UserService;
import com.nemanja.userapp.util.ApiFactory;
import com.nemanja.userapp.util.AppExecutors;
import com.nemanja.userapp.util.OnCallbackMoviesRecieved;
import com.nemanja.userapp.util.OnCallbackRecieved;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private static Repository instance;

    private final UserService service;
    private final MovieService movieService;
    private final AppDatabase appDatabase;
    private final AppExecutors appExecutors;

    private static List<User> list = new ArrayList<>();
    private static LiveData<List<User>> userList;

    private static List<Movie> movieList = new ArrayList<>();

    private Repository(UserService service, MovieService movieService, AppDatabase appDatabase, AppExecutors appExecutors) {
        this.service = service;
        this.movieService = movieService;
        this.appDatabase = appDatabase;
        this.appExecutors = appExecutors;
    }

    //singleton
    public static Repository getInstance(Context context) {
        if (instance == null) {
            instance = new Repository(ApiFactory.getService(), ApiFactory.getMovieService(), AppDatabase.getInstance(context), AppExecutors.getInstance());
        }
        return instance;
    }

    public void getUsersWithService(final OnCallbackRecieved listener) {
        if (list.isEmpty()) {
            Call<UserResponse> call = service.getRecipes();
            call.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if (response.isSuccessful()) {
                        List<User> listApi = response.body().getRecipes();
                        list = listApi;
                        listener.returnList(list);
                        Log.i("uspesno", "odradjen callback");
                    } else Log.i("greska", "neuspesno");
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Log.i("fail", t.getMessage());
                }
            });
        }
        listener.returnList(list);
    }

    public void addUserToDb(final User user) {
        appExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.getUserDao().insert(user);
                Log.i("dodat user", "uspesno");
            }
        });
    }

    public LiveData<List<User>> getUsers() {
        if (userList == null) {
            userList = new MutableLiveData<>();
            userList = appDatabase.getUserDao().getUsers();
        }
        return userList;
    }

    public LiveData<List<User>> searchUsers(String inputName) {
        return appDatabase.getUserDao().searchUsers(inputName);
    }

    public void deleteUser(final User user) {
        appExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                appDatabase.getUserDao().delete(user);
                Log.i("dodat user", "uspesno");
            }
        });
    }

    public void getMovies(final OnCallbackMoviesRecieved listener) {
        if (movieList.isEmpty()) {
            Call<MovieResponse> call = movieService.popularMovies();
            call.enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                    if (response.isSuccessful()) {
                        List<Movie> movies = response.body().getMovies();
                        movieList = movies;
                        listener.returnList(movieList);
                        Log.i("uspesno", "odradjen callback");
                    } else Log.i("greska", "neuspesno");
                }

                @Override
                public void onFailure(Call<MovieResponse> call, Throwable t) {
                    Log.i("fail", t.getMessage());
                }
            });
        }
        listener.returnList(movieList);
    }

}
