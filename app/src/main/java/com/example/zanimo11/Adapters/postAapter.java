package com.example.zanimo11.Adapters;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zanimo11.Controller.FindUsActivity;
import com.example.zanimo11.Data.Client;
import com.example.zanimo11.Data.UserInterface;
import com.example.zanimo11.Models.Comments;
import com.example.zanimo11.Models.posts;
import com.example.zanimo11.Models.user;
import com.example.zanimo11.R;
import com.example.zanimo11.UserManager.UserSessionManager;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class postAapter extends RecyclerView.Adapter<postAapter.postViewHolder> {
    public static Context context;
    List<posts> listPost;
    Dialog postDialog;
    user user;
    Client client;
    EditText champComment;
    ImageView imgSend;
    String idPost = "";



    UserSessionManager session;


    public postAapter(Context context, List<posts> listPost, user user) {
        this.context = context;
        this.listPost = listPost;
        this.user = user;
    }


    @NonNull
    @Override
    public postViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new postViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_view_post, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull postViewHolder holder, int position) {
        posts post = listPost.get(position);
        holder.description.setText(post.getDescription());
        Glide.with(context).load(post.getImage()).into(holder.ImagePost);
        client = new Client();
        session = new UserSessionManager(getContext());
        HashMap<String, String> userSession = session.getUserDetails();

        String id_user = userSession.get(UserSessionManager.KEY_ID);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client.getOnePost(user.get_id(), post.get_id());
                idPost = post.get_id();
                postDialog = new Dialog(context);
                postDialog.setContentView(R.layout.activity_one_post);

                TextView name = (TextView) postDialog.findViewById(R.id.nameOnePost);
                TextView prenom = (TextView) postDialog.findViewById(R.id.prenom);
                ImageView imageuser = (ImageView) postDialog.findViewById(R.id.imageUserOnePost);
                ImageView imagePost = (ImageView) postDialog.findViewById(R.id.imagePostOnePost);
                TextView description = (TextView) postDialog.findViewById(R.id.descriptionOnePost);
                TextView comment = (TextView) postDialog.findViewById(R.id.commentTextOnePost);
                ImageView imgComment = (ImageView) postDialog.findViewById(R.id.commentOnePost);
                EditText champComment = (EditText) postDialog.findViewById(R.id.champComment);
                ImageView imgSend = (ImageView) postDialog.findViewById(R.id.send);
                LinearLayout linearLayout = (LinearLayout) postDialog.findViewById(R.id.champAddComment);
                ImageView btndelete = (ImageView) postDialog.findViewById(R.id.btndelete);
                Button btnCall = (Button) postDialog.findViewById(R.id.btncall);

                if (user.get_id().equals(id_user)) {
                    btndelete.setVisibility(View.VISIBLE);
                    btndelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            client.deleteOnePoste(user.get_id(), post.get_id()).enqueue(new Callback<posts>() {
                                @Override
                                public void onResponse(Call<posts> call, Response<posts> response) {
                                    Toast.makeText(getContext(), " Publication Supprimer ! ", Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onFailure(Call<posts> call, Throwable t) {
                                    Toast.makeText(getContext(), " Erreur de Suppression !? ", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    });
                } else {
                    btndelete.setVisibility(View.INVISIBLE);
                }

                name.setText(user.getNom());
                prenom.setText(user.getPrenom());
                Glide.with(context).load(user.getImageuser()).into(imageuser);
                Glide.with(context).load(post.getImage()).into(imagePost);
                description.setText(post.getDescription());
                comment.setText("");
                for (int i = 0; i < post.getComments().size(); i++) {
                    comment.setText(comment.getText() + post.getComments().get(i).getComment() + "\n");
                }
                imgComment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        champComment.setVisibility(View.VISIBLE);
                        imgSend.setVisibility(View.VISIBLE);
                        imgSend.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                addComment();
                                champComment.setVisibility(View.INVISIBLE);
                                imgSend.setVisibility(View.INVISIBLE);
                                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
                            }
                        });
                    }
                });
                btnCall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getContext(), "btn call clicked", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:"+user.getNumtel()));
                        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return;
                        }
                        context.startActivity(intent);


                }
            });

                postDialog.show();
            }
        });
    }

    private Context getContext() {
        return this.context;
    }

    @Override
    public int getItemCount() {
        return this.listPost.size();
    }

    public static class postViewHolder extends RecyclerView.ViewHolder {
        LinearLayout postlinearlayout;
        TextView description;
        ImageView ImagePost;
        RecyclerView commentRecyclerView;

        public postViewHolder(View itemView) {
            super(itemView);
            postlinearlayout = (LinearLayout) itemView.findViewById(R.id.postLayout);
            description = (TextView) itemView.findViewById(R.id.description);
            ImagePost = (ImageView) itemView.findViewById(R.id.imagePost);
            //commentRecyclerView = (RecyclerView) itemView.findViewById(R.id.commentRecyclerView);
        }
    }

    public void addComment() {
        Comments comments = new Comments();
        client = new Client();

        session = new UserSessionManager(getContext());
        HashMap<String, String> userSession = session.getUserDetails();

        String nom = userSession.get(UserSessionManager.KEY_NAME);

        EditText champComment = (EditText) postDialog.findViewById(R.id.champComment);
        String comment = champComment.getText().toString();

        comments.setComment("@" + nom + ":" + comment);
        client.aadComent(user.get_id(), idPost, comments).enqueue(new Callback<Comments>() {
            @Override
            public void onResponse(Call<Comments> call, Response<Comments> response) {
                Toast.makeText(getContext(), " Commentaire Ajouter ", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Comments> call, Throwable t) {
                Toast.makeText(getContext(), " Erreur D'ajout !? " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}