package com.app.manchpostapp.service;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.app.manchpostapp.data.AppDatabase;
import com.app.manchpostapp.data.Post;
import com.app.manchpostapp.data.PostDao;

import java.util.List;

public class PostServiceImpl implements PostService {
    private PostDao postDao;

    public PostServiceImpl(Context context) {
        postDao = AppDatabase.getInstance(context).postDao();
    }

    @Override
    public LiveData<List<Post>> getMutablePostAll() {
        return postDao.getMutablePostAll();
    }

    @Override
    public void insertAll(Post... posts) {
        postDao.insertAll(posts);
    }
}
