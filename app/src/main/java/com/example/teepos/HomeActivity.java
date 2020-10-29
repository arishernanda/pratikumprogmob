package com.example.teepos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import hari.bounceview.BounceView;

public class HomeActivity extends AppCompatActivity {
    private SharedPreferences preferences;
    private String token_login;
    private ImageView btn_home, btn_add, btn_profile;
    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        preferences = getSharedPreferences("credential_login", Context.MODE_PRIVATE);
        token_login = preferences.getString("token_login", null);

        btn_home = (ImageView) findViewById(R.id.home);
        btn_add = (ImageView) findViewById(R.id.add_postingan);
        btn_profile = (ImageView) findViewById(R.id.profile);

        loadFragment(new HomeFragment());
        btn_home.setColorFilter(Color.parseColor("#F0E68C"));
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_add.setColorFilter(Color.parseColor("#FFFFFF"));
                btn_profile.setColorFilter(Color.parseColor("#FFFFFF"));
                btn_home.setColorFilter(Color.parseColor("#F0E68C"));
                loadFragment(new HomeFragment());
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_add.setColorFilter(Color.parseColor("#F0E68C"));
                btn_profile.setColorFilter(Color.parseColor("#FFFFFF"));
                btn_home.setColorFilter(Color.parseColor("#FFFFFF"));
                loadFragment(new AddPostinganFragment());
            }
        });

        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_add.setColorFilter(Color.parseColor("#FFFFFF"));
                btn_profile.setColorFilter(Color.parseColor("#F0E68C"));
                btn_home.setColorFilter(Color.parseColor("#FFFFFF"));
                loadFragment(new ProfileFragment());
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_layout, fragment);
        fragmentTransaction.commit();
    }
}