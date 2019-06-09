package com.app.manchpostapp.service;

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
    public List<Post> getAll() {
        return postDao.getAll();
    }

    @Override
    public void insertAll(Post... posts) {
        postDao.insertAll(posts);
    }
}
