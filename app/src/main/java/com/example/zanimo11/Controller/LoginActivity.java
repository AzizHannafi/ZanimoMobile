package com.example.zanimo11.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zanimo11.Data.Client;
import com.example.zanimo11.Models.user;
import com.example.zanimo11.R;
import com.example.zanimo11.UserManager.UserSessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    public EditText mail_log;
    public EditText mp_log;
    UserSessionManager session;
    Button inscription;
    public Button SeConnecter;
    Client client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mail_log = (EditText) findViewById(R.id.mailAuth);
        mp_log = (EditText) findViewById(R.id.motdepasseAuth);
        SeConnecter = (Button) findViewById(R.id.seConnecter);
        inscription=(Button)findViewById(R.id.inscription);
        SeConnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });

        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void Login() {
        user user = new user();
        client = new Client();
        String mail = mail_log.getText().toString().trim();
        String mp = mp_log.getText().toString().trim();
        user.setMail(mail);
        user.setMotdepasse(mp);
        session= new UserSessionManager(getApplicationContext());
        client.login(user).enqueue(new Callback<com.example.zanimo11.Models.user>() {
            @Override
            public void onResponse(Call<com.example.zanimo11.Models.user> call, Response<com.example.zanimo11.Models.user> response) {

                if (response.body() == null) {
                    Toast.makeText(getApplicationContext(), "Vérifier Vos Informations !? ", Toast.LENGTH_LONG).show();
                }

                if (response.body() != null) {
                    String mailUser = String.valueOf(response.body().getMail());
                    String mpUser = String.valueOf(response.body().getMotdepasse());
                    if ((mailUser.equals(mail)) && (mpUser.equals(mp))) {
                        session.createUserLoginSession(String.valueOf(response.body().get_id()),response.body().getNom());
                        Toast.makeText(getApplicationContext(),"Bienvenue à Zanimo"+" " + response.body().getNom()+" "+response.body().getPrenom(), Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        // Add new Flag to start new Activity
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                }
            }
            @Override
            public void onFailure(Call<com.example.zanimo11.Models.user> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}