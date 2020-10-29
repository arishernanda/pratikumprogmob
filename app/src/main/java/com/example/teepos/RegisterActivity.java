package com.example.teepos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.teepos.api.ApiService;
import com.example.teepos.api.RetrofitHelper;
import com.example.teepos.datasignup.ResponseSignUp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private EditText phone_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ImageView imageView = (ImageView) findViewById(R.id.bg_landing_page);
        Glide.with(this).load(R.drawable.bg_login_page)
                .centerCrop()
                .into(imageView);
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        phone_et = (EditText) findViewById(R.id.phone_regis);

        Button btn_register = (Button) findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nama_et = (EditText) findViewById(R.id.nama);
                EditText email_et = (EditText) findViewById(R.id.email);
                EditText phone_et = (EditText) findViewById(R.id.phone_regis);
                EditText password_et = (EditText) findViewById(R.id.password);
                asyncSignUp(
                        nama_et.getText().toString(),
                        email_et.getText().toString(),
                        phone_et.getText().toString(),
                        password_et.getText().toString()
                );
            }
        });
    }

    private void asyncSignUp(String nama, String email, String phone, String password) {
        RetrofitHelper.server().signUp(
                nama,
                email,
                phone,
                password
        ).enqueue(new Callback<ResponseSignUp>() {
            @Override
            public void onResponse(Call<ResponseSignUp> call, Response<ResponseSignUp> response) {
                Intent toLogin = new Intent(RegisterActivity.this, LoginActivity.class);
                String msg = "Register Berhasil, silahkan login.";
                toLogin.putExtra("msg", msg);
                startActivity(toLogin);
            }

            @Override
            public void onFailure(Call<ResponseSignUp> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Gagal Register", Toast.LENGTH_SHORT).show();
            }
        });
    }


}