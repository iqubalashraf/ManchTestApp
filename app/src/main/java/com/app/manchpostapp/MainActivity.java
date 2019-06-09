package com.app.manchpostapp;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.manchpostapp.adapters.PostListAdapter;
import com.app.manchpostapp.data.Post;
import com.app.manchpostapp.viewmodels.PostViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public List<Post> postList = new ArrayList<>();
    FloatingActionButton fab_add_post;
    RecyclerView post_list;
    private PostListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        initializeListener();
        PostViewModel model = ViewModelProviders.of(this).get(PostViewModel.class);
        model.getPosts().observe(this, posts -> {
            if (posts != null) {
                Collections.reverse(posts);
                postList = posts;
                adapter.setPosts(posts);
                adapter.notifyDataSetChanged();
            }
        });


    }

    private void initialize() {
        adapter = new PostListAdapter();
        fab_add_post = findViewById(R.id.fab_add_post);
        post_list = findViewById(R.id.post_list);
        post_list.setLayoutManager(new LinearLayoutManager(this));
        post_list.setAdapter(adapter);
    }

    private void initializeListener() {
        fab_add_post.setOnClickListener(v -> startActivity(
                AddNewPostActivity.getIntent(getApplicationContext(), postList.size())));
    }

}
