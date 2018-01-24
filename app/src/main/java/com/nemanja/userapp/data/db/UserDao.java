package com.nemanja.userapp.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.nemanja.userapp.data.model.User;

import java.util.List;

/**
 * Created by Vladan on 12/9/2017.
 */
@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Delete
    void delete(User user);

    @Update
    void update(User user);

    @Query("select * from User")
    LiveData<List<User>> getUsers();

    @Query("select * from User where name like :inputName")
    LiveData<List<User>> searchUsers(String inputName);
}
