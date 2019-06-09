package com.app.manchpostapp;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.manchpostapp.adapters.PostListAdapter;
import com.app.manchpostapp.data.Post;
import com.app.manchpostapp.service.PostServiceImpl;
import com.app.manchpostapp.utilities.HelperMethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab_add_post;
    RecyclerView post_list;
    private Context context;
    public List<Post> postList = new ArrayList<>();
    private PostServiceImpl postService;
    private PostListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        initializeListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        HelperMethod.getUsersFromDB(postService, new Callback() {
            @Override
            public void onFetchSuccessfully(List<Post> posts) {
                Collections.reverse(posts);
                postList = posts;
                adapter.setPosts(posts);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initialize(){
        context = this;
        postService = new PostServiceImpl(context);
        adapter = new PostListAdapter();
        fab_add_post = findViewById(R.id.fab_add_post);
        post_list = findViewById(R.id.post_list);
        post_list.setLayoutManager(new LinearLayoutManager(context));
        post_list.setAdapter(adapter);
    }
    private void initializeListener(){
        fab_add_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(AddNewPostActivity.getIntent(getApplicationContext(), postList.size()));
            }
        });
    }

    public interface Callback{
        void onFetchSuccessfully(List<Post> posts);
    }
}
