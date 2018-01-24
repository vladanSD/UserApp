package com.nemanja.userapp.util;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nemanja.userapp.BuildConfig;
import com.nemanja.userapp.data.service.MovieService;
import com.nemanja.userapp.data.service.UserService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public final class ApiFactory {

    private static volatile OkHttpClient client;
    private static volatile UserService service;
    private static volatile MovieService movieService;

    public static UserService getService(){
        UserService service = ApiFactory.service;
            if(service == null){
                synchronized (ApiFactory.class){
                    service = ApiFactory.service;
                    if(service == null){
                        service = ApiFactory.service = createService();
                    }
                }
            }
            return service;
    }

    public static MovieService getMovieService(){
        MovieService mService = ApiFactory.movieService;
        if(mService == null){
            synchronized (ApiFactory.class){
                mService = ApiFactory.movieService;
                if(mService == null){
                    mService = ApiFactory.movieService = createMovieService();
                }
            }
        }
        return mService;
    }

    private static UserService createService(){
        return new Retrofit.Builder()
                .baseUrl("http://192.168.0.16://ovde/")
//                .baseUrl("http://10.0.2.2://ovde/")
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create(getCustomGson()))
                .build()
                .create(UserService.class);
    }

    private static MovieService createMovieService(){
        return new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/3/")
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create(getCustomGson()))
                .build()
                .create(MovieService.class);
    }

    private static OkHttpClient getClient(){
        OkHttpClient client = ApiFactory.client;
        if(client == null){
            synchronized (ApiFactory.class){
                client = ApiFactory.client;
                if(client == null){
                    client = ApiFactory.client = buildClient();
                }
            }
        }
        return client;
    }

    private static OkHttpClient buildClient(){
        return new OkHttpClient.Builder()
                .addInterceptor(new ApiKeyInterceptor())
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    private static Gson getCustomGson(){
        return new GsonBuilder()
                .setLenient()
                .create();

    }


}
