package com.example.zanimo11.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;


import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zanimo11.Models.user;
import com.example.zanimo11.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class userPostAdapter extends RecyclerView.Adapter<userPostAdapter.userPostViewHolder> {
    public static Context context;
    List<user> listeUser;



    public userPostAdapter(Context context, List<user> listeUser) {
        this.context = context;
        this.listeUser = listeUser;
    }

    @NonNull
    @Override
    public userPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_list_item, parent, false);
        userPostViewHolder userPostViewHolder = new userPostViewHolder(view);
        return userPostViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull userPostViewHolder holder, int position) {

        user user = listeUser.get(position);
        if (user.getPosts().size()==0){
            holder.linearLayout.setVisibility(View.INVISIBLE);
            holder.linearLayout.setLayoutParams(new LinearLayout.LayoutParams(0,0));
        }else {
            holder.name.setText(user.getNom());
            holder.prenom.setText(user.getPrenom());
            Glide.with(context).load(user.getImageuser()).into(holder.imageUser);
            postAapter postAapter = new postAapter(context, user.getPosts(), user);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            holder.recyclerViewPost.setLayoutManager(layoutManager);
            holder.recyclerViewPost.setAdapter(postAapter);
        }


    }

    private Context getContext() {
        return this.context;
    }

    @Override
    public int getItemCount() {
        return this.listeUser.size();
    }

    public static class userPostViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        TextView name, prenom;

        CircleImageView imageUser;

        RecyclerView recyclerViewPost;

        public userPostViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            prenom= (TextView) itemView.findViewById(R.id.prenom);
            imageUser = (CircleImageView) itemView.findViewById(R.id.imageUser);
            recyclerViewPost = (RecyclerView) itemView.findViewById(R.id.recyclerviewpost);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.listUser);
        }
    }
}
