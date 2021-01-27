package com.example.zanimo11.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zanimo11.Data.Client;
import com.example.zanimo11.Models.user;
import com.example.zanimo11.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity { public EditText nom_add;
    public EditText prenom_add;
    public EditText password_add;
    public EditText email_add;
    public EditText numtel_add;
    public Button Ajouter;

    Client client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nom_add = (EditText) findViewById(R.id.nom);
        prenom_add = (EditText)findViewById(R.id.prenom);
        password_add = (EditText) findViewById(R.id.motdepasse);
        email_add = (EditText) findViewById(R.id.mail);
        numtel_add = (EditText) findViewById(R.id.numtel);
        Ajouter = (Button) findViewById(R.id.Ajouter);
        Ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adduser();
            }
        });
    }
    public void adduser() {
        user user = new user();
        client= new Client();

        String nom = nom_add.getText().toString().trim();
        String prenom = prenom_add.getText().toString().trim();
        String password = password_add.getText().toString().trim();
        String email = email_add.getText().toString().trim();
        String numtel = numtel_add.getText().toString().trim();
        String imageUser="https://cdn.dribbble.com/users/2400016/screenshots/14175779/media/f05cd1d8fd5da9fb2f68458be691f9a8.jpg?compress=1&resize=800x600";
        user.setNom(nom);
        user.setPrenom(prenom);
        user.setMail(email);
        user.setMotdepasse(password);
        user.setNumtel(numtel);
        user.setImageuser(imageUser);
        user.setStatus(false);

        client.addUser(user).enqueue(new Callback<com.example.zanimo11.Models.user>() {
            @Override
            public void onResponse(Call<com.example.zanimo11.Models.user> call, Response<com.example.zanimo11.Models.user> response) {
                Toast.makeText(getApplicationContext(),"Vous étes Inscrit ",Toast.LENGTH_LONG).show();
                Intent intent= new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<com.example.zanimo11.Models.user> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }

}