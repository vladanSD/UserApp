package com.nemanja.userapp.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.nemanja.userapp.data.model.User;

/**
 * Created by Vladan on 12/9/2017.
 */

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context){
        if(INSTANCE == null) INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "recipes_db")
                .build();
        return INSTANCE;
    }

    public abstract UserDao getUserDao();
}
