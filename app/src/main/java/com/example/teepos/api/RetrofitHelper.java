package com.example.teepos.api;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private static Retrofit sRetrofit = null;

    private static String TAG = "retrofit";

    private static Retrofit getInstance() {
        if (sRetrofit == null) {
            // Interceptor
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(@NotNull String strResponse) {
                    Log.d(TAG, strResponse);
                }
            });
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .addInterceptor(headerInterceptor)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.1.72:8000/api/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            sRetrofit = retrofit;
        }

        return sRetrofit;
    }

    private static Interceptor headerInterceptor = new Interceptor() {
        @NotNull
        @Override
        public Response intercept(@NotNull Chain chain) throws IOException {
            Request request = chain.request();
            Request newRequest;

            newRequest = request.newBuilder()
                    .addHeader("Accept", "application/json")
                    .build();
            return chain.proceed(newRequest);
        }
    };

    public static ApiService server() {
        ApiService server = getInstance().create(ApiService.class);
        return server;
    }
}
