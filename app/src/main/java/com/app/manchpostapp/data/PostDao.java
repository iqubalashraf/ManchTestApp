package com.app.manchpostapp.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PostDao {

    @Query("SELECT * FROM post")
    List<Post> getAll();

    @Query("SELECT * FROM post")
    LiveData<List<Post>> getMutablePostAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Post... posts);
}
