package com.app.manchpostapp.service;

import com.app.manchpostapp.data.Post;

import java.util.List;

public interface PostService {
    List<Post> getAll();

    void insertAll(Post... users);
}
