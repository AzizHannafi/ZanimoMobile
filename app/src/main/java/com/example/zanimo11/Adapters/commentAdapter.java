package com.example.zanimo11.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zanimo11.Models.Comments;
import com.example.zanimo11.R;

import java.util.List;

class commentAdapter extends RecyclerView.Adapter<commentAdapter.commentViewHolder> {

    public static  Context context;
    List<Comments> listeComment;

    public commentAdapter(Context context,List<Comments> listeComment) {
        this.context=context;
        this.listeComment=listeComment;
    }

    @NonNull
    @Override
    public commentAdapter.commentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new commentViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_comment_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull commentAdapter.commentViewHolder holder, int position) {
        Comments comment=listeComment.get(position);
        holder.commet.setText(comment.getComment());
        Log.i( "onBindViewHolder: ",comment.getComment());

    }

    @Override
    public int getItemCount() {
        return this.listeComment.size();
    }

    public class commentViewHolder extends RecyclerView.ViewHolder {
        TextView commet;
        public commentViewHolder(@NonNull View itemView) {
            super(itemView);
            commet=(TextView) itemView.findViewById(R.id.comment_post);
        }
    }
}
