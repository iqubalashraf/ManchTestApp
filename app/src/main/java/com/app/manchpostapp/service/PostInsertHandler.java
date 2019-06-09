package com.app.manchpostapp.service;

import android.os.AsyncTask;
import android.util.Log;

import com.app.manchpostapp.data.Post;

public class PostInsertHandler extends AsyncTask<Void, Void, Boolean> {

    private int uid;
    private String title;
    private String description;
    private PostServiceImpl postService;
    private Callback callback;

    public PostInsertHandler(int uid, String title, String description,
                             PostServiceImpl postService, Callback callback) {
        this.uid = uid;
        this.title = title;
        this.description = description;
        this.postService = postService;
        this.callback = callback;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        postService.insertAll(new Post(uid, title, description));
        return true;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        callback.onInsertedSuccessfully();
    }

    public interface Callback{
        void onInsertedSuccessfully();
    }
}
