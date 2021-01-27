package com.example.zanimo11.Controller;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zanimo11.Data.Client;
import com.example.zanimo11.Models.posts;
import com.example.zanimo11.R;
import com.example.zanimo11.UserManager.UserSessionManager;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddPostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddPostFragment extends Fragment {
    UserSessionManager session;
    public EditText image, date, description, dateExpiration, genreAnimale, typeAnimale, typeAnnonce;
    private Button AjouterPost;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddPostFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddPostFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddPostFragment newInstance(String param1, String param2) {
        AddPostFragment fragment = new AddPostFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_add_post, container, false);

        image = (EditText) v.findViewById(R.id.AnimalImage);
        date = (EditText) v.findViewById(R.id.PostDate);
        description = (EditText) v.findViewById(R.id.PostDescription);
        dateExpiration = (EditText) v.findViewById(R.id.DateExpiration);
        genreAnimale = (EditText) v.findViewById(R.id.GenreAnimale);
        typeAnimale = (EditText) v.findViewById(R.id.TypeAnimale);
        typeAnnonce = (EditText) v.findViewById(R.id.TypePost);
        AjouterPost = (Button) v.findViewById(R.id.btn_AddPost);

        AjouterPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add_Post();
            }
        });
        return v;
    }

    private void Add_Post() {
        String img = image.getText().toString().trim();
        String dat = date.getText().toString().trim();
        String descrip = description.getText().toString().trim();
        String dateexp = dateExpiration.getText().toString().trim();
        String genre = genreAnimale.getText().toString().trim();
        String typeanimale = typeAnimale.getText().toString().trim();
        String typeannonce = typeAnnonce.getText().toString().trim();
        Client Client = new Client();
        posts Posts = new posts();
        Posts.setImage("https://cdn.dribbble.com/users/1044993/screenshots/14140999/media/a59cfaa50deba04004484f688a70427c.png?compress=1&resize=800x600");
        Posts.setDate(dat);
        Posts.setDescription(descrip);
        Posts.setDateExpiration("25/01/2021");
        Posts.setGenreAnimale(genre);
        Posts.setTypeAnimale(typeanimale);
        Posts.setTypeAnnonce(typeannonce);
        //Log.i(" Image : ", image.getText().toString());
        session = new UserSessionManager(getContext());
        HashMap<String, String> userSession = session.getUserDetails();
        String id = userSession.get(UserSessionManager.KEY_ID);
        Client.AddPost(id, Posts).enqueue(new Callback<posts>() {
            @Override
            public void onResponse(Call<posts> call, Response<posts> response) {
                Toast.makeText(getContext(), " Votre Annonce est Ajouter ", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<posts> call, Throwable Err) {
                Toast.makeText(getContext(), Err.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}