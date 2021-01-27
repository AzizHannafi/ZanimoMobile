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
import com.example.zanimo11.Models.user;
import com.example.zanimo11.R;
import com.example.zanimo11.UserManager.UserSessionManager;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link infoUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class infoUserFragment extends Fragment {
    UserSessionManager session;
    public EditText nom, prenom, image, mail, tel, motdepasse,status;
    private Button modificationinfo;

    Client Client = new Client();


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public infoUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment infoUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static infoUserFragment newInstance(String param1, String param2) {
        infoUserFragment fragment = new infoUserFragment();
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

        View v = inflater.inflate(R.layout.fragment_info_user, container, false);
        nom = (EditText) v.findViewById(R.id.nom);
        prenom = (EditText) v.findViewById(R.id.prenom);
        mail = (EditText) v.findViewById(R.id.mail);
        image = (EditText) v.findViewById(R.id.image);
        status = (EditText) v.findViewById(R.id.status);
        tel = (EditText) v.findViewById(R.id.tel);
        motdepasse = (EditText) v.findViewById(R.id.motdepasse);
        modificationinfo = (Button) v.findViewById(R.id.btn_Modification);


        session = new UserSessionManager(getContext());
        HashMap<String, String> userSession = session.getUserDetails();
        String id = userSession.get(UserSessionManager.KEY_ID);

        Client.getOneUser(id).enqueue(new Callback<user>() {
            @Override
            public void onResponse(Call<user> call, Response<user> response) {
                nom.setText(response.body().getNom());
                prenom.setText(response.body().getPrenom());
                mail.setText(response.body().getMail());
                image.setText(response.body().getImageuser());
                tel.setText(response.body().getNumtel());
                motdepasse.setText(response.body().getMotdepasse());
            }

            @Override
            public void onFailure(Call<user> call, Throwable t) {

            }
        });

        modificationinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Modification_Info();
            }
        });

        return v;
    }

    private void Modification_Info() {
        String usernom = nom.getText().toString().trim();
        String userprenom = prenom.getText().toString().trim();
        String usermail = mail.getText().toString().trim();
        String userimage = image.getText().toString().trim();
        String usertel = tel.getText().toString().trim();
        String usermotdepasse = motdepasse.getText().toString().trim();

        user User = new user();

        User.setNom(usernom);
        User.setPrenom(userprenom);
        User.setMail(usermail);
        User.setImageuser(userimage);
        User.setNumtel(usertel);
        User.setMotdepasse(usermotdepasse);
        User.setStatus(false);
        Log.i(" Nom : ", nom.getText().toString());

        session = new UserSessionManager(getContext());
        HashMap<String, String> userSession = session.getUserDetails();
        String id = userSession.get(UserSessionManager.KEY_ID);

        Client.UpdateUser(id, User).enqueue(new Callback<user>() {

            @Override
            public void onResponse(Call<user> call, Response<user> response) {
                Toast.makeText(getContext(), " Les Informations sont Modifier", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<user> call, Throwable Err) {
                Toast.makeText(getContext(), Err.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}