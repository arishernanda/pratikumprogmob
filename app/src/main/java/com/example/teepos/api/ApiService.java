package com.example.teepos.api;

import com.example.teepos.datasignup.ResponseLogin;
import com.example.teepos.datasignup.ResponseSignUp;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

  @FormUrlEncoded
  @POST("register")
  Call<ResponseSignUp> signUp(
          @Field("name") String name,
          @Field("email") String email,
          @Field("phone") String phone,
          @Field("password") String password
  );

  @FormUrlEncoded
  @POST("login")
  Call<ResponseLogin> login(
          @Field("email") String email,
          @Field("password") String password
  );
}
