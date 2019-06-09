package com.app.manchpostapp.service;

import android.arch.lifecycle.LiveData;

import com.app.manchpostapp.data.Post;

import java.util.List;

public interface PostService {
    LiveData<List<Post>> getMutablePostAll();

    void insertAll(Post... users);
}
