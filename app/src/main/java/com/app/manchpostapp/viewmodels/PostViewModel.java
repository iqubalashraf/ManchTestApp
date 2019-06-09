package com.app.manchpostapp.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.app.manchpostapp.MyApplication;
import com.app.manchpostapp.data.Post;
import com.app.manchpostapp.service.PostServiceImpl;

import java.util.List;

public class PostViewModel extends ViewModel {
    private LiveData<List<Post>> posts;

    private PostServiceImpl postService;

    public PostViewModel() {
        postService = new PostServiceImpl(MyApplication.getContext());
    }


    public LiveData<List<Post>> getUsers() {
        if (posts == null) {
            posts = new MutableLiveData<List<Post>>();
            loadUsers();
        }
        return posts;
    }

    private void loadUsers() {
        posts = postService.getMutablePostAll();
    }
}

