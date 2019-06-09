package com.app.manchpostapp.utilities;

import android.os.AsyncTask;

import com.app.manchpostapp.MainActivity;
import com.app.manchpostapp.data.Post;
import com.app.manchpostapp.service.PostServiceImpl;

import java.util.List;

public class HelperMethod {

    public static void getUsersFromDB(final PostServiceImpl postService, final MainActivity.Callback callback) {
        new AsyncTask<Void, Void, Void>() {
            List<Post> posts;
            @Override
            protected Void doInBackground(Void... params) {
                posts = postService.getAll();
                return null;
            }

            @Override
            protected void onPostExecute(Void agentsCount) {
                callback.onFetchSuccessfully(posts);
            }
        }.execute();
    }

}
