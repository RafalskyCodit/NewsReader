package com.example.newsreader.model;

import com.example.newsreader.api.NewsApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.newsreader.api.NewsApi.BASE_URL;

public class RetrofitClient {
    private static RetrofitClient client;
    private NewsApi api;

    private RetrofitClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        api = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build().create(NewsApi.class);
    }

    public static RetrofitClient getInstance(){
        if (client == null){
            client = new RetrofitClient();
        }
        return client;
    }

    public NewsApi getNewsApi(){
        return api;
    }
}
