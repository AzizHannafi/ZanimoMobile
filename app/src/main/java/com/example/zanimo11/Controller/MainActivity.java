package com.example.zanimo11.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zanimo11.Data.Client;
import com.example.zanimo11.Models.user;
import com.example.zanimo11.R;
import com.example.zanimo11.UserManager.UserSessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Client client;
    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        final DrawerLayout dr = findViewById(R.id.drawerlayout);

        session = new UserSessionManager(getApplicationContext());

        // Check user login (this is the important point)
        // If User is not logged in , This will redirect user to LoginActivity
        // and finish current activity from activity stack.
        if (session.checkLogin())
            finish();

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        String id = user.get(UserSessionManager.KEY_ID);
        String nom = user.get(UserSessionManager.KEY_NAME);

        BottomNavigationView botNavView = findViewById(R.id.bottom_navigation);
        botNavView.setSelectedItemId(R.id.btn_lost);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LostFragment()).commit();
        botNavView.setOnNavigationItemSelectedListener(navListener);

        findViewById(R.id.btn_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dr.openDrawer(GravityCompat.START);
            }
        });
        NavigationView nv = findViewById(R.id.sideBar);
        View header = nv.getHeaderView(0);

        TextView Nomnv = (TextView) header.findViewById(R.id.userName);
        TextView Prenomnv = (TextView) header.findViewById(R.id.Lastname);
        ImageView ImgNv = (ImageView) header.findViewById(R.id.imgUserdr);
        client = new Client();
        client.getOneUser(id).enqueue(new Callback<com.example.zanimo11.Models.user>() {
            @Override
            public void onResponse(Call<com.example.zanimo11.Models.user> call, Response<com.example.zanimo11.Models.user> response) {
                Prenomnv.setText(response.body().getPrenom());
                Glide.with(getApplicationContext()).load(response.body().getImageuser()).into(ImgNv);
                //Glide.with(getApplicationContext()).load(response.body().getImageuser()).into(iconUser);

            }

            @Override
            public void onFailure(Call<com.example.zanimo11.Models.user> call, Throwable t) {
            }
        });
        Nomnv.setText(nom);
        nv.setNavigationItemSelectedListener(sidnavListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new
            BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.btn_lost:
                            selectedFragment = new LostFragment();

                            break;
                        case R.id.btn_adopte:
                            selectedFragment = new AdopteFragment();

                            break;
                        case R.id.btn_found:
                            selectedFragment = new FoundFragment();

                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return true;
                }
            };


    private NavigationView.OnNavigationItemSelectedListener sidnavListener = new NavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            DrawerLayout dr = findViewById(R.id.drawerlayout);

            switch (item.getItemId()) {
                case R.id.logoPost:
                    selectedFragment = new AddPostFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    dr.closeDrawers();
                    break;
                case R.id.logoInfoUser:
                    selectedFragment = new infoUserFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    dr.closeDrawers();
                    break;
                case R.id.logoLogout:
                    session.logoutUser();
                    break;
                case R.id.logoApropos:
                    //selectedFragment= new FindUsFragment();
                    Intent intent= new Intent(getApplicationContext(),FindUsActivity.class);
                    startActivity(intent);
                    dr.closeDrawers();
                    break;
            }
            return false;
        }
    };

}


