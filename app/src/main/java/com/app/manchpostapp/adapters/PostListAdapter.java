package com.app.manchpostapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.manchpostapp.R;
import com.app.manchpostapp.data.Post;

import java.util.List;

public class PostListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Post> posts;

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return  new ItemViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_main_holder, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder){
            ((ItemViewHolder) holder).tv_title.setText(posts.get(position).getTitle());
            ((ItemViewHolder) holder).tv_description.setText(posts.get(position).getDescription());
        }
    }

    @Override
    public int getItemCount() {
        if(posts == null)
            return 0;
        return posts.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView tv_title, tv_description;

        ItemViewHolder(View itemView) {
            super(itemView);
            tv_description = itemView.findViewById(R.id.tv_description);
            tv_title = itemView.findViewById(R.id.tv_title);
        }
    }

}
